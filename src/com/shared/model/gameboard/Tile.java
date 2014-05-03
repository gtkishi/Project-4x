package com.shared.model.gameboard;

import java.io.Serializable;
import java.util.ArrayList;

import com.shared.model.Terrain;
import com.shared.model.buildings.Building;
import com.shared.model.control.Player;
import com.shared.model.units.Unit;

/**
 * 
 * @author Benjamin Deininger, Travis Strattom, and Nicholas Topping
 * @class Tile
 * @summary Tile class is the basic game space that units and buildings will
 *          populate. Tiles can be of different terrain types (see
 *          Terrain.java), contain various resources (see Resource.java), impede
 *          the movement of units, etc. The Tile class is used to populate a
 *          GameBoard object, in order to create the field of play for a 4X
 *          Game.
 */
public class Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707530827818938579L;
	private boolean passable; // Boolean representing whether or not units can
								// pass over/through a terrain tile.
	private Resource resource; // Resource that populates this tile.

	private float height; // Height generated from the diamondSquareGenerator in
							// GameBoard
	private Player owner; // The Player that contains possession of the tile,
							// though mostly it will not have an ownder
	private Terrain terrain; // The type of terrain for this tile, based upne
								// height.
	private boolean occupiedByBuilding = false; // keeps track of whether or not
												// tile is occupied by buliding.

	private float xco; // xcoordinate of upper left hand corner of tile
	private float yco; // y coordinate of upper left hand corner of tile

	private Building buildingRef = null;
	private ArrayList<Unit> unitsOnTile;


	/**
	 * Tile(): Description: Constructor for Tile Object. Determines the terrain
	 * type based on heightMap, and assigns resource.
	 * 
	 * Parameters:
	 * 
	 * @param Resource r - the resource to assign (generally NONE for
	 * constructor)
	 * 
	 * @param float heightMap - the height of this specific tile, determines the
	 * terrain type.
	 * 
	 * @param float xco - xcoordinate of upper left hand corner
	 * 
	 * @param float yco - ycoordinate of upper left hand corner
	 * 
	 * Return Value:
	 * 
	 * @return a new Tile object.
	 */
	public Tile(Resource r, float heightMap, float xco, float yco) {
		height = heightMap;

		resource = r;


		owner = null;
		passable = true;
		terrain = calculateTerrainType(height);
		unitsOnTile = new ArrayList<Unit>();
	}
	
	public Tile() {}


	/**
	 * isOccupiedByBuilding(): Description: returns whether or not tile is
	 * occupied by buliding
	 * 
	 * Return Value:
	 * 
	 * @return boolean value; true if occupied, false if not.
	 */
	public boolean isOccupiedByBuilding() {
		return occupiedByBuilding;
	}

	/**
	 * isPassable() Description: Returns whether or not this tile is passable by
	 * a unit. I.E. if the tile contains a wall/natural wall, then it will not
	 * be passable.
	 * 
	 * Return Value:
	 * 
	 * @return a boolean; true if passable, false if not.
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * hasOwner()
	 * determines whether this tile is currently owned by a player or not. 
	 * possibly @deprecated. 
	 * @return true if owned by a player, false if not. 
	 */
	public boolean hasOwner() {
		if (owner == null)
			return false;
		else
			return true;
	}

	/**
	 * getResource(): Description: Returns the type of resource located on this
	 * tile.
	 * 
	 * Return Value:
	 * 
	 * @return REesource enum, refer to Resource.java for possible types.
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * getXCoordinate()
	 * returns the x coordinate of this tile in the GameBoard it is on.
	 * @return float for xco of upper left hand corner.
	 */
	public float getXCoordinate() {
		return xco;
	}

	/**
	 * getYCoordinate()
	 * returns the y coordinate of this tile in the GameBoard it is on.
	 * @return float for y co of upper lefthand corner. 
	 */
	public float getYCoordinate() {
		return yco;
	}

	/**
	 * getHeight(): Description: Returns the height value of this tile, which
	 * was generated by the diamondSquareGenerator method in GameBoard
	 * 
	 * Return Value:
	 * 
	 * @return the height (float) value.
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @summary - return the current building occupying tile
	 * @return buildingRef
	 */
	public Building getBuilding() {
		return buildingRef;
	}

	/*
	 * getOwner() Description: Returns the current owner of the tile
	 * 
	 * Return Value:
	 * 
	 * @return Player object that is the current owner (may be null)
	 */
	public Player getOwner() {

		return owner;
	}

	/**
	 * getTerrainType(): Description: Returns the terrain type of this tile.
	 * 
	 * Return Value:
	 * 
	 * @return current value of terrain.
	 */
	public Terrain getTerrainType() {
		return terrain;
	}

	/**
	 * setTerrainType():
	 * Changes the terrain type of this tile, also modifies the height
	 * of the tile to reflect the new terrain. Method mainly used 
	 * when wanting to adjust terrain to make it more cooperative with 
	 * the tiles
	 * @param t - new terrain type to set. 
	 */
	public void setTerrainType(Terrain t)
	{	
		this.terrain = t;
		// need to adjust height to reflect new terrain type
		switch (t)
		{
		case WATER:
			this.height = (float)(131/255);
			break;
		case SAND:
			this.height = (float)(135/255);
			break;
		case GRASS:
			this.height = (float)(139/255);
			break;
		case FOREST:
			this.height = (float)(155/255);
			break;
		case MOUNTAIN:
			this.height = (float)(183/255);
			break;
		case SNOW:
			this.height = (float)(200/255);
			break;
		}
	}
	
	/**
	 * setPassable(): Description: Sets the passable value of the tile. I.E. If
	 * player places a building or wall on a tile, it will become impassable.
	 * 
	 * Return Value:
	 * 
	 * @return void.
	 */
	public void setPassable(boolean b) {
		passable = b;
	}

	/**
	 * setResource(): Description: Sets the resource of the tile, only possible
	 * if tile is passable and contains no resource.
	 * 
	 * Parameters:
	 * 
	 * @param Resource r - type of resource to set tile to
	 * 
	 * Return Value:
	 * 
	 * @return a boolean value; true if possible to set resource, false if not.
	 * If false returned, Tile's resource was not set to new value.
	 */
	public boolean setResource(Resource r) {
		if (!this.passable || this.resource != Resource.NONE) {
			return false;
		}
		this.resource = r;

		return true;
	}

	/**
	 * setOwner() Description: Sets the owner of this tile, i.e. if a player
	 * places a structure on a tile, it becomes their's. Can only set owner if
	 * setting to null, p is equal to current owner, or current owner is null.
	 * 
	 * Parameters:
	 * 
	 * @param Player p - Player who is new owner OR null.
	 * 
	 * Return Value:
	 * 
	 * @return boolean value; true if successfully set player, false if not
	 */
	public boolean setOwner(Player p) {
		if (p == null) {
			owner = p;
			return true;
		} else if (owner != null && !owner.equals(p)) {
			return false; // currently has an owner, that is not p.
		} else {
			owner = p; // tile is null, XOR owner == p.
			return true;
		}

	}

	/**
	 * setIsOccupiedByBuilding()
	 * sets whether or not it is true fi this tile is occupied by a building
	 * @param t - value to set occupiedByBuilding to. 
	 */
	public void setIsOccupiedByBuilding(boolean t) {
		occupiedByBuilding = t;
	}

	/*
	 * 1391895502278 - good seed for terrain generation.
	 */

	/**
	 * calculateTerrainType(): Description: Determines the terrain type based on
	 * the height value passed to the function. Currently can generate Water,
	 * Dirt, Grass, Hull, Mountain, and Snow terrain. TODO might be good to add
	 * some sort of safeguard to protect against 'WaterWorld' scenarios.
	 * 
	 * Parameters:
	 * 
	 * @param float height - height of terrain to generate
	 * 
	 * Return Value:
	 * 
	 * @return Terrain type that correspondes with given height.
	 */
	public Terrain calculateTerrainType(float height) {

		float newHeight = 255 * height;

		if (newHeight < 132) {
			return Terrain.WATER;
		} else if (newHeight < 136) {
			return Terrain.SAND;
		} else if (newHeight < 140 + 5) {
			return Terrain.GRASS;
		} else if (newHeight < 156 + 10) {
			return Terrain.FOREST;
		} else if (newHeight < 184 + 5) {
			return Terrain.MOUNTAIN;
		} else {
			return Terrain.SNOW;
		}
	}
	
	/**
	 * setBuildingRef(): Description Sets the new building reference for this
	 * tile (allows tile to see what building is on it) Will also change the
	 * value of occupiedByBuilding. This function will fail if there is
	 * currently a building on the tile (will return false to indicate so)
	 * 
	 * Parameters:
	 * 
	 * @param nb
	 *            - new building reference
	 * 
	 *            Return Value:
	 * @return True if buildingReference successfully changed, false if not.
	 */

	/** @deprecated 
	 * public boolean setBuilding(Building nb) { // cannot setBuilding to null,
	 * use remove building to do so if (nb == null) { return false; } else if
	 * (buildingRef == null) { buildingRef = nb; occupiedByBuilding = true;
	 * return true; } else { return false; // building already occupies this
	 * tile } }
	 */

	/**
	 * removeBuilding(): Description: removes the current building reference
	 * (changes occupiedByBuilding to reflect this)
	 * 
	 * Return Value:
	 * 
	 * @return True if building was removed (buildingRef was not null before
	 *         calling), false if not
	 */
	public boolean removeBuilding() {
		// cannot remove a building that doesn't exist
		if (buildingRef == null) {
			return false;
		} else // building exists, remove it from this tile
		{
			buildingRef = null;
			occupiedByBuilding = false;
			return true;
		}
	}

	/**
	 * addUnit()
	 * adds a unit to this tile. possibly @deprecated
	 * @param u - unit to add to tile. 
	 */
	public void addUnit(Unit u) {
		unitsOnTile.add(u);
	}

	/**
	 * removeUnit()
	 * @param u - unit to remove from this tile
	 * @return true if unit was successfully removed, false if not. 
	 */

	public boolean removeUnit(Unit u) {
		return unitsOnTile.remove(u);
	}
}
