package cat.nyaa.autobloodmoon;

import cat.nyaa.autobloodmoon.arena.ArenaConfig;
import cat.nyaa.autobloodmoon.level.LevelConfig;
import cat.nyaa.autobloodmoon.mobs.MobConfig;
import cat.nyaa.autobloodmoon.stats.StatsConfig;
import cat.nyaa.nyaacore.configuration.PluginConfigure;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Configuration extends PluginConfigure {
    public AutoBloodmoon plugin;
    @Serializable
    public String language = "en_US";
    @Serializable
    public int call_timeout = 600;
    @Serializable
    public int preparation_time = 600;
    @Serializable
    public boolean save_inventory = false;
    @Serializable
    public boolean border_particle = true;
    @Serializable
    public int border_particle_height = 3;
    @Serializable
    public boolean pvp = false;
    @Serializable
    public boolean pvp_scoreboard_team = true;// use vanilla scoreboard team to prevent pvp
    @Serializable
    public ChatColor pvp_scoreboard_team_color = ChatColor.AQUA;
    @Serializable
    public int temp_pvp_protection_time = 10;// 10 seconds
    @Serializable
    public int pvp_penalty_percent = 0;
    @Serializable
    public int pvp_penalty_max = 0;
    @Serializable(name = "title_fadein_tick")
    public int title_fadein_tick = 10;
    @Serializable(name = "title_stay_tick")
    public int title_stay_tick = 30;
    @Serializable(name = "title_fadeout_tick")
    public int title_fadeout_tick = 10;
    
    @Serializable
    public String schematicsDir = "plugins/WorldEdit/schematics";
    
    @Serializable(name = "mob_limits.infernal")
    public int mob_limits_infernal = 100;
    @Serializable(name = "mob_limits.normal")
    public int mob_limits_normal = 100;

    @Serializable
    public int reward_tax = 10;

    @StandaloneConfig
    public ArenaConfig arenaConfig;
    @StandaloneConfig
    public RewardConfig rewardConfig;
    @StandaloneConfig
    public MobConfig mobConfig;
    @StandaloneConfig
    public LevelConfig levelConfig;
    @StandaloneConfig
    public StatsConfig statsConfig;

    @Override
    protected JavaPlugin getPlugin() {
        return plugin;
    }

    public Configuration(AutoBloodmoon pl) {
        plugin = pl;
        arenaConfig = new ArenaConfig(plugin);
        rewardConfig = new RewardConfig(plugin);
        mobConfig = new MobConfig(plugin);
        levelConfig = new LevelConfig(plugin);
        statsConfig = new StatsConfig(plugin);
    }
}
