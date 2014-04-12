package entities.buildings;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import control.Player;
import entities.resources.Resources;

public class TestBuildings {

	@Test
	public void testBrk() {
		// TODO Auto-generated method stub
		Player p = new Player("Xu", 111);
		Bank bk = new Bank(new UUID(0, 0), 1, 1, 1, null);
		bk.gold = 100000;
		p.resources.setGold(0);
		// bk.withdraw(1000);
		assertEquals(p.resources.getGold(), 1000);
		assertEquals(bk.gold, 99000);
		// bk.withdraw(11000);
		assertEquals(p.resources.getGold(), 12000);
		assertEquals(bk.gold, 88000);
		// assertEquals(bk.withdraw(100000),false);
		assertEquals(p.resources.getGold(), 12000);
		// bk.deposit(5000);
		assertEquals(p.resources.getGold(), 7000);
		// assertEquals(bk.deposit(50000),false);
		assertEquals(p.resources.getGold(), 7000);

	}

	@Test
	public void testResourceBuildings() {
		Player p = new Player("Xu", 111);
		Farm farm = new Farm(new UUID(0, 0), 1, 1, 1);
		farm.setOwner(p);
		assertEquals(0, p.getResources().getFood());
		assertEquals(100, farm.getBaseResources().getFood());
				
		farm.advanceResourceProduction();
		
		assertEquals(10, p.getResources().getFood());
		assertEquals(90, farm.getBaseResources().getFood());
				
		farm.advanceResourceProduction();
		
		assertEquals(20, p.getResources().getFood());
		assertEquals(80, farm.getBaseResources().getFood());
				
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		farm.advanceResourceProduction();
		
		assertEquals(100, p.getResources().getFood());
		assertEquals(0, farm.getBaseResources().getFood());

		farm.advanceResourceProduction();
		
		assertEquals(100, p.getResources().getFood());
		assertEquals(0, farm.getBaseResources().getFood());
				

		
	}

}
