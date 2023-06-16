package Maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Textures {
	//pole pro uložení textur
	private static Texture[] textures;
	//pole s naètenými texturami
	public static final String[] TEXTURES = {"dust.jpg", "brick.jpg", "ceiling.jpg", "gold.jpg", "door.jpg"};

	public static void init(GL2 gl) {
		textures = new Texture[TEXTURES.length];
		
		try {
			//úprava textur
			for (int i = 0; i <= TEXTURES.length - 1; i++) {
				File infile = new File("texture/" + TEXTURES[i]); 
				BufferedImage image = ImageIO.read(infile);
				textures[i] = TextureIO.newTexture(AWTTextureIO.newTextureData(gl.getGLProfile(), image, false));			
				textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT); 
				textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE); 
	}	
	
	public static Texture get(int n) {
		assert textures[n] != null;
		return textures[n];
	}
}
