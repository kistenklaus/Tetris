package engine.tile;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColoredTile extends Tile{
	
	private Color color;
	
	public ColoredTile(int xCoord, int yCoord, Color color) {
		super(xCoord, yCoord);
		this.color = color;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect(super.xpos, super.ypos, super.width, super.height);
	}

}
