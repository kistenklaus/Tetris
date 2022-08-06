package engine.tile;

import java.awt.Graphics2D;

public abstract class Tile {
	
	protected int xCoord, yCoord;
	protected int xpos, ypos;
	protected int width, height;
	
	protected Tile(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = TileSettings.TILEWIDTH;
		this.height = TileSettings.TILEHEIGHT;
		this.xpos = xCoord *width;
		this.ypos = yCoord *height;
	}
	
	/**
	 * Implies a intersection calculation based on the tileCoord
	 * @exception tiles must be created with the same TileSettings
	 * @param tile intersection partner
	 * @return true if there is a intersection
	 */
	public boolean intersects(Tile tile) {
		if(this.xCoord == tile.getXCoord() &&
				this.yCoord == tile.getYCoord()) {
			return true;
		}
		return false;
	}
	public boolean intersects(int xCoord, int yCoord) {
		if(this.xCoord == xCoord &&
				this.yCoord == yCoord) {
			return true;
		}
		return false;
	}
	
	public boolean inCanvas() {
		return (yCoord > -1 && xCoord > -1);
	}
	
	public abstract void render(Graphics2D g);
	
	public void moveX(int xCoordOff) {
		this.xCoord += xCoordOff;
		this.xpos = width *xCoord;
	}
	public void moveY(int yCoordOff) {
		this.yCoord += yCoordOff;
		this.ypos = height *yCoord;
	}
	
	public int getXCoord() {
		return this.xCoord;
	}
	public int getYCoord() {
		return this.yCoord;
	}
	public void setCoord(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.xpos = xCoord *width;
		this.ypos = yCoord *height;
	}
}
