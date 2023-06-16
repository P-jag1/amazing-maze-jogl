package Maze;

import com.jogamp.opengl.GL2;
//tøída pro uložení všech prvkù bludištì jako jsou stìny, podlaha, atd.
public interface Component {

	void draw(GL2 gl);
}
