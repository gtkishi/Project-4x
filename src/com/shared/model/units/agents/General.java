package com.shared.model.units.agents;

import com.shared.model.control.AIControl;
import com.shared.model.control.Player;
import com.shared.model.entities.GameObjectType;
import com.shared.model.gameboard.GameBoard;
import com.shared.model.stats.BaseStatsEnum;
import com.shared.model.stats.UnitStats;
import com.shared.model.units.Agent;
import com.shared.model.units.UnitType;

//queueing of commands for controller, controller checks queue each
//time segment, decides how many commands to execute. New commands
//added to end of queue

public class General extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8790107618851657532L;
	private int range;
	private AIControl brain;
	/**
	 * General():
	 * @param id       - unique id for this General object
	 * @param playerId - id of player that owns this general
	 * @param UnitStasts stats - stats this unit should currently have. 
	 * @param xco  - initial x coordinate on gameboard of general object. 
	 * @param yco  - initial y coordinate on gameboard of genera object. 
	 */
	public General(int id, int playerId, UnitStats new_stats, float xco, float yco) {
		super( id, playerId, BaseStatsEnum.GENERAL, new_stats, GameObjectType.UNIT, UnitType.GENERAL,  xco,
				 yco);
		// TODO Auto-generated constructor stub
	}
	
	public General() {}
	
	public String acceptCommandByPrestige(int i){
		switch(i/50){
		case(0):return "nothing";
		case(1):return "no";
		case(2):return "maybe no";
		case(3):return "yes";
		default:return "nothing";
		}
	}
	
	public String decision() {
		return "NO";
	}
	
	public int getPrestige(Player p){
		return p.prestige.get(this);
	}
	
	public void addPrestige(int i,Player p){
		p.prestige.put(this, this.getPrestige(p)+i);
	}
	// add unit to collection

	// make decision command

	// what options each general has:
	// rally troops, defensive position, attack, move, remove troops
	// add troops
	
	/*
	 * TODO add option to 'patrol' around a city(ies). 
	 * TODO add option to 'explore', causing general to lead troops in random 
	 *      unexplored direction 
	 * TODO add option to 'invade', if enemy city is known, use A*-esque algorithm to 
	 *      lead troops to city and invade.  
	 * TODO add option to 'defend', send troops to ward off enemies at a city (an ally's or your own)
	 */
	
	public void update(Player p, GameBoard gb)
	{
		// TODO implements
	}

}
