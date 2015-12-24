package me.xmx.minechatapi;

import org.bukkit.entity.Player;

public class ChatConnection {
	
	private final Player player;
	private final PhoneType type;
	
	public ChatConnection(Player player, PhoneType type) {
		this.player = player;
		this.type = type;
	}
	
	public void close(String clientMsg) {
		if(ClientManager.usingMineChat(player)) {
			ClientManager.removeClient(player.getUniqueId(), this);
			player.kickPlayer(clientMsg);
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public PhoneType getPhoneType() {
		return this.type;
	}
}
