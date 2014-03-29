package entities.buildings;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import control.BuildingType;
import control.Tools;
import entities.GameObject;
import entities.GameObjectType;
import entities.stats.BaseStatsEnum;
import entities.stats.UnitStats;
import entities.units.Unit;

public abstract class Building extends GameObject {

	private int height; // height of the structure
	private int width; // width of the structure
	private long castleId = Tools.generateUniqueId();// this is how a building
														// knows what 'city' it
														// belongs to
	private BuildingType buildingType;
	private Queue<Unit> buildingQ = new LinkedList<Unit>();

	
    /** ResourceBuidling():
	 * Base constructor for Resource Type buildings, calls super building constructor and sets base amount of 
	 * resources. 
	 * 
	 * Parameters
	 * @param UUID id - the unique identifier for this Resource Building 
	 * @param int playerId - the id of the player who owns this resource building 
	 * @param baseStats - the basic stats for this building (ie health, capacity, etc.)
	 * @param new_stats - the current stats of this unit (less than or equal to baseStats generally)
	 * @param buildingType Type of building - 
	 * @param float xco - initial x coordinate
	 * @param float yco - initial y coordinate
	 * @param int height - how many tiles high building takes up 
	 * @param int width - how many tiles wide the building takes up
	 */
	public Building(UUID id, int playerId, BaseStatsEnum baseStats,
			UnitStats new_stats, GameObjectType gameObjectType,
			BuildingType buildingType, float xco, float yco, int height,
			int width) {
		super(id, playerId, baseStats, new_stats, gameObjectType, xco, yco);
		this.buildingType = buildingType;
		this.height = height;
		this.width = width;
		// super(p, baseStats, type, xco, yco);
		this.height = height;
		this.width = width;

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	/*
	 * Holding the Queue for the units that the building is responsible for
	 * producing. It can add units to its queue or remove finished units from
	 * its queue
	 */
	public boolean queueUnit(Unit u) {
		return buildingQ.offer(u);
	}

	public Unit dequeueUnit() {
		return buildingQ.poll();
	}

	public long getCastleId() {
		return castleId;
	}

	public boolean productionQueueEmpty() {

		return buildingQ.isEmpty();
	}

	public void advanceUnitProduction(int timestep) {
		// add timestep to each unit

	}

	public Unit getProducedUnit() {
		return buildingQ.poll();
	}

	public BuildingType getBuildingType() {

		return buildingType;
	}

}