package com.fourx.research.technologies;

import java.util.ArrayList;

import com.fourx.buffs.BuffStats;
import com.fourx.buffs.TYPE;
import com.fourx.research.Technology;
import com.fourx.research.TechnologyEnum;

public class InfantryDamage1 extends Technology {

	public InfantryDamage1() {
		super();
		buffstats = new BuffStats();
		buffstats.damage = 1;
		appliesTo = new ArrayList<TYPE>();
		appliesTo.add(TYPE.INFANTRY);
	}
}
