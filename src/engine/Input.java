package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener{
	
	public static final int A = 0;
	public static final int S = 1;
	public static final int D = 2;
	public static final int W = 3;
	public static final int SPACE = 4;
	public static final int ESCAPE = 5;
	public static final int R = 6;
	
	private boolean[] keys;
	
	private boolean left_mouse;
	
	public Input() {
		keys = new boolean[7];
		this.left_mouse = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			keys[A] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			keys[S] = true;	
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			keys[D] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			keys[W] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[SPACE] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			keys[ESCAPE] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			keys[R] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			keys[A] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			keys[S] = false;	
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			keys[D] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			keys[W] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[SPACE] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			keys[ESCAPE] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			keys[R] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean isKeyDown(int key) {
		return keys[key];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			this.left_mouse = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1) {
			this.left_mouse = false;
		}
	}
	
	public boolean isLeftMouseDown() {
		return left_mouse;
	}

}

















