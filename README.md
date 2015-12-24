# MineChatAPI
A Minecraft API for controlling MineChat users on Spigot and Bukkit servers.

*The awkward spacing in the code was not intended.*

## Developing with MineChatAPI
Like most plugins, you need to download and place a **MineChatAPI** jar in your plugins folder and add it as a library to your project.

**Optional Step:** 
If you're seeking to hide MineChat connection messages, you'll need to hook into the plugin. 
See "**Hooking into MineChatAPI**"

Now, with MineChatAPI installed on your server it will start calling the event [**MineChatClientJoinEvent**](https://github.com/xMakerx/MineChatAPI/blob/master/src/me/xmx/minechatapi/events/MineChatClientJoinEvent.java, "MineChatClientJoinEvent.java") when a MineChat
client joins and a [**MineChatClientLeaveEvent**](https://github.com/xMakerx/MineChatAPI/blob/master/src/me/xmx/minechatapi/events/MineChatClientLeaveEvent.java, "MineChatClientLeaveEvent.java") event when one leaves.

For example, we could disconnect all iPhone users by simply listening to **MineChatClientJoinEvent**.
```java
public void onMineChatClientConnect(final MineChatClientJoinEvent evt) {
  final Player player = evt.getPlayer();
  final ChatConnection conn = evt.getConnection();
  if(conn.getPhoneType() == PhoneType.IPHONE) {
    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&4iPhone users are not allowed on our server."));
  }
}
```
We could also check if a player is using MineChat using the [**ClientManager**](https://github.com/xMakerx/MineChatAPI/blob/master/src/me/xmx/minechatapi/ClientManager.java, "ClientManager.java") class.
```java
public void onPlayerDeath(final PlayerDeathEvent evt) {
  final Player player = evt.getEntity();
  if(ClientManager.usingMineChat(player)) {
    // This player is using MineChat!
  }
}
```
The possibilities are endless! Those are just the basics of what you can do with this API. If you need additional help feel free to contact me and I'll get back to you as soon as possible.

## Hooking into MineChatAPI
To hook into MineChatAPI you should do something similar to the following.
You do not need to check if the Plugin object is an instance of MineChatAPI, however, 
it is highly suggested.
```java
final Plugin mineChatPlugin = Bukkit.getServer().getPluginManager().getPlugin("MineChatAPI");
MineChatAPI mineChatAPI;
if(mineChatPlugin != null && mineChatPlugin instanceof MineChatAPI) {
  mineChatAPI = (MineChatAPI)mineChatPlugin;
}
```
Once you're all hooked up, you can now disable MineChat messages manually like so:
```java
mineChatAPI.setShowConnectionMessage(false);
```
