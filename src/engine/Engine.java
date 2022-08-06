package engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class Engine extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -3521765137857336153L;
	
	private boolean running;
	private Thread updThread;
	private Thread renderThread;
	private Window window;
	private String name;
	private double tickTime;
	public static int WIDTH, HEIGHT;
	private Input input;
	
	private GameContainer gc;
	
	public Engine(String name,int width, int height, GameContainer gc, double tickTime) {
		this.name = name;
		Engine.WIDTH = width;
		Engine.HEIGHT = height;
		Dimension d = new Dimension(width, height);
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.gc = gc;
		this.tickTime = tickTime;
		this.input = new Input();
	}
	
	public void init() {
		this.running = false;
		
		this.window = new Window();
		this.renderThread = new Thread(window);
		this.window.init(this, renderThread);
		
		this.updThread = new Thread(this);
		
		
		this.addKeyListener(input);
		this.addMouseListener(input);
		this.requestFocus();
		
		this.gc.setInput(input);
		gc.init();
		
	}
	
	public synchronized void start() {
		if(!running) {
			this.running = true;
			updThread.start();
			window.start();
		}
		
	}
	
	public synchronized void stop() {
		this.running = false;
		window.stop();
		//not perfectly clean
		System.exit(-1);
		try {
			updThread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
	}

	@Override
	public void run() {
		long lastFrame = System.currentTimeMillis();
		double timer = 0;
		while(running) {
			
			if(input.isKeyDown(Input.ESCAPE))this.running =false;
			long thisFrame = System.currentTimeMillis();
			double delta = (thisFrame-lastFrame)/1000d;
			lastFrame = thisFrame;
			timer+=delta;
			if(timer > tickTime) {
				synchronized (gc) {
					gc.tick();
				}
				
				timer -= tickTime;
			}
		}
		stop();
	}
	
	public void render(Graphics2D g) {
		synchronized (gc) {
			gc.render(g);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setTickTime(double tickTime) {
		this.tickTime = tickTime;
	}
	
}
