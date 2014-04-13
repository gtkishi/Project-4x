package entities.buildings;

import java.util.UUID;

import entities.resources.Resources;
import entities.stats.BaseStatsEnum;

/**
 * LumberMill
 * 
 * @author NR Top The lumberMill allows the user to generate wood resources
 */
public class LumberMill extends Building implements ResourceBuilding {

	/**
	 * LumberMill() creates a new lumber mill object
	 * 
	 * @param id
	 *            - unique id for this lumber mill
	 * @param playerId
	 *            - id of player who owns this lumber mill
	 * @param xco
	 *            - x coordinate of this mill
	 * @param yco
	 *            - y coordinate of this mill
	 */

	protected Resources baseResourceAmount;
	protected Resources resourceAmount;

	// Global resource rate modifier that affects all resource buildings

	/**
	 * Farm() creates a new farm, must be placed on a food tile.
	 * 
	 * @param id
	 *            - UUID of this building
	 * @param playerId
	 *            - id of the player who own's this building
	 * @param xco
	 *            - x coordinate of the building
	 * @param yco
	 *            - y coordinate of the building
	 */
	public LumberMill(UUID id, int playerId, float xco, float yco) {
		super(id, playerId, BaseStatsEnum.LUMBER_MILL, BaseStatsEnum.LUMBER_MILL.getStats(),
				BuildingType.LUMBER_MILL, xco, yco, 1, 1);
		baseResourceAmount = new Resources(0, 1000, 0, 0, 0);
		resourceAmount = new Resources(0, 10, 0, 0, 0);
	}

	public Resources generateResource() {

		return resourceAmount;
	}

	@Override
	public void advanceResourceProduction() {

		if (baseResourceAmount.spend(0, 10, 0, 0, 0)) {

			this.getPlayer().addResources(resourceAmount);
		}

		return;
	}

	public Resources getBaseResources() {

		return baseResourceAmount;
	}

}
