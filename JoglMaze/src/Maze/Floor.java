package Maze;

import com.jogamp.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;
import Geometry.Vertex3D;
import Solid.Surface;

public class Floor extends Surface implements Component {
	
	private int textureIndex;
	
	public Floor(Vertex3D vertex, int textureIndex) {
		super(vertex);
		super.createFloorSurface();
		this.textureIndex = textureIndex;
	}
	
	public void draw(GL2 gl) {
		
		Texture texture = Textures.get(textureIndex);
		texture.enable(gl);
		texture.bind(gl);		
		super.draw(gl);		
		texture.disable(gl);
	}
	
}
