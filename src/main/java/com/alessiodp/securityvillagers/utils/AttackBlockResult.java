package com.alessiodp.securityvillagers.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;

public class AttackBlockResult {
	@Getter @Setter private Entity damager;
	@Getter @Setter private AttackResult result;
	
	public AttackBlockResult() {
		damager = null;
		result = AttackResult.SUCCESS;
	}
	
	public enum AttackResult {
		SUCCESS, HIT, SHOOT;
		
		public boolean isSuccess() {
			return this.equals(SUCCESS);
		}
		public boolean isHit() {
			return this.equals(HIT);
		}
		public boolean isShoot() {
			return this.equals(SHOOT);
		}
	}
}
