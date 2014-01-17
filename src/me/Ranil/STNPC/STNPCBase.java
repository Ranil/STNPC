package me.Ranil.STNPC;

import me.Ranil.STNPC.commands.RegisterSpawnArea;
import me.Ranil.STNPC.enums.CustomEntityType;
import me.Ranil.STNPC.events.BaseEvents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class STNPCBase extends JavaPlugin {

	private BaseEvents events = new BaseEvents(this);
	
	public FileConfiguration config;
	
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(events, this);

		this.config = getConfig();
		
		registerCommand("spawnregion");
		
		CustomEntityType.registerEntities();
	}
	
	public void onDisable(){
		CustomEntityType.unregisterEntities();
		this.saveConfig();
	}
	
	public void registerCommand(String cmd){
		this.getCommand(cmd).setExecutor(new RegisterSpawnArea(this));
	}
}
