package com.alessiodp.securityvillagers.addons.internal;

import com.alessiodp.securityvillagers.SecurityVillagers;
import com.alessiodp.securityvillagers.addons.external.GravityUpdaterHandler;
import com.alessiodp.securityvillagers.configuration.Constants;
import com.alessiodp.securityvillagers.configuration.data.ConfigMain;
import com.alessiodp.securityvillagers.configuration.data.Messages;
import com.alessiodp.securityvillagers.utils.SVPermission;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ADPUpdater {
	private static SecurityVillagers plugin;
	
	@Getter private static String foundVersion = "";
	
	public ADPUpdater(SecurityVillagers instance) {
		plugin = instance;
	}
	
	public static void alertPlayers() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK
				&& ConfigMain.SECURITYVILLAGERS_UPDATES_WARN
				&& !foundVersion.isEmpty()) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				alertPlayer(p);
			}
		}
	}
	public static void alertPlayer(Player player) {
		if (player.hasPermission(SVPermission.ADMIN_UPDATES.toString())) {
			plugin.sendMessage(player, Messages.SECURITYVILLAGERS_UPDATEAVAILABLE
					.replace("%version%", foundVersion)
					.replace("%thisversion%", plugin.getDescription().getVersion()));
		}
	}
	
	public static void asyncTaskCheckUpdates() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK) {
			plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
				checkUpdates();
			}, 20, 20*60*60*24); // 24 hours
		}
	}
	
	public static void asyncCheckUpdates() {
		if (ConfigMain.SECURITYVILLAGERS_UPDATES_CHECK) {
			plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
				checkUpdates();
			}); // 24 hours
		}
	}
	
	
	private static void checkUpdates() {
		foundVersion = "";
		String version;
		JSONObject data = getVersionInfo();
		if (data != null) {
			version = (String) data.get(Constants.UPDATER_FIELD_VERSION);
		} else {
			// ADP Updater failed
			plugin.log(Constants.UPDATER_FALLBACK);
			version = getUpdatesFromFallback();
		}
		
		if (checkVersion(version, plugin.getDescription().getVersion())) {
			// New version found
			foundVersion = version;
			
			plugin.log(Constants.UPDATER_FOUND
					.replace("{currentVersion}", plugin.getDescription().getVersion())
					.replace("{newVersion}", foundVersion));
			alertPlayers();
		}
	}
	
	private static String getUpdatesFromFallback() {
		String ret = "";
		GravityUpdaterHandler updater = new GravityUpdaterHandler(plugin,
				Constants.CURSE_PROJECT_ID,
				null,
				GravityUpdaterHandler.UpdateType.NO_DOWNLOAD,
				false);
		if (updater.getResult() == GravityUpdaterHandler.UpdateResult.UPDATE_AVAILABLE) {
			String[] split = updater.getLatestName().split(GravityUpdaterHandler.DELIMETER);
			ret = split[split.length - 1];
		}
		return ret;
	}
	
	private static JSONObject getVersionInfo() {
		JSONObject ret = null;
		try {
			URLConnection conn = new URL(Constants.UPDATER_URL
					.replace("{version}", plugin.getDescription().getVersion())).openConnection();
			conn.setConnectTimeout(10000);
			conn.addRequestProperty("User-Agent", "ADP Updater");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			ret = (JSONObject) JSONValue.parse(br.readLine());
			br.close();
		} catch (IOException ex) {
			plugin.log(Constants.UPDATER_FAILED_IO);
		} catch (Exception ex) {
			plugin.log(Constants.UPDATER_FAILED_GENERAL);
		}
		return ret;
	}
	
	private static boolean checkVersion(String ver, String compareWith) {
		boolean ret = false;
		String[] splitVer = splitVersion(ver);
		String[] splitCompareWith = splitVersion(compareWith);
		
		try {
			for (int c=0; c < splitVer.length && !ret; c++) {
				int a = Integer.parseInt(splitVer[c]);
				int b = c < splitCompareWith.length ? Integer.parseInt(splitCompareWith[c]) : 0;
				if (a > b)
					ret = true;
				else if (a < b)
					break;
			}
		} catch (Exception ex) {
			ret = true;
		}
		return ret;
	}
	
	private static String[] splitVersion(String value) {
		value = value.split(Constants.UPDATER_DELIMITER_TYPE)[0];
		return value.split(Constants.UPDATER_DELIMITER_VERSION);
	}
}
