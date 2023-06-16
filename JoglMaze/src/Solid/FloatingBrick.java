package Solid;

import java.util.ArrayList;
import java.util.List;

import Geometry.Polygon;
import Geometry.Vertex3D;

public class FloatingBrick extends Shape {
	
	private float x;
	private float y;
	private float z;
	private List<Vertex3D> vertices;

	public FloatingBrick(Vertex3D vertex) {
		super(vertex);
	}
	//metoda pro vytvoøení cihly
	public void createPlate() {
		
		createVertices();
		
		Polygon p1 = new Polygon(vertices.get(0), vertices.get(1), vertices.get(5), vertices.get(4));
		Polygon p2 = new Polygon(vertices.get(0), vertices.get(1), vertices.get(3), vertices.get(2));
		Polygon p3 = new Polygon(vertices.get(1), vertices.get(3), vertices.get(6), vertices.get(5));
		Polygon p4 = new Polygon(vertices.get(0), vertices.get(2), vertices.get(7), vertices.get(4));
		Polygon p5 = new Polygon(vertices.get(2), vertices.get(3), vertices.get(6), vertices.get(7));
		Polygon p6 = new Polygon(vertices.get(4), vertices.get(5), vertices.get(6), vertices.get(7));
		//pøidání cihly do seznamu
		addPolygon(p1);
		addPolygon(p2);
		addPolygon(p3);
		addPolygon(p4);
		addPolygon(p5);
		addPolygon(p6);
	}
	//vytvoøí vertexy cihly
	public void createVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		
		vertices.add(new Vertex3D(x + 0.4f, y + 0.3f, z - 0.45f)); 
		vertices.add(new Vertex3D(x + 0.6f, y + 0.3f, z - 0.45f)); 
		vertices.add(new Vertex3D(x + 0.4f, y + 0.3f, z - 0.55f)); 
		vertices.add(new Vertex3D(x + 0.6f, y + 0.3f, z - 0.55f)); 	
		vertices.add(new Vertex3D(x + 0.4f, y + 0.35f, z - 0.45f)); 
		vertices.add(new Vertex3D(x + 0.6f, y + 0.35f, z - 0.45f)); 		
		vertices.add(new Vertex3D(x + 0.6f, y + 0.35f, z - 0.55f)); 
		vertices.add(new Vertex3D(x + 0.4f, y + 0.35f, z - 0.55f)); 
		
	}

}
