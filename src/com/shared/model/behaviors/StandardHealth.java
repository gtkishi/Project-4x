package com.shared.model.behaviors;

public class StandardHealth implements Attackable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -916977649619581882L;
	private float hp; // Number of hit points
	private float maxHP; // Number of maximum hit points
	private int damageToTake; // Amount of damage to take during next simulation step
	private int armor;
	private float healthRegen;
	private boolean isDead = false;
	
	public StandardHealth() {
		this.maxHP = 10;
		this.hp = maxHP;
		this.armor = 0;
	}
	
	public StandardHealth( float maxHP, float healthRegen, int armor ) {
		this.maxHP = maxHP;
		this.hp = this.maxHP;
		this.armor = armor;
	}
	
	public void takeDamage( int damage ) {
		damageToTake += damage - armor;
	}
	
	@Override
	public void simulateDamage(int timeStep) {
		// TODO Auto-generated method stub
		if(!isDead) {
			hp += healthRegen * timeStep / 1000;
			hp -= damageToTake;
			if( hp <= 0 ) {
				hp = 0;
				isDead = true;
			}
			damageToTake = 0;
		}

	}

	@Override
	public boolean isDead() {
		return isDead;
	}


}