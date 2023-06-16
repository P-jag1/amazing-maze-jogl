package Geometry;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

public class Polygon {
	
	private List<Vertex3D> vertices;
	
	public Polygon() {
		this.vertices = new ArrayList<Vertex3D>();
	}
	//metoda pro vytvoøení polygonu pomocí vertexù
	public Polygon(Vertex3D...vertices) {
		this.vertices = new ArrayList<Vertex3D>();		
		addVertex(vertices);
	}
	//metoda pro pøidání vertexù do seznamu
	public void addVertex(Vertex3D... vertices) {
		for (Vertex3D v : vertices) {
			this.vertices.add(v);
		}
	}
	//metoda pro pøidání jednoho vertexu do seznamu
	public void addVertex(float x, float y, float z) {
		this.vertices.add(new Vertex3D(x, y, z));
	}
	//metoda, která bude vykreslovat polygon bez textury
	public void drawWithoutTexture(GL2 gl) {
		gl.glBegin(GL2.GL_POLYGON);	
		for (Vertex3D v : vertices) {
			v.drawGlVertex3f(gl);
		}
		gl.glEnd();
	}
	//metoda, která bude vykreslovat polygon s texturou
	public void drawWithTexture(GL2 gl) {
		gl.glBegin(GL2.GL_POLYGON);
		
		float s = 0;
		float t = 0;
		int pom = 0;
		//pro každý vertex vypoèít souøadnicí textury
		for (Vertex3D v : this.vertices) {
			if (pom == 0) {
				s = 0;
				t = 0;
				pom++;
			}
			else if (pom == 1) {
				s = 1;
				t = 0;
				pom++;
			}
			else if (pom == 2) {
				s = 1;
				t = 1;
				pom++;
			}
			else if (pom == 3) {
				s = 0;
				t = 1;
				pom = 0;
			}
			
			gl.glTexCoord2f(s, t);
			v.drawGlVertex3f(gl);
		}
		
		gl.glEnd();
	}
}
