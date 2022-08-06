package engine.gfx;

import java.awt.Graphics2D;

public abstract class Effect {
	
	/**
	 * a float between 0 and 1 1 means alive 0 mean dead
	 */
	protected float alive, tickDelta;
	
	protected Effect(int ticks) {
		this.tickDelta = 1f/ ticks;
		this.alive = 1;
	}
	
	public abstract void tock();
	public abstract void render(Graphics2D g);
	
	public void tick() {
		tock();
		alive-=tickDelta;
	}
	
	public boolean isAlive() {
		return alive > 0;
	}
	
}
