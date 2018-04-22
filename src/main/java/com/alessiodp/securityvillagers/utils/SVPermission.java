package com.alessiodp.securityvillagers.utils;

public enum SVPermission {
	EGG("securityvillagers.egg"),
	HIT("securityvillagers.hit"),
	SHOOT("securityvillagers.shoot"),
	TRADE("securityvillagers.trade"),
	
	ADMIN_BYPASS_FACTIONS("securityvillagers.admin.bypass.factions"),
	ADMIN_BYPASS_MUTE("securityvillagers.admin.bypass.mute"),
	ADMIN_CHANGEAGE("securityvillagers.admin.changeage"),
	ADMIN_HELP("securityvillagers.admin.help"),
	ADMIN_PROFESSION("securityvillagers.admin.profession"),
	ADMIN_RELOAD("securityvillagers.admin.reload"),
	ADMIN_RENAME("securityvillagers.admin.rename"),
	ADMIN_SELECTION("securityvillagers.admin.selection"),
	ADMIN_UPDATES("securityvillagers.admin.updates");
	
	private final String perm;
	SVPermission(String t) {
		perm = t;
	}
	
	@Override
	public String toString() {
		return perm;
	}
}
