package engine.gfx;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EffectMap {
	
	ArrayList<Effect> effects;
	
	public EffectMap() {
		this.effects = new ArrayList<>();
	}
	
	
	public void tick() {
		for(int i = 0; i < effects.size(); i++) {
			this.effects.get(i).tick();
			if(!this.effects.get(i).isAlive()) {
				this.effects.remove(i);
				i--;
			}
		}
	}
	
	
	public void add(Effect effect) {
		this.effects.add(effect);
	}
	
	
	public void render(Graphics2D g) {
		for(int i = 0; i < effects.size(); i++) {
			this.effects.get(i).render(g);
		}
	}



	
}
