package com.alessiodp.securityvillagers.api.enums;

public enum AttackResult {
	/**
	 * The attack is not protected
	 */
	SUCCESS,
	/**
	 * The melee attack is protected
	 */
	HIT,
	/**
	 * The ranged attack is protected
	 */
	SHOOT;
	
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
