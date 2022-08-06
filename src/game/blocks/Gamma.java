package game.blocks;

import java.awt.Color;
import java.awt.Polygon;

import engine.math.Vector2f;
import engine.tile.TileSettings;

public class Gamma extends Block{
	
	public Gamma(int xCoord, int yCoord) {
		super(new Vector2f[] {
				new Vector2f(0,0),
				new Vector2f(1,0),	
				new Vector2f(-1,0),	
				new Vector2f(-1,-1),
		}, new Vector2f(xCoord, yCoord), Color.blue);
	}

	@Override
	protected Polygon initGhost() {
		int w = TileSettings.TILEWIDTH/2;
		int h = TileSettings.TILEHEIGHT/2;
		Polygon ghost = new Polygon(
				new int[] {
						-w,3*w,3*w,-3*w,-3*w,-w
				},
				new int[] {
						-h,-h,h,h,-3*h,-3*h,
				}, 6);
		return ghost;
	}
}
