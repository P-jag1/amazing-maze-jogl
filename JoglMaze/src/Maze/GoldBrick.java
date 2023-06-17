package Maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import Geometry.Vertex3D;
import Solid.FloatingBrick;

public class GoldBrick extends FloatingBrick implements Component{
	
	private int textureIndex;
	
	public GoldBrick(Vertex3D vertex, int textureIndex) {
		super(vertex);
		super.createPlate();
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
