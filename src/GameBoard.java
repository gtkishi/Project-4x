import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import control.Player;
import entities.Infantry;
import entities.Tile;
import entities.Unit;

/* 
 *  Programmer :  Ben Deininger
 * 		
 *  Purpose :  This class creates an instance of a game board, which is a 2d array of Tile objects.  A tile object will 
 *  			tentatively contain simple ownership and a resource.  There will be a collection of players for the board.
 *  			The constructor requires the row and column size and the number of players. 
 * 
 */

public class GameBoard {

	private Tile[][] map;
	private int rows;
	private int cols;
	private ArrayList<Player> players;
	private static Random rand = new Random();

	public GameBoard(int row, int col, int numPlayers) {
		rows = row;
		cols = col;
		map = new Tile[row][col];

		// create players

		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player("player" + i));
		}

		// create a noisemap
		System.out.println("Building noisemap...");
		long startTime = System.currentTimeMillis();
		float[][] noisemap = diamondSquareGenerator(row,
				System.currentTimeMillis(), 0.1f);
		long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

		// create tiles
		// needs optimization!
		System.out.println("Building Board...");
		startTime = System.currentTimeMillis();

		// create a random number generator
		Random rand = new Random();
		rand.setSeed(4);

		for (int c = 0; c < cols; c++) {
			for (int r = 0; r < rows; r++) {

				// give the tile a random resource number
				int resource = rand.nextInt(4);
				float height = noisemap[r][c];

				// create a new tile

				map[r][c] = new Tile(resource, height);
				System.out.printf("%3f ", noisemap[r][c]);
			}
			 System.out.println();
		}
		endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));

		// player creation
		Player player1 = new Player("p1");
		player1.setName("p2");

		// placing a unit
		Unit temp = new Infantry(player1);
		this.placeUnitAt(temp, 0, 0);

	}

	// -----------------------------------------------------------------------------------------
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;

	}

	public Tile getTileAt(int x, int y) {
		return map[x][y];
	}

	public void placeUnitAt(Unit u, int x, int y) {

		map[x][y].placeUnit(u);

	}

	/*
	 * Gabe Kishi - DiamondSquare Procedurally generates terrain using a height
	 * map produced by the Diamond Squares algorithm
	 * http://en.wikipedia.org/wiki/Diamond-square_algorithm
	 */

	public static float[][] diamondSquareGenerator(int SIZE, long seed,
			float roughness) {
		// size is the nearest power of 2 that fully contains SIZE plus 1
		int size = (1 << (int) Math.ceil(Math.log(SIZE) / Math.log(2))) + 1;

		// seed random number generator
		rand.setSeed(seed);
		float[][] noise = new float[size][size];

		// initialize the corner values
		noise[0][0] = rand.nextFloat();
		noise[size - 1][0] = rand.nextFloat();
		noise[0][size - 1] = rand.nextFloat();
		noise[size - 1][size - 1] = rand.nextFloat();

		// begin Diamond Squares iteration
		dsIter(noise, size, roughness);

		return noise;
	}

	private static void dsIter(float[][] noise, int size, float roughness) {
		int sideLength = size - 1;
		int sizeMinus = size - 1;
		float rough = roughness, avg;

		while (sideLength > 1) {
			int halfLen = sideLength / 2;

			// squares
			for (int x = 0; x < size - 1; x += sideLength)
				for (int y = 0; y < size - 1; y += sideLength) {
					avg = (noise[x][y] + noise[x + sideLength][y]
							+ noise[x][y + sideLength] + noise[x + sideLength][y
							+ sideLength]) / 4.0f;
					avg += 2 * rough * rand.nextFloat() - rough;
					noise[x + halfLen][y + halfLen] = avg;
				}

			float N, E, S, W;

			// diamonds
			
			
			
			/*
			 * height -> color type = terrain type
			 * 
			 * 
			 * for (int x = 0; x < SIZE; x++){
			for (int y = 0; y < SIZE; y++){
				int val = (int)(255*noise[x][y]);
				if (noise2[x][y] != 0)
					g.setColor(new Color(224, 224, 224));
				else
					g.setColor((
						val < 132 ? new Color(0, 0, 128) :
							val < 136 ? Color.BLUE : 
								val < 140 ? new Color (255, 255, 64) :
									val < 156 ? new Color(32, 110, 32) : 
										val < 184 ? new Color(17, 70, 17) :
													val < 202 ? new Color(76, 60, 22):
															val < 216 ? new Color(103, 80, 30): new Color(160, 120, 90)));
				g.fillRect(x, y, 1, 1);
			}
		}
			 * 
			 * 
			 */
			
			
			
			
			
			
			
			
			
			for (int x = 0; x < size - 1; x += halfLen)
				for (int y = (x + halfLen) % sideLength; y < size - 1; y += sideLength) {
					W = noise[(x - halfLen + sizeMinus) % sizeMinus][y];
					E = noise[(x + halfLen) % sizeMinus][y];
					N = noise[x][(y - halfLen + sizeMinus) % sizeMinus];
					S = noise[x][(y + halfLen) % sizeMinus];

					avg = (N + E + S + W) / 4.0f;
					avg += 2 * rough * rand.nextFloat() - rough;
					noise[x][y] = avg;
					if (x == 0)
						noise[size - 1][y] = avg;
					if (y == 0)
						noise[x][size - 1] = avg;
				}

			sideLength /= 2;
			rough *= .8;
		}
	}

}
