package com.alessiodp.securityvillagers.common.villagers.objects;

import com.alessiodp.securityvillagers.common.configuration.data.ConfigMain;
import lombok.Getter;

public enum VillagerProfession {
	ARMORER,
	BUTCHER,
	CARTOGRAPHER,
	CLERIC,
	FARMER,
	FISHERMAN,
	FLETCHER,
	LEATHERWORKER,
	LIBRARIAN,
	MASON,
	NITWIT,
	NONE,
	SHEPHERD,
	TOOLSMITH,
	WEAPONSMITH;
	
	@Getter private String name;
	
	VillagerProfession() {
		name = "";
	}
	
	public static void setup() {
		VillagerProfession.ARMORER.name = ConfigMain.PROFESSION_TYPE_ARMORER;
		VillagerProfession.BUTCHER.name = ConfigMain.PROFESSION_TYPE_BUTCHER;
		VillagerProfession.CARTOGRAPHER.name = ConfigMain.PROFESSION_TYPE_CARTOGRAPHER;
		VillagerProfession.CLERIC.name = ConfigMain.PROFESSION_TYPE_CLERIC;
		VillagerProfession.FARMER.name = ConfigMain.PROFESSION_TYPE_FARMER;
		VillagerProfession.FISHERMAN.name = ConfigMain.PROFESSION_TYPE_FISHERMAN;
		VillagerProfession.FLETCHER.name = ConfigMain.PROFESSION_TYPE_FLETCHER;
		VillagerProfession.LEATHERWORKER.name = ConfigMain.PROFESSION_TYPE_LEATHERWORKER;
		VillagerProfession.LIBRARIAN.name = ConfigMain.PROFESSION_TYPE_LIBRARIAN;
		VillagerProfession.MASON.name = ConfigMain.PROFESSION_TYPE_MASON;
		VillagerProfession.NITWIT.name = ConfigMain.PROFESSION_TYPE_NITWIT;
		VillagerProfession.NONE.name = ConfigMain.PROFESSION_TYPE_NONE;
		VillagerProfession.SHEPHERD.name = ConfigMain.PROFESSION_TYPE_SHEPHERD;
		VillagerProfession.TOOLSMITH.name = ConfigMain.PROFESSION_TYPE_TOOLSMITH;
		VillagerProfession.WEAPONSMITH.name = ConfigMain.PROFESSION_TYPE_WEAPONSMITH;
	}
	
	public static VillagerProfession getProfession(String name) {
		VillagerProfession ret = null;
		for (VillagerProfession profession : VillagerProfession.values()) {
			if (!profession.getName().isEmpty() && profession.getName().equalsIgnoreCase(name)) {
				ret = profession;
				break;
			}
		}
		return ret;
	}
}

