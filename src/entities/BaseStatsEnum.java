package entities;

import com.fourx.buffs.BuffStats;

public enum BaseStatsEnum {
	FOOTMAN(new UnitStats(5, 1, 1, .5f, 40f, 3, 1.5f));

	private UnitStats stats;

	private BaseStatsEnum(UnitStats stats) {
		this.stats = stats;
	}

	public UnitStats getStats() {
		return stats;
	}

	public UnitStats augment(UnitStats s, BuffStats t) {
		float current_max = 1f;
		float current_health = 1f;
		if (s != null) {
			current_max = s.max_health;
			current_health = s.health;
		}

		// when we research an upgrade, the new stats should be based off the
		// basestats and tech learned.
		s = t.augment(stats.clone());
		// keep the percentage life the same
		s.health = s.max_health * (current_health / current_max);

		return s;
	}
}
