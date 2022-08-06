package game;

import java.awt.Graphics2D;

import engine.Engine;
import engine.GameContainer;
import engine.gfx.EffectMap;

public class Tetris extends GameContainer{
	
	private TetrisField tfield;
	private Background bg;
	private EffectMap effectmap;
	private ScoreMenue scoremenue;
	@Override
	public void init() {
		this.effectmap = new EffectMap();
		this.tfield = new TetrisField(Engine.WIDTH/3, Engine.HEIGHT-64, super.getInput(), this.effectmap);
		this.bg = new Background(Engine.WIDTH, Engine.HEIGHT);
		this.scoremenue = new ScoreMenue(Engine.WIDTH, Engine.HEIGHT, tfield);
	}

	@Override
	public void tick() {
		this.tfield.tick();
		this.effectmap.tick();
		this.scoremenue.tick();
	}

	@Override
	public void render(Graphics2D g) {
		bg.renderLayer0(g);
		
		g.translate(Engine.WIDTH/6*2, 32);
		this.effectmap.render(g);
		this.tfield.render(g);
		g.translate(-Engine.WIDTH/6*2, -32);
		
		g.translate(Engine.WIDTH/3*2, 32);
		this.scoremenue.render(g);
		g.translate(Engine.WIDTH/3*2, -32);
		
	}
	
	
	
	public static void main(String[] args) {
		Engine engine = new Engine("Tetris", 960, 640, new Tetris(), 0.01d);
		engine.init();
		engine.start();
	}
	
}
