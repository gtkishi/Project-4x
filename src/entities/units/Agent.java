package entities.units;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fourx.buffs.UnitType;

import control.CommandQueue;
import control.Player;
import entities.BaseStatsEnum;

public class Agent extends Unit {
	private Map<String, Unit> underlings;
	private CommandQueue cq;

	public Agent(Player p) {
		super(p, BaseStatsEnum.FOOTMAN, UnitType.INFANTRY, 0, 0);
		underlings = new HashMap<String, Unit>();
		cq = new CommandQueue();
	}

}
