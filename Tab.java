import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public class TabPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Le plugin TabPlugin a été activé !");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setTabHeaderAndFooter(player, "&bBienvenue sur le serveur", "&eRejoignez notre Discord : discord.gg/monserveur");
    }

    public void setTabHeaderAndFooter(Player player, String header, String footer) {
        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);

        if (objective == null) {
            objective = scoreboard.registerNewObjective("tabheaderfooter", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }

        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', header));

        // Utilisation d'un faux joueur pour afficher le footer
        Score score = objective.getScore(ChatColor.translateAlternateColorCodes('&', footer));
        score.setScore(0);

        player.setScoreboard(scoreboard);
    }
}
