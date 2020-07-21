package com.alessiodp.securityvillagers.common.utils;

import com.alessiodp.core.common.commands.utils.ADPPermission;

public enum SecurityVillagersPermission implements ADPPermission {
	
	USER_EGG				("securityvillagers.user.egg"),
	USER_HIT				("securityvillagers.user.hit"),
	USER_SHOOT				("securityvillagers.user.shoot"),
	USER_TRADE				("securityvillagers.user.trade"),
	
	ADMIN_ALERTS			("securityvillagers.admin.alerts"),
	ADMIN_BYPASS_MUTE		("securityvillagers.admin.bypass.mute"),
	ADMIN_CHANGEAGE			("securityvillagers.admin.changeage"),
	ADMIN_CHANGEAGE_CD_BYPASS ("securityvillagers.admin.changeage.cooldown.bypass"),
	ADMIN_HELP				("securityvillagers.admin.help"),
	ADMIN_PROFESSION		("securityvillagers.admin.profession"),
	ADMIN_PROFESSION_CD_BYPASS ("securityvillagers.admin.profession.cooldown.bypass"),
	ADMIN_PROTECT			("securityvillagers.admin.protect"),
	ADMIN_RELOAD			("securityvillagers.admin.reload"),
	ADMIN_RENAME			("securityvillagers.admin.rename"),
	ADMIN_SELECT			("securityvillagers.admin.select"),
	ADMIN_VERSION			("securityvillagers.admin.version");
	
	private final String perm;
	SecurityVillagersPermission(String t) {
		perm = t;
	}
	
	@Override
	public String toString() {
		return perm;
	}
}
