package Maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import Geometry.Vertex3D;
import Solid.Cube;

public class Wall extends Cube implements Component {

	private int textureIndex;
	
	public Wall(Vertex3D vertex, int textureIndex) {
		super(vertex);
		super.createCube();
		this.textureIndex = textureIndex;
	}
	
	@Override
	public void draw(GL2 gl) {
		Texture texture = Textures.get(textureIndex);
		texture.enable(gl);
		texture.bind(gl);		
		super.draw(gl);		
		texture.disable(gl);
	}
}
