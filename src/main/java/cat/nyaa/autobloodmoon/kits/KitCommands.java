package cat.nyaa.autobloodmoon.kits;

import cat.nyaa.autobloodmoon.AutoBloodmoon;
import cat.nyaa.utils.CommandReceiver;
import cat.nyaa.utils.Internationalization;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class KitCommands extends CommandReceiver<AutoBloodmoon> {
    private AutoBloodmoon plugin;

    public KitCommands(Object plugin, Internationalization i18n) {
        super((AutoBloodmoon) plugin, i18n);
        this.plugin = (AutoBloodmoon) plugin;
    }

    @Override
    public String getHelpPrefix() {
        return "kit";
    }

    @SubCommand(value = "create", permission = "bm.admin")
    public void commandCreateKit(CommandSender sender, Arguments args) {
        Player player = asPlayer(sender);
        String kitName = args.next();
        String type = args.next().toUpperCase();
        KitItems.KitType kitType = null;
        for (KitItems.KitType k : KitItems.KitType.values()) {
            if (k.name().equals(type)) {
                kitType = k;
                break;
            }
        }
        if (kitType == null) {
            msg(player, "user.kit.kit_type_error");
            return;
        }
        if (!plugin.cfg.rewardConfig.kits.containsKey(kitName)) {
            plugin.cfg.rewardConfig.kits.put(kitName, new HashMap<>());
        }
        KitItems kit = new KitItems(kitName, kitType, null);
        plugin.kitListener.selectChest.put(player, kit);
        msg(player, "user.kit.right_click_chest");
    }

    @SubCommand(value = "view", permission = "bm.admin")
    public void commandViewKit(CommandSender sender, Arguments args) {
        Player player = asPlayer(sender);
        String kitName = args.next();
        String type = args.next().toUpperCase();
        KitItems.KitType kitType = null;
        for (KitItems.KitType k : KitItems.KitType.values()) {
            if (k.name().equals(type)) {
                kitType = k;
                break;
            }
        }
        if (kitType == null) {
            msg(sender, "user.kit.kit_type_error");
            return;
        }
        if (!plugin.cfg.rewardConfig.kits.containsKey(kitName) ||
                !plugin.cfg.rewardConfig.kits.get(kitName).containsKey(kitType)) {
            msg(sender, "user.kit.not_found");
            return;
        }
        KitItems kit = plugin.cfg.rewardConfig.kits.get(kitName).get(kitType);
        Inventory inv = Bukkit.createInventory(null, 54, kitName + " - " + kitType.name());
        inv.setContents(kit.getItems().toArray(new ItemStack[kit.getItems().size()]));
        player.openInventory(inv);
    }

    @SubCommand(value = "remove", permission = "bm.admin")
    public void commandRemoveKit(CommandSender sender, Arguments args) {
        Player player = asPlayer(sender);
        String kitName = args.next();
        String type = args.next().toUpperCase();
        KitItems.KitType kitType = null;
        for (KitItems.KitType k : KitItems.KitType.values()) {
            if (k.name().equals(type)) {
                kitType = k;
                break;
            }
        }
        if (kitType == null) {
            msg(sender, "user.kit.kit_type_error");
            return;
        }
        if (!plugin.cfg.rewardConfig.kits.containsKey(kitName) ||
                !plugin.cfg.rewardConfig.kits.get(kitName).containsKey(kitType)) {
            msg(sender, "user.kit.not_found");
            return;
        }
        plugin.cfg.rewardConfig.kits.get(kitName).remove(kitType);
        plugin.cfg.rewardConfig.save();
        msg(player, "user.kit.save_success");
    }

    @SubCommand(value = "list", permission = "bm.admin")
    public void commandListKit(CommandSender sender, Arguments args) {
        for (String kitName : plugin.cfg.rewardConfig.kits.keySet()) {
            for (KitItems.KitType type : plugin.cfg.rewardConfig.kits.get(kitName).keySet()) {
                sender.sendMessage(kitName + " - " + type.name());
            }
        }
    }
}