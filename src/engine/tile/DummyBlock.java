package engine.tile;

import java.awt.Graphics2D;

public class DummyBlock extends Tile{

	public DummyBlock(int xCoord, int yCoord) {
		super(xCoord, yCoord);
	}

	@Override
	public void render(Graphics2D g) {}


}
