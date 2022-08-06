package game.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import engine.math.Vector2f;
import engine.tile.RetroTile;
import engine.tile.Tile;
import engine.tile.TileSettings;

public abstract class Block {
	
	protected ArrayList<Tile> parts;
	protected int rotationState;
	protected Vector2f coord;
	protected Vector2f[] pointers;
	protected Color mainColor;
	protected Polygon ghost;
	protected Vector2f[] ghostVert;
	protected int ghostDeapth;
	
	protected Block(Vector2f[] pointers, Vector2f coord, Color mainColor) {
		this.pointers = pointers;
		this.mainColor = mainColor;
		this.parts = new ArrayList<>();
		this.coord = coord;
		for(int i = 0; i < pointers.length; i++) {
			parts.add(new RetroTile((int)(pointers[i].getX()+coord.getX()),
					(int)(pointers[i].getY()+coord.getY()), mainColor));
		}
		this.ghost = initGhost();
		this.ghostVert = new Vector2f[this.ghost.npoints];
		for(int i = 0; i < this.ghost.npoints; i++) {
			this.ghostVert[i] = new Vector2f(ghost.xpoints[i], ghost.ypoints[i]);
		}
		
		this.ghostDeapth = 0;
	}
	
	protected abstract Polygon initGhost();
	
	public void rotate() {
		for(Vector2f pointer: pointers) {
			pointer.rotateRad(-Math.PI/2);
			pointer.round();
		}
		for(int i = 0; i < parts.size(); i++) {
			parts.get(i).setCoord((int)(pointers[i].getX() + coord.getX()),
					(int)(pointers[i].getY() + coord.getY()));
		}
		
		for(Vector2f vec : ghostVert) {
			vec.rotateRad(-Math.PI/2);
		}
		applyGhostVert();
		
	}
	
	public void rotateRev() {
		for(Vector2f pointer: pointers) {
			pointer.rotateRad(Math.PI/2);
			pointer.round();
		}
		for(int i = 0; i < parts.size(); i++) {
			parts.get(i).setCoord((int)(pointers[i].getX() + coord.getX()),
					(int)(pointers[i].getY() + coord.getY()));
		}
		for(Vector2f vec : ghostVert) {
			vec.rotateRad(Math.PI/2);
		}
		applyGhostVert();
	}
	
	private void applyGhostVert() {
		for(int i = 0; i < ghost.npoints; i++) {
			this.ghost.xpoints[i] = (int)ghostVert[i].getX();
			this.ghost.ypoints[i] = (int)ghostVert[i].getY();
		}
	}
	
	public void determinePos() {
		moveY(ghostDeapth);
	}
	
	
	public boolean sideBorders() {
		for(int i = 0; i < parts.size(); i++) {
			if(parts.get(i).getXCoord() < 0 || parts.get(i).getXCoord() >= 10) {
				return true;
			}
		}
		return false;
	}
	
	public boolean grounded(int lines) {
		for(int i = 0; i < parts.size(); i++) {
			if(parts.get(i).getYCoord() >= lines) {
				return true;
			}
		}
		return false;
	}
	
	public void calcGhost(ArrayList<Tile> blocks, int lines) {
		synchronized (this) {
			int counter = 0;
			boolean done = false;
			while(true) {
				if(this.grounded(lines)) {
					this.ghostDeapth = counter;
					break;
				}
				for(int i = 0; i < blocks.size(); i++) {
					if(this.intersects(blocks.get(i))) {
						done = true;
						break;
					}
				}
				if(done)break;
				
				moveY(1);
				counter++;
			}
			this.ghostDeapth = counter-1;
			moveY(-counter);
		}
	}
	
	
	public void moveX(int xCoordOff) {
		for(Tile block: parts) {
			block.moveX(xCoordOff);
		}
		this.coord.addX(xCoordOff);
	}
	
	public void moveY(int yCoordOff) {
		for(Tile block: parts) {
			block.moveY(yCoordOff);
		}
		this.coord.addY(yCoordOff);
	}
	
	public boolean intersects(Tile tile) {
		for(Tile part: parts) {
			if(part.intersects(tile))
				return true;
		}
		return false;
	}
	
	public boolean intersects(int xCoord, int yCoord) {
		for(Tile part: parts) {
			if(part.intersects(xCoord, yCoord))
				return true;
		}
		return false;
	}
	
	
	public boolean intersects(Block block) {
		for(Tile part: parts) {
			if(block.intersects(part))
				return true;
		}
		return false;
	}
	
	public void render(Graphics2D g) {
		renderGhost(g);
		synchronized (this) {
			for(Tile part: parts) {
				if(part.inCanvas()) {
					part.render(g);
				}
			}
		}
	}
	
	public void justRender(Graphics2D g) {
		for(Tile part: parts) {
			part.render(g);
		}
	}
	
	public void renderGhost(Graphics2D g) {
		g.translate(coord.getX()*TileSettings.TILEWIDTH+16, coord.getY()*TileSettings.TILEHEIGHT+16 + this.ghostDeapth * TileSettings.TILEHEIGHT);
		g.setColor(mainColor);
		g.draw(ghost);
		g.setColor(new Color(mainColor.getRed(), mainColor.getGreen(), mainColor.getBlue(), 25));
		g.fill(ghost);
		g.translate(-(coord.getX()*TileSettings.TILEWIDTH+16), -(coord.getY()*TileSettings.TILEHEIGHT+16 + this.ghostDeapth * TileSettings.TILEHEIGHT));
	}
	
	public Vector2f getCoord() {
		return coord;
	}

	public Vector2f[] getPointers() {
		return pointers;
	}

	public Color getColor() {
		return mainColor;
	}

	public ArrayList<Tile> getParts() {
		return parts;
	}
	
	
	
}
