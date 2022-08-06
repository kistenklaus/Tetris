package engine.math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector2f {
	
	private float comX, comY;
	
	public Vector2f(float comX, float comY) {
		this.comX = comX;
		this.comY = comY;
	}
	public Vector2f() {
		this.comX = 0;
		this.comY = 0;
	}
	public Vector2f(Vector2f v1) {
		this.comX = v1.getX();
		this.comY = v1.getY();
	}
	
	
	public Vector2f SUB(Vector2f v2) {
		return new Vector2f(this.comX - v2.getX(), this.comY - v2.getY());
	}
	
	public float getX() {
		return comX;
	}
	
	public float getY() {
		return comY;
	}
	
	public void addX(float dx) {
		this.comX += dx;
	}
	public void addY(float dy) {
		this.comY += dy;
	}
	
	/**
	 * rotates the Vector by a param degrees with matrix transformation
	 * @param degress
	 */
	public void rotateDeg(float degress) {
		this.rotateRad(Math.toRadians(degress));
	}
	
	public void rotateRad(double rad) {
		Vector2f r = new Matrix2f(
				(float)cos(rad),(float)-sin(rad),
				(float)sin(rad),(float)cos(rad))
				.mult(this);
		this.comX = r.getX();
		this.comY = r.getY();
	}
	
	public void round() {
		this.comX = Math.round(this.comX);
		this.comY = Math.round(this.comY);
	}
	
	public String toString() {
		return comX + " | " + comY;
	}
	
	public boolean equals(Object obj) {
		Vector2f v = (Vector2f) obj;
		return v.getX() == this.comX && v.getY() == this.comY;
	}
}
