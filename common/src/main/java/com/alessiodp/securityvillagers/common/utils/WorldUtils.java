package com.alessiodp.securityvillagers.common.utils;

import java.util.List;

public class WorldUtils {
	public static boolean containsWorld(List<String> allowedWorlds, String world) {
		for (String allowedWorld : allowedWorlds) {
			if (allowedWorld.equalsIgnoreCase("-" + world))
				return false;
			
			if (allowedWorld.equals("*") || allowedWorld.equalsIgnoreCase(world))
				return true;
		}
		return false;
	}
}
