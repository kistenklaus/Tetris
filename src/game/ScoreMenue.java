package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreMenue {
	
	private TetrisField tfield;
	private int dx1, dy1;
	private String nextBlockName;
	
	public ScoreMenue(int width, int height, TetrisField tfield) {
		this.tfield = tfield;
		this.nextBlockName = "";
	}
	
	public void tick() {
		String nextBlockName = this.tfield.getNextBlock().getClass().getSimpleName();
		if(!this.nextBlockName.equals(nextBlockName)) {
			this.nextBlockName = nextBlockName;
			
			if(this.nextBlockName.equals("Stick")) {
				this.dx1 = -16;
				this.dy1 = 75-16;
			}else if(this.nextBlockName.equals("Square")) {
				this.dx1 = -45;
				this.dy1 = 75;
			}
			else {
				this.dx1 = -32;
				this.dy1 = 75;
			}
			
			
		}
		
	}
	
	public void render(Graphics2D g) {
		g.translate(dx1, dy1);
		tfield.getNextBlock().justRender(g);
		g.translate(-dx1, -dy1);
		
		//SCORE
		g.setFont(new Font("SansSerif", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString(String.format("%10d", this.tfield.getScore()), 200, 360);
		
		//LINES
		g.drawString(String.format("%10d", this.tfield.getLinesCleared()), 200, 290);
		
	}
	
}
