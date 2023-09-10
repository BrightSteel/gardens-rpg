//package com.gardensmc.gardensrpg.scheduler.schedules;
//
//import com.gardensmc.gardensrpg.GardensRPG;
//import com.gardensmc.gardensrpg.scheduler.Schedule;
//import com.gardensmc.gardensrpg.util.ParticleUtil;
//import org.bukkit.Bukkit;
//import org.bukkit.Color;
//import org.bukkit.Material;
//import org.bukkit.Particle;
//import org.bukkit.entity.Player;
//
//import java.util.Set;
//import java.util.UUID;
//
//public class SpawnActiveParticlesSchedule extends Schedule {
//
//    @Override
//    public void schedule() {
//        Bukkit.getScheduler().scheduleSyncRepeatingTask(GardensRPG.plugin, () -> {
//            Set<UUID> primedPlayers = GardensRPG.castActiveAbilityHandler.getPrimedPlayers();
//            for (UUID uuid : primedPlayers) {
//                Player player = Bukkit.getPlayer(uuid);
//                if (player != null) {
//                    player.getWorld().spawnParticle(
//                            Particle.REDSTONE,
//                            ParticleUtil.getRightSide(player.getLocation().add(0, 0.75, 0), 0),
//                            5,
//                            0.2,
//                            0.3,
//                            0.2,
//                            0.1,
//                            new Particle.DustOptions(Color.PURPLE, 1)
//                    );
//                }
//            }
//        }, 0, 20);
//    }
//}
