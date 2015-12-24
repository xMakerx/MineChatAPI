package me.xmx.minechatapi.events;

import org.bukkit.entity.Player;

/**
 * Called when a client is found to be using MineChat leaves.s
 * @param player (The player object of the user)
 * @param conn (The generated ChatConnection object for the MineChat connection)
 */

import me.xmx.minechatapi.ChatConnection;

public class EventMineChatClientLeave extends MineChatEvent {
	
	private final Player player;
	private final ChatConnection conn;
	
	public EventMineChatClientLeave(final Player player, final ChatConnection conn) {
		this.player = player;
		this.conn = conn;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public ChatConnection getConnection() {
		return this.conn;
	}
}