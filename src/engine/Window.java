package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window implements Runnable{
	
	private JFrame jf;
	private boolean running;
	private Thread renderThread;
	private BufferStrategy bs;
	private Engine engine;
	
	public void init(Engine engine, Thread renderThread) {
		this.jf = new JFrame(engine.getName());
		this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jf.setResizable(false);
		this.jf.add(engine);
		this.jf.pack();
		this.jf.setLocationRelativeTo(null);
		this.jf.setVisible(true);
		
		this.renderThread = renderThread;
		this.running = false;
		
		this.engine = engine;
	}
	
	public synchronized void start() {
		if(!running) {
			renderThread.start();
			this.running = true;
		}
	}
	
	public synchronized void stop() {
		this.running = false;
		try {
			renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while(running) {
			if(bs == null) {
				engine.createBufferStrategy(2);
			}
			this.bs = engine.getBufferStrategy();
				
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.gray);
			g.fillRect(0, 0, jf.getWidth(), jf.getHeight());
			
			engine.render((Graphics2D) g);
				
			g.dispose();
			bs.show();
			
		}
	}
	
}
