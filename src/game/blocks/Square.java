package game.blocks;

import java.awt.Color;
import java.awt.Polygon;

import engine.math.Vector2f;
import engine.tile.TileSettings;

public class Square extends Block{
	public Square(int xCoord, int yCoord) {
		super(new Vector2f[] {
				new Vector2f(0,0),
				new Vector2f(1,0),	
				new Vector2f(0,-1),	
				new Vector2f(1,-1),	
		}, new Vector2f(xCoord, yCoord), Color.yellow);
	}
	
	/**
	 * implies that the Square cannt rotate
	 */
	public void rotate() {	}

	@Override
	protected Polygon initGhost() {
		int w = TileSettings.TILEWIDTH/2;
		int h = TileSettings.TILEHEIGHT/2;
		Polygon ghost = new Polygon(
				new int[] {
						-w, w*3, w*3, -w
				},
				new int[] {
						-h*3, -h*3, h, h
				}, 4);
		
		return ghost;
	}
	
}
