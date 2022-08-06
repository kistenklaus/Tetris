package game.blocks;

import java.awt.Color;
import java.awt.Polygon;

import engine.math.Vector2f;
import engine.tile.TileSettings;

public class Stick extends Block{
	
	private boolean rotBool;
	
	public Stick(int xCoord, int yCoord) {
		super(new Vector2f[] {
				new Vector2f(0,0),
				new Vector2f(1,0),	
				new Vector2f(-1,0),	
				new Vector2f(-2,0),
		}, new Vector2f(xCoord, yCoord), Color.cyan);
		this.rotBool = false;
	}
	
	public void rotate() {
		this.rotBool = !rotBool;
		if(rotBool) {
			super.rotateRev();
		}else {
			super.rotate();
		}
		
	}

	@Override
	protected Polygon initGhost() {
		int w = TileSettings.TILEWIDTH/2;
		int h = TileSettings.TILEHEIGHT/2;
		Polygon ghost = new Polygon(
				new int[] {
					-w*5, w*3, w*3, -w*5	
				},
				new int[] {
					-h, -h, h, h
				}, 4);
		return ghost;
	}
}
