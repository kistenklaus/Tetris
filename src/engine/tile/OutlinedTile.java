package engine.tile;

import java.awt.Color;
import java.awt.Graphics2D;

public class OutlinedTile extends Tile{

	private Color mainColor;
	private Color outlineColor;
	private int strokeWeight;
	
	public OutlinedTile(int xCoord, int yCoord, Color mainColor, Color outlineColor, int strokeWeight) {
		super(xCoord, yCoord);
		this.mainColor = mainColor;
		this.outlineColor = outlineColor;
		this.strokeWeight = strokeWeight;
	}

	@Override
	public void render(Graphics2D g) {
		
		g.setColor(this.outlineColor);
		g.fillRect(super.xpos, super.ypos, super.width, super.height);
		
		g.setColor(this.mainColor);
		
		g.fillRect(super.xpos+this.strokeWeight, super.ypos+this.strokeWeight,
				super.width-this.strokeWeight*2, super.height - this.strokeWeight*2);
		
	}

}
