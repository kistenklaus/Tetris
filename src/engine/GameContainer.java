package engine;

import java.awt.Graphics2D;

public abstract class GameContainer {
	
	private Input input;
	
	public abstract void init();
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput() {
		if(input == null) {
			System.err.println("\n --input is not init by the engine-- \n");
			throw new NullPointerException();
		}
		return input;
	}
	
}
