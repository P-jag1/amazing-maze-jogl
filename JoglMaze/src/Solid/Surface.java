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
	//metoda pro vytvo�en� podlahy
	public void createFloorSurface() {
		//vytvo�en� seznamu pot�ebn�ch vertex�
		createFloorVertices();
		//vytvo�en� hrany
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}
	//metoda pro vytvo�en� stropu
	public void createCeilingSurface() {
		
		createCeilingVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(2), vertices.get(3));
		addPolygon(p);
	}
	//metoda pro vytvo�en� dve��
	public void createDoorSurface() {
		
		createDoorVertices();
		
		Polygon p = new Polygon(vertices.get(0), vertices.get(1), vertices.get(3), vertices.get(2));
		addPolygon(p);
	}
	//metoda pro vytvo�en� vertex� podlahy
	public void createFloorVertices() {
		
		vertices = new ArrayList<Vertex3D>();
		//z�sk�n� vertexu, ze kter�ho budeme vytv��et hranu
		Vertex3D origin = getOrigin();
		x = origin.getX();
		y = origin.getY(); 
		z = origin.getZ();
		//vytvo�en� pot�ebn�ch bod� k sestaven� hrany
		vertices.add(new Vertex3D(origin)); 
		vertices.add(new Vertex3D(x + 1, y, z)); 
		vertices.add(new Vertex3D(x + 1, y, z - 1)); 
		vertices.add(new Vertex3D(x, y, z - 1));
	}
	//metoda pro vytvo�en� vertex� stropu
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
	//metoda pro vytvo�en� vertex� dve��
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
