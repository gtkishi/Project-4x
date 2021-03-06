package control.commands;

import java.util.UUID;

import control.Factory;
import control.GameModel;
import control.Player;
import entities.buildings.Building;
import entities.units.Unit;
import entities.units.UnitType;

public class BuildingProductionCommand implements Command {
	private int playerId;
	private UUID buildingId;
	private UnitType unitType;

	public BuildingProductionCommand(int playerId, UUID buildingId,
			UnitType unitType) {
		this.playerId = playerId;
		this.buildingId = buildingId;
		this.unitType = unitType;
	}

	@Override
	public boolean validateCommand(GameModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean performCommand(GameModel model) {
		// Find the building's location so we can spawn a unit there
		Player player = model.getPlayer(playerId);
		Building thisBuilding = player.getGameObjects().getBuildings()
				.get(buildingId);

		Unit u = Factory.buildUnit(player, playerId, unitType,
				thisBuilding.getX(), thisBuilding.getY());

		// Add this unit to the player's queue
		thisBuilding.queueUnit(u);

		return false;
	}

}
