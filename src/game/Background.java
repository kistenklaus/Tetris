package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage bg;
	private int width, height;
	
	public Background(int width, int height) {
		this.width = width;
		this.height = height;
		try {
			this.bg = ImageIO.read(new File("./res/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void renderLayer0(Graphics2D g) {
		g.drawImage(bg, 0, 0, width, height, null);
	}
	
}
