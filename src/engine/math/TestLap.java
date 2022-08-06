package engine.math;

import static java.lang.Math.*;

public class TestLap {
	
	public static void main(String[] args) {
		
		
		Vector2f a = new Vector2f(-1,0);
		double rad = PI;
		Matrix2f m = new Matrix2f(
				(float)cos(rad), (float)-sin(rad),
				(float)sin(rad), (float)cos(rad));
		Vector2f b = m.mult(a);
		b.round();
		System.out.println("b = " +b);
		a.rotateRad(rad);
		a.round();
		System.out.println("a = " +a);
		
		
	}
	
}
