package Geometry;

import com.jogamp.opengl.GL2;
//t��da reprezentuj�c� bod se sou�adnicemi
public class Vertex3D {

	private float x;
	private float y;
	private float z;
	
	public Vertex3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vertex3D(Vertex3D v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	//metoda pro vykreslen� vertexu
	public void drawGlVertex3f(GL2 gl) {
		gl.glVertex3f(x, y, z);
	}
	
	public float getX() { 
		return x; 
	}
	
	public float getY() { 
		return y; 
	}
	
	public float getZ() { 
		return z; 
	}
}
