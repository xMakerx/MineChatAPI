package me.xmx.minechatapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.xmx.minechatapi.events.EventMineChatClientJoin;
import me.xmx.minechatapi.events.EventMineChatClientLeave;

public class Events implements Listener {
	
	final MineChatAPI main;
	
	public Events(final MineChatAPI main) {
		this.main = main;
	}
	
	@EventHandler
	public void onMineChatClientJoin(final EventMineChatClientJoin evt) {
		if(!evt.isCancelled()) {
			ClientManager.addClient(evt.getPlayer().getUniqueId(), evt.getConnection());
		}
	}
	
	@EventHandler
	public void onMineChatClientLeave(final EventMineChatClientLeave evt) {
		if(!evt.isCancelled()) {
			ClientManager.removeClient(evt.getPlayer().getUniqueId(), evt.getConnection());
		}
	}
	
	@EventHandler
	public void onPlayerLeaveEvent(final PlayerQuitEvent evt) {
		final Player player = evt.getPlayer();
		if(ClientManager.usingMineChat(player)) {
			final ChatConnection conn = ClientManager.getConnection(player);
			ClientManager.removeClient(player.getUniqueId(), conn);
			main.getServer().getPluginManager().callEvent(new EventMineChatClientLeave(player, conn));
		}
	}
	
	@EventHandler
	public void onPlayerChatEvent(final AsyncPlayerChatEvent evt) {
		final Player player = evt.getPlayer();
		final String message = evt.getMessage();
		
		if(message.toLowerCase().contains("minechat") && message.toLowerCase().contains("connected")) {
			// The player connected with MineChat
			final PhoneType type = (message.toLowerCase().contains("iphone")) ? PhoneType.IPHONE : PhoneType.ANDROID;
			
			final EventMineChatClientJoin clJoin = new EventMineChatClientJoin(player, new ChatConnection(player, type));
			main.getServer().getPluginManager().callEvent(clJoin);
			if(!main.showConnectionMessage()) {
				evt.setCancelled(true);
			}
		}
	}

}
