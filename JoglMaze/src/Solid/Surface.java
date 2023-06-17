package Solid;

import java.util.ArrayList;
import java.util.List;

import Geometry.Polygon;
import Geometry.Vertex3D;

public class Surface extends Shape {

	private float x;
	private float y;
	private float z;
	private List<Vertex3D> vertices;
	
	public Surface(Vertex3D vertex) {
		super(vertex);
	}
	
	public void createFloorSurface() {
		createFloorVertices();
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}

	public void createCeilingSurface() {
		
		createCeilingVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}

	public void createDoorSurface() {
		
		createDoorVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(3), vertices.get(2));
		addPolygon(p);
	}

	public void createFloorVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		vertices.add(new Vertex3D(origin)); 
		vertices.add(new Vertex3D(x + 1, y, z)); 
		vertices.add(new Vertex3D(x + 1, y, z - 1)); 
		vertices.add(new Vertex3D(x, y, z - 1));
	}

	public void createCeilingVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
	
		vertices.add(new Vertex3D(x, y + 1, z)); 
		vertices.add(new Vertex3D(x + 1, y + 1, z)); 
		vertices.add(new Vertex3D(x + 1, y + 1, z - 1)); 
		vertices.add(new Vertex3D(x, y + 1, z - 1));
	}	

	public void createDoorVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		
		vertices.add(new Vertex3D(x + 0.6f, y, z)); 
		vertices.add(new Vertex3D(x + 0.6f, y + 1, z)); 
		vertices.add(new Vertex3D(x + 0.6f, y, z - 1)); 
		vertices.add(new Vertex3D(x + 0.6f, y + 1, z - 1));
	}	
	
}
