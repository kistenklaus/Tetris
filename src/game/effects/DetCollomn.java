package game.effects;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.gfx.Effect;
import engine.tile.TileSettings;

public class DetCollomn extends Effect{

	private int collomn, lines;
	private Color mainColor;
	
	public DetCollomn(int collomn, int lines, final Color mainColor) {
		super(50);
		this.collomn = collomn;
		this.lines = lines;
		this.mainColor = mainColor;
	}

	@Override
	public void tock() {
		
	}

	
	@Override
	public void render(Graphics2D g) {
		int w = TileSettings.TILEWIDTH;
		int h = TileSettings.TILEHEIGHT;
		g.setColor(new Color(mainColor.getRed(),mainColor.getGreen(),mainColor.getBlue(),
				(int)((alive/2f) * 255)));
		g.fillRect(w*collomn,0 , w, h*lines);
	}


}
