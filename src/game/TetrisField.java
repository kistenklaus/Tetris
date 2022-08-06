package game;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import engine.Input;
import engine.gfx.EffectMap;
import engine.sound.SoundClip;
import engine.tile.DummyBlock;
import engine.tile.Tile;
import engine.tile.TileSettings;
import game.blocks.Alpha;
import game.blocks.Block;
import game.blocks.Gamma;
import game.blocks.InvSkew;
import game.blocks.Logo;
import game.blocks.OutvSkew;
import game.blocks.Square;
import game.blocks.Stick;
import game.effects.DetCollomn;

public class TetrisField{
	
	private ArrayList<Tile> blocks;
	private Block activeBlock;
	private int tickCounter;
	private Input input;
	private EffectMap effectmap;
	private boolean rotationBool;
	private boolean leftBool;
	private boolean rightBool;
	private boolean downBool;
	private Block nextBlock;
	private int score, linesCleard;
	private SoundClip[] detSounds;
	
	private int lines;
	
	public TetrisField(int width, int height, Input input, EffectMap effectmap) {
		this.input = input;
		this.effectmap = effectmap;
		if(width%10 != 0)System.err.println("please pick a better width");
		if(height%(width/10f) != 0)System.err.println("please pick a better height");
		TileSettings.TILEWIDTH = width/10;
		TileSettings.TILEHEIGHT = width/10;
		
		this.lines = (int) (height / (width/10f));
		
		this.tickCounter = 0;
		this.rotationBool = true;
		this.leftBool = true;
		this.rightBool = true;
		this.downBool = true;
		
		this.score = 0;
		this.linesCleard = 0;
		
		this.activeBlock = newBlock();
		this.nextBlock = newBlock();
		
		this.blocks = new ArrayList<>();
		
		try {
			File souceFolder = new File("./res/audio/detSounds/");
			if(!souceFolder.isDirectory())throw new FileNotFoundException("The File is not a Dictonary");
			File[] data = souceFolder.listFiles();
			this.detSounds = new SoundClip[data.length];
			for(int i = 0; i < data.length; i++) {
				this.detSounds[i] = new SoundClip(data[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	public void tick() {
		tickCounter++;
		
		if(input.isKeyDown(Input.A) && leftBool) {
			this.leftBool = false;
			this.activeBlock.moveX(-1);
			if(this.activeBlock.sideBorders()) {
				this.activeBlock.moveX(1);
			}
			for(int i = 0; i < blocks.size(); i++) {
				if(this.activeBlock.intersects(blocks.get(i))) {
					this.activeBlock.moveX(1);
					break;
				}
			}
			
		}
		if(!(input.isKeyDown(Input.A))) {
			this.leftBool = true;
		}
		if(input.isKeyDown(Input.D) && rightBool) {
			this.rightBool = false;
			this.activeBlock.moveX(1);
			if(this.activeBlock.sideBorders()) {
				this.activeBlock.moveX(-1);
			}
			for(int i = 0; i < blocks.size(); i++) {
				if(this.activeBlock.intersects(blocks.get(i))) {
					this.activeBlock.moveX(-1);
					break;
				}
			}
		}
		if(!(input.isKeyDown(Input.D))) {
			this.rightBool = true;
		}
		
		
		
		if((input.isKeyDown(Input.R) || input.isLeftMouseDown()) && rotationBool) {
			this.rotationBool = false;
			this.activeBlock.rotate();
			if(this.activeBlock.sideBorders()) {
				this.activeBlock.rotateRev();
			}
			for(int i = 0; i < blocks.size(); i++) {
				if(this.activeBlock.intersects(blocks.get(i))) {
					this.activeBlock.rotateRev();
					break;
				}
			}
		}
		if(!(input.isKeyDown(Input.R) || input.isLeftMouseDown())) {
			this.rotationBool = true;
		}
		
		
		
		if(tickCounter > 50 ) {
			tickCounter = 0;
			this.activeBlock.moveY(1);
			if(this.activeBlock.grounded(this.lines)) {
				this.activeBlock.moveY(-1);
				collAction();
			}
			for(int i = 0; i < blocks.size(); i++) {
				if(this.activeBlock.intersects(blocks.get(i))) {
					this.activeBlock.moveY(-1);
					collAction();
				}
			}
		}else if((input.isKeyDown(Input.S) || input.isKeyDown(Input.SPACE)) && this.downBool){
			this.downBool = false;
			this.activeBlock.determinePos();
			
			//determineEffect:
			
			//sound
			Random ran = new Random();
			this.detSounds[ran.nextInt(detSounds.length)].play();
			
			//visual
			ArrayList<Tile> tiles = this.activeBlock.getParts();
			boolean[] collomns = new boolean[10];
			for(int x = 0; x < 10; x++) {
				for(int i = 0; i < tiles.size(); i++) {
					if(tiles.get(i).getXCoord() == x && !collomns[x]) {
						this.effectmap.add(new DetCollomn(x, this.lines, this.activeBlock.getColor()));
						collomns[x] = true;
					}
				}
				
			}
			
			//<
			
			collAction();
		}
		if(!(input.isKeyDown(Input.S) || input.isKeyDown(Input.SPACE))) {
			this.downBool = true;
		}
		
		this.activeBlock.calcGhost(this.blocks, this.lines);
	}
	
	/**
	 * the action after a coll has been detected
	 */
	private void collAction() {
		this.blocks.addAll(this.activeBlock.getParts());
		this.activeBlock = nextBlock;
		this.nextBlock = newBlock();
		this.checkTetris();
	}
	
	public void render(Graphics2D g) {
		this.activeBlock.render(g);
		for(int i = 0; i < blocks.size(); i++) {
			Tile block = blocks.get(i);
			if(block != null) {
				block.render(g);
			}
		}
	}
	
	private Block newBlock() {
		
		switch(new Random().nextInt(9)) {
		case 0:
			return new Stick(5, 0);
		case 1:
			return new Gamma(5, 0);
		case 2:
			return new Alpha(5, 0);
		case 3:
			return new Square(5, 0);
		case 4:
			return new InvSkew(5, 0);
		case 6:
			return new Logo(5, 0);
		case 7:
			return new OutvSkew(5, 0);
		default:
			return newBlock();
		}
		
	}
	
	private void checkTetris() {
		Tile it = new DummyBlock(0,0);
		
		for(int x = 0; x < 10; x++) {
			it.setCoord(x, 0);
			for(int i = 0; i < blocks.size(); i++) {
				if(it.intersects(blocks.get(i))) {
					System.out.println("DEEAAAAAAAAAAAAD \n-@TODO TetrisField line~186\n" + "Score: " +getScore() + "\n");
					this.blocks.clear();
					this.score = 0;
					this.linesCleard = 0;
				}
			}
		}
		
		
		
		for(int y = 0; y < lines; y++) {
			int amount = 0;
			for(int x = 0; x < 10; x++) {
				it.setCoord(x, y);
				for(int i = 0; i < blocks.size(); i++) {
					if(it.intersects(blocks.get(i))) {
						amount++;
					}
				}
			}
			if(amount == 10) {
				//removing
				int counter = 0;
				for(int x = 0; x < 10; x++) {
					it.setCoord(x, y);
					for(int i = 0; i < blocks.size(); i++) {
						if(it.intersects(blocks.get(i))) {
							blocks.remove(i);
							counter++;
							i--;
						}
					}
				}
				
				this.score += counter/4 * counter/4;
				this.linesCleard += counter/4;
				
				//stacking up
				for(int y2 = y; y2 > 0; y2--) {
					for(int x = 0; x < 10; x++) {
						it.setCoord(x, y2);
						for(int i = 0; i < blocks.size(); i++) {
							if(it.intersects(blocks.get(i))) {
								blocks.get(i).moveY(1);
							}
						}
					}
				}
			}
			
		}
		
		/**
		 * @todo check for blocks that are doubled
		 */
	}
	public int getScore() {
		return this.score;
	}
	public Block getNextBlock() {
		return this.nextBlock;
	}
	public int getLinesCleared() {
		return this.linesCleard;
	}
}


















