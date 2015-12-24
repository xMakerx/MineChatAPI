package me.xmx.minechatapi;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ClientManager {
	
	private static HashMap<UUID, ChatConnection> clients = new HashMap<UUID, ChatConnection>();
	
	/**
	 * Start keeping track of a client by UUID.
	 * You must add a client to be able to check if they're using a MineChat client.
	 * @param UUID id
	 * @param ChatConnection conn
	 */
	
	public static void addClient(UUID id, ChatConnection conn) {
		if(conn == null || id == null) return;
		clients.put(id, conn);
	}
	
	/**
	 * Stop keeping track of a client.
	 * This should be used when you're done checking if a client is using MineChat.
	 * @param UUID id
	 * @param ChatConnection conn
	 */
	
	public static void removeClient(UUID id, ChatConnection conn) {
		if(conn == null || id == null) return;
		clients.remove(id, conn);
	}
	
	/**
	 * Sees if there's an entry for a UUID.
	 * @param UUID id
	 * @return boolean
	 * True if a client exists with that UUID.
	 * False if a client doesn't exist with that UUID.
	 */
	
	private static boolean hasClientByUUID(UUID id) {
		if(clients.containsKey(id)) return true;
		return false;
	}
	
	/**
	 * Returns the ChatConnection of a client from UUID.
	 * @param UUID id
	 * @return ChatConnection, if client found, and null if UUID not in HashMap.
	 */
	
	private static ChatConnection getConnectionByUUID(UUID id) {
		if(hasClientByUUID(id)) {
			return clients.get(id);
		}
		return null;
	}
	
	/**
	 * Checks if a player is using MineChat.
	 * @param Player player
	 * @return boolean
	 */
	
	public static boolean usingMineChat(Player player) {
		return hasClientByUUID(player.getUniqueId());
	}
	
	/**
	 * Checks if a UUID is using MineChat
	 * @param UUID id
	 * @return boolean
	 */
	
	public static boolean usingMineChat(UUID id) {
		return hasClientByUUID(id);
	}
	
	/**
	 * Gets a player's ChatConnection object if one exists.
	 * @param Player player
	 * @return ChatConnection if one exists or null if one doesn't.
	 */
	
	public static ChatConnection getConnection(Player player) {
		return getConnectionByUUID(player.getUniqueId());
	}
	
	/**
	 * Returns a HashMap of all the clients the manager is keeping track of.
	 * @return HashMap<UUID, ChatConnection>
	 */
	
	public static HashMap<UUID, ChatConnection> getClients() {
		return ClientManager.clients;
	}
}
