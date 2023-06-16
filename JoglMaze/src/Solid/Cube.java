package Solid;

import java.util.ArrayList;
import java.util.List;

import Geometry.Polygon;
import Geometry.Vertex3D;

public class Cube extends Shape {
	
	private float x;
	private float y;
	private float z;
	private List<Vertex3D> vertices;
	
	public Cube(Vertex3D vertex) {
		super(vertex);
	}
	//metoda pro vytvoøení krychle
	public void createCube() {
		//vytvoøení seznamu potøebných vertexù
		createVertices();
		//vytvoøení jednotlivých hran krychle
		Polygon p1 = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		Polygon p2 = new Polygon(vertices.get(1), vertices.get(4), vertices.get(5), vertices.get(2));
		Polygon p3 = new Polygon(vertices.get(4), vertices.get(7), vertices.get(6), vertices.get(5));
		Polygon p4 = new Polygon(vertices.get(7), vertices.get(0), vertices.get(3), vertices.get(6));
		Polygon p5 = new Polygon(vertices.get(3), vertices.get(2), vertices.get(5), vertices.get(6));
		Polygon p6 = new Polygon(vertices.get(0), vertices.get(1), vertices.get(7), vertices.get(4));
		//pøidání krychle do seznamu
		addPolygon(p1);
		addPolygon(p2);
		addPolygon(p3);
		addPolygon(p4);
		addPolygon(p5);
		addPolygon(p6);
	}
	//metoda pro vytvoøení vertexù krychle
	private void createVertices() {

		vertices = new ArrayList<Vertex3D>();
		//získání vertexu, ze kterého budeme vytváøet jednotkovou krychly
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		//vytvoøení potøebných bodù k sestavení jednotkové krychle	
		vertices.add(new Vertex3D(origin)); 
		vertices.add(new Vertex3D(x + 1, y, z)); 
		vertices.add(new Vertex3D(x + 1, y + 1, z)); 
		vertices.add(new Vertex3D(x, y + 1, z)); 	
		vertices.add(new Vertex3D(x + 1, y, z - 1)); 
		vertices.add(new Vertex3D(x + 1, y + 1, z - 1)); 		
		vertices.add(new Vertex3D(x, y + 1, z - 1)); 
		vertices.add(new Vertex3D(x, y, z - 1)); 
	}
}
