package me.Ranil.STNPC.commands;

import java.util.Arrays;

import me.Ranil.STNPC.STNPCBase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterSpawnArea implements CommandExecutor {

	private STNPCBase plugin;

	public RegisterSpawnArea(STNPCBase instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getLogger()
					.info("Error: You must be a player to register a spawning location");
		}
		Player player = (Player) sender;
		if (!player.isOp()) {
			player.sendMessage(ChatColor.RED
					+ "You don't have permission to use this command");
		}
		if (cmd.getName().equalsIgnoreCase("spawnregion")) {
			if (args.length == 2) {
				String base = args[1];
				String id = args[0];
				if (id != null) {
					if (base.equalsIgnoreCase("10")
							|| base.equalsIgnoreCase("20")
							|| base.equalsIgnoreCase("30")) {
						Location location = player.getLocation();
						if (plugin.config.contains(id)) {
							player.sendMessage(ChatColor.RED
									+ "ERROR: ID already in use!");
						} else {
							saveLocation(id, base, location);
							player.sendMessage(ChatColor.AQUA
									+ "Saved location " + id + " at level "
									+ base);
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Base levels must be in multiples of 10!");
					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "Usage: /spawnregion <ID> <baselevel>");
				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "Usage: /spawnregion <ID> <baselevel>");
			}
		}
		return false;
	}

	public void saveLocation(String id, String level, Location location) {
		String[] list = {id};
		
		plugin.config.set(id + ".x", location.getX());
		plugin.config.set(id + ".y", location.getY());
		plugin.config.set(id + ".z", location.getZ());
		plugin.config.getStringList(level).add(id);
		if(!plugin.config.contains(level)){
			plugin.config.set(level, Arrays.asList(list));
		}
		plugin.saveConfig();
		
	}
}
