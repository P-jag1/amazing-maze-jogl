package Solid;

import java.util.List;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Geometry.Polygon;
import Geometry.Vertex3D;

public abstract class Shape {

	private Vertex3D origin;
	//sezman pologonù 
	private List<Polygon> polygons;
	
	public Shape(Vertex3D vertex) {
		this.origin = vertex;
		this.polygons = new ArrayList<Polygon>();
	}
	//metoda pro pøidání polygonu
	public void addPolygon(Polygon surface) {
			this.polygons.add(surface);
	}
	//metoda pro vykreslení seznamu polygonù
	public void draw(GL2 gl) {
		for (Polygon p : polygons) {
			p.drawWithTexture(gl);
		}
	}
	
	public Vertex3D getOrigin() {
		return origin;
	}
}
