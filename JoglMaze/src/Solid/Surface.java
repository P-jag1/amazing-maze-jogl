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
	//metoda pro vytvoøení podlahy
	public void createFloorSurface() {
		//vytvoøení seznamu potøebných vertexù
		createFloorVertices();
		//vytvoøení hrany
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}
	//metoda pro vytvoøení stropu
	public void createCeilingSurface() {
		
		createCeilingVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}
	//metoda pro vytvoøení dveøí
	public void createDoorSurface() {
		
		createDoorVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(3), vertices.get(2));
		addPolygon(p);
	}
	//metoda pro vytvoøení vertexù podlahy
	public void createFloorVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		//získání vertexu, ze kterého budeme vytváøet hranu
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		//vytvoøení potøebných bodù k sestavení hrany
		vertices.add(new Vertex3D(origin)); 
		vertices.add(new Vertex3D(x + 1, y, z)); 
		vertices.add(new Vertex3D(x + 1, y, z - 1)); 
		vertices.add(new Vertex3D(x, y, z - 1));
	}
	//metoda pro vytvoøení vertexù stropu
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
	//metoda pro vytvoøení vertexù dveøí
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
