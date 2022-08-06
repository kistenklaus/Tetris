package engine.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class RetroTile extends Tile {

	private Color[] partColor;
	private int strokeWeight;
	private Polygon[] parts;

	public RetroTile(int xCoord, int yCoord, Color mainColor) {
		super(xCoord, yCoord);
		this.partColor = new Color[] {
			mixColor(mainColor, Color.white, 0.65f),
			mixColor(mainColor, Color.white, 0.5f),
			mainColor,
			mixColor(mainColor, Color.black, 0.25f),
			mixColor(mainColor, Color.black, 0.5f)
		};

		this.strokeWeight = width / 5;
		this.parts = new Polygon[] {
				new Polygon(
						new int[] { 0, width, width - strokeWeight, strokeWeight },
						new int[] { 0, 0, strokeWeight, strokeWeight },
						4),
				new Polygon(
						new int[] { 0, 0, strokeWeight, strokeWeight },
						new int[] { 0, height, height-strokeWeight, strokeWeight },
						4),
				new Polygon(
						new int[] { strokeWeight, width-strokeWeight, width-strokeWeight, strokeWeight },
						new int[] { strokeWeight, strokeWeight, height-strokeWeight, height-strokeWeight },
						4),
				new Polygon(
						new int[] { width-strokeWeight, width-strokeWeight, width, width },
						new int[] { strokeWeight, height-strokeWeight, height, 0 },
						4),
				new Polygon(
						new int[] { strokeWeight, 0, width, width-strokeWeight },
						new int[] { height-strokeWeight, height, height, height-strokeWeight },
						4) };

		for (Polygon part : parts) {
			part.translate(super.xpos, super.ypos);
		}

	}
	
	private Color mixColor(Color mix1, Color mix2, float ratio) {
		ratio = 1-ratio;
		int red = (int)(mix1.getRed() * ratio + mix2.getRed() * (1-ratio));
		int green = (int)(mix1.getGreen() * ratio + mix2.getGreen() * (1-ratio));;
		int blue = (int)(mix1.getBlue() * ratio + mix2.getBlue() * (1-ratio));;
		
		
		return new Color(red, green, blue);
	}

	@Override
	public void render(Graphics2D g) {
		for(int i = 0; i < this.parts.length; i++) {
			g.setColor(partColor[i]);
			g.fill(this.parts[i]);
		}
		
	}
	
	public void moveX(int xCoordOff) {
		for(Polygon part : parts) {
			part.translate(xCoordOff * super.width, 0);
		}
		super.moveX(xCoordOff);
	}
	
	public void moveY(int yCoordOff) {
		for(Polygon part : parts) {
			part.translate(0, yCoordOff * height);
		}
		super.moveY(yCoordOff);
	}
	public void setCoord(int xCoord, int yCoord) {
		for(Polygon part : parts) {
			part.translate(-super.xpos, -super.ypos);
		}
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.xpos = xCoord *width;
		this.ypos = yCoord *height;
		for(Polygon part : parts) {
			part.translate(super.xpos, super.ypos);
		}
	}

}



















