package com.fourx.research;

import com.fourx.research.technologies.*;

public enum TechnologyEnum {
	INFANTRYDAMAGE1(new InfantryDamage1()), INFANTRYDAMAGE2(new InfantryDamage2());
	
	private Technology value;
	private TechnologyEnum(Technology tech) {
		value = tech;
	}
	
	public Technology getValue() {
		return value;
	}
}
