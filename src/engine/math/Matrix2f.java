package engine.math;


public class Matrix2f {
	
	private Vector2f i,j;
	
	
	public Matrix2f(float a1, float a2, float b1, float b2) {
		this.i = new Vector2f(a1,a2);
		this.j = new Vector2f(b1,b2);
	}
	
	public Vector2f mult(Vector2f v) {
		float x1 = i.getX()*v.getX() + j.getX()*v.getY();
		float x2 = i.getY()*v.getX() + j.getY()*v.getY();
		return new Vector2f(x1,x2);
	}
	
}
