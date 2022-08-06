package game.blocks;

import java.awt.Color;
import java.awt.Polygon;

import engine.math.Vector2f;
import engine.tile.TileSettings;

public class InvSkew extends Block{

	public InvSkew(int xCoord, int yCoord) {
		super(new Vector2f[] {
			new Vector2f(0,0),
			new Vector2f(-1,0),	
			new Vector2f(0,-1),	
			new Vector2f(1,-1),		
		}, new Vector2f(xCoord, yCoord), Color.green);
	}

	@Override
	protected Polygon initGhost() {
		int w = TileSettings.TILEWIDTH/2;
		int h = TileSettings.TILEHEIGHT/2;
		Polygon ghost = new Polygon(
				new int[] {
						-w,-w,w*3,w*3,w,w,-w*3,-w*3
				},
				new int[] {
						-h,-h*3,-h*3,-h,-h,h,h,-h
				}, 8);
		
		return ghost;
	}

}
