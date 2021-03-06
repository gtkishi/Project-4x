package diplomacy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import control.GameModel;
import control.Player;
import entities.diplomacy.trading.TradeFactory;
import entities.diplomacy.trading.TradeManager;
import entities.diplomacy.trading.interfaces.ITrade;
import entities.diplomacy.trading.interfaces.ITradeManager;
import entities.resources.Resources;

public class TestDiplomacyTrading {
	@Test
	public void test() {
		GameModel model = new GameModel();
		Player p1 = model.getPlayer(1);
		Player p2 = model.getPlayer(2);

		/* Set initial resources to 100 of each. */
		Resources initial = new Resources(100, 100, 100, 100, 100);
		p1.resources.receive(initial);
		p2.resources.receive(initial);

		ITradeManager tradeManager = new TradeManager(model);

		Resources r1 = new Resources(10, 0, 0, 0, 0);
		Resources r2 = new Resources(0, 0, 0, 0, 0);
		/*
		 * Create an Interval Trade that occurs every 1 seconds, for 5 seconds
		 * for a total of 5 trades. Create this trade between player1 and
		 * player2. Player1 trades r1 against Player2's r2.
		 */
		ITrade trade = TradeFactory.createIntervalResourceTrade(1, 5,
				p1.getId(), p2.getId(), r1, r2);
		/* Create the proposal */
		tradeManager.createProposal(trade);

		/* Call update a bunch of times, nothing should happen. */
		for (int i = 0; i < 500; i++) {
			tradeManager.update(0.5f);
		}
		/*
		 * Check if the Resources did not update, because the proposal has not
		 * been accepted yet.
		 */
		assertEquals(p1.resources.compareTo(initial), true);

		tradeManager.acceptProposal(trade);

		/* Update a bunch of times again. */
		for (int i = 0; i < 500; i++) {
			tradeManager.update(0.5f);
		}

		/*
		 * Check that p1's resources was updated 5times 10 Gold each time for a
		 * total loss of 50Gold.
		 */
		Resources compare = new Resources(50, 100, 100, 100, 100);
		assertEquals(p1.resources.compareTo(compare), true);

		/*
		 * Check that p2's resources increased to 150, the inverse change of p1,
		 * the other player in the trade.
		 */
		compare = new Resources(150, 100, 100, 100, 100);
		assertEquals(p2.resources.compareTo(compare), true);
	}

}
