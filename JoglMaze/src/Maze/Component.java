package Maze;

import com.jogamp.opengl.GL2;
//t��da pro ulo�en� v�ech prvk� bludi�t� jako jsou st�ny, podlaha, atd.
public interface Component {

	void draw(GL2 gl);
}
