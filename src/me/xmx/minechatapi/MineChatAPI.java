package me.xmx.minechatapi;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MineChatAPI extends JavaPlugin {

	File configFile;
	YamlConfiguration config;
	private boolean showConnectionMsg = true;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getLogger().info("Listening for new MineChat connections...");
		
		configFile = new File(getDataFolder() + "/config.yml");
		if(!configFile.exists()) {
			saveDefaultConfig();
		}else {
			if(getConfig().get("blockConnectionMsgs") == null) {
				getConfig().set("blockConnectionMsgs", true);
				saveConfig();
			}
		}
		handleConfig();
	}
	
	private void handleConfig() {
		final boolean blockConnMsgs = getConfig().getBoolean("blockConnectionMsgs");
		showConnectionMsg = (!blockConnMsgs);
		if(blockConnMsgs) {
			getLogger().info("Blocking all MineChat connection messages...");
		}
	}
	
	public void onDisable() {
		getLogger().info("Closing connection listener...");
	}
	
	public void setShowConnectionMessage(boolean flag) {
		this.showConnectionMsg = flag;
	}
	
	public boolean showConnectionMessage() {
		return this.showConnectionMsg;
	}
	
}
