package com.shared.model.units;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.shared.model.control.CommandQueue;
import com.shared.model.control.Player;
import com.shared.model.entities.GameObjectType;
import com.shared.model.gameboard.GameBoard;
import com.shared.model.stats.BaseStatsEnum;
import com.shared.model.stats.UnitStats;

/**
 * 
 * @author NRTopping
 * @class Agent
 * @summary 
 * This class extends the Unit object. Agents act at their own digression, based on commands
 * given to by players. Agent class is not meant to be created on own, only classes that implement
 * Agent (hence why it is abstract). 
 * 
 */
public abstract class Agent extends Unit {
	private Map<String, Unit> underlings; // TODO possibly replace Unit or String with their unique ID's (UUID?)
	private CommandQueue cq; // list of current commnands to issue.
	
	/*
	 * Agent():
	 * Description:
	 * Base constructor for and Agent object. Calls the Unit constructor, 
	 * initializes underlings, and Command Queue. 
	 * 
	 * Parameters:
	 * @param UUID id - a unique id created by Java's UUID class
	 * @param int playerId - id of player that owns this agent
	 * @param BaseStatsEnum baseStats - base stats of a unit (Baseline for unit, if unit has no research/improvements, then unitstats = baseStats) 
	 * @param UnitStats new_stats - the altered stats of the unit (will be used for combat/gameplay) 
	 * @param GameObjectType type - the group that this object applies to (i.e. general groups such as building, unit, blah) 
	 * @param UnitType unitTYpe - more specific type within the group
	 * @param float xco - initial x coordinate of the unit
	 * @param float yco - initial y coordinate of the unit 
	 * 
	 */
	public Agent(UUID id, int playerId, BaseStatsEnum baseStats, UnitStats new_stats, GameObjectType type, UnitType unitType, float xco,
			float yco) {
		super(id, playerId,  baseStats, new_stats, unitType, xco, yco);
		underlings = new HashMap<String, Unit>();
		cq = new CommandQueue();
	}

	
	/*
	 * To be implemented by all agents, update the agents status 
	 * once every turn/couple of turns based on the status of the
	 * player and the GameBoard.
	 */
	public abstract void update(Player p, GameBoard gb);
}