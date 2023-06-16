package Maze;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

import Geometry.Vertex3D;
import Solid.Surface;

public class Doors extends Surface implements Component {
	//index textury, kterou budeme pou��vat
	private int textureIndex;
	
	public Doors(Vertex3D vertex, int textureIndex) {
		super(vertex);
		super.createDoorSurface();
		this.textureIndex = textureIndex;
	}
	
	public void draw(GL2 gl) {
		//vykreslen� dve��
		Texture texture = Textures.get(textureIndex);
		texture.enable(gl);
		texture.bind(gl);		
		super.draw(gl);		
		texture.disable(gl);
	}

}
