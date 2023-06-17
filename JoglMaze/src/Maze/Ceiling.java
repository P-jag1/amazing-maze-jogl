package Maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import Geometry.Vertex3D;
import Solid.Surface;

public class Ceiling extends Surface implements Component{
	
	private int textureIndex;
		
	public Ceiling(Vertex3D vertex, int textureIndex) {
		super(vertex);
		super.createCeilingSurface();
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
