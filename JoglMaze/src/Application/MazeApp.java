package Application;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import Gui.DialogEnd;
import Gui.DialogFinish;
import Gui.DialogGuide;
import Gui.DialogStart;
import Maze.Maze;
import Maze.Textures;



public class MazeApp extends KeyAdapter implements GLEventListener {

	
	public static final int CANVAS_WIDTH = 900;	
	public static final int CANVAS_HEIGHT = 600;
	private float ar;
	private float rotAngle;
	private float helpRotAngle;
	private float positionX;
	private float positionY = 0.6f;
	private float positionZ;
	private int level = 1;
	private boolean firstTimeSetup = true;

	private GLU glu = new GLU();
	private static DialogStart diaStart;
	private static DialogGuide diaGuide;
	private DialogEnd diaEnd = new DialogEnd();
	private DialogFinish diaFinish = new DialogFinish();
	private Maze maze;
	private static JFrame frame;
	private static GLCanvas canvas;

	//vytvoøení bludištì
	public void setupMaze(GLCanvas canvas) {	
		//pokud ho vytváøíme poprvé
		//chceme vytvoøit pouze jeden timer
		if(firstTimeSetup) {
			rotAngle = 0;
			helpRotAngle = 0;
			setTimer(canvas);
			firstTimeSetup = false;
			diaFinish.setLocationRelativeTo(frame);
			diaEnd.setLocationRelativeTo(frame);
		}
		//interakce s GUI
		//pokud uživatel vybral možnost výbìru levelu
		if(diaStart.isLevelOk()) {
			if(diaStart.getDiaSel().getLevel() == 0) {
				System.exit(0);
			} else {
				level = diaStart.getDiaSel().getLevel();
				diaStart.setLevelOk(false);
			}
		}
		//podle aktuálního levelu se vykreslí pøíslušná maze
		//získají se poèáteèní pozice hráèe
		if(level == 1) {
			maze = new Maze("Maze1.txt", diaEnd);
			positionX = maze.getCurrentX() + 0.5f;
			positionZ = maze.getCurrentZ() - 0.5f;
			rotAngle = 0;
			helpRotAngle = 0;
		} else if(level == 2) {
			maze = new Maze("Maze2.txt", diaEnd);
			positionX = maze.getCurrentX() + 0.5f;
			positionZ = maze.getCurrentZ() - 0.5f;
			rotAngle = 0;
			helpRotAngle = 0;
		} else if(level == 3) {
			maze = new Maze("Maze3.txt", diaEnd);	
			positionX = maze.getCurrentX() + 0.5f;
			positionZ = maze.getCurrentZ() - 0.5f;
			rotAngle = 0;
			helpRotAngle = 0;
		}
	}
	//timer, který nám pøeklesluje canvas
	private void setTimer(GLCanvas canvas) {
		//hodnota, která se bude pøièítat k pohybu hráèe
		float adjust = 0.01f;
		//inicializace timeru
		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				//hlídáme kdy hrá došel na konec kola
				//podle jeho rozhodnutí poté vykonáme urèitou akci
				if(diaEnd.isEndOk()) {
					System.exit(0);
				} else if(diaEnd.isNextLevel()) {
					if(level == 1) {
						level = 2;
						diaEnd.setNextLevel(false);
						setupMaze(canvas);
					} else if(level == 2) {
						level = 3;
						diaEnd.setNextLevel(false);
						setupMaze(canvas);
					} else if(level == 3) {
						diaFinish.New();
					}
				}
				
				if(diaEnd.isReplay()) {
					if(level == 1) {
						diaEnd.setReplay(false);
						setupMaze(canvas);
					} else if(level == 2) {
						diaEnd.setReplay(false);
						setupMaze(canvas);
					} else if(level == 3) {
						diaEnd.setReplay(false);
						setupMaze(canvas);
					}
				}
				//volání metod pro plynulý posun hráèe
				AdjustX(adjust);
				AdjustZ(adjust);
				AdjustRotation();
				
				//nakonec pøekreslíme
				canvas.repaint();
			}
			//metoda pro zmìnu rotace
			//vždy se pøièíta a odeèíta od rotací podle smìru otáèení
			//opakuje se až do doby kdy rotace není opìt nula tzn. že hráè se v danou chvíli netoèí
			private void AdjustRotation() {
				
				if (helpRotAngle > 0) {
					rotAngle++;
					helpRotAngle--;
				}
				else if (helpRotAngle < 0) {
					rotAngle--;
					helpRotAngle++;
				}
			}
			//metoda pro zmìnu souøadnice x
			//získáme aktualní souøadnici x
			//opakovanì pøíèítáme/odeèítáme hodnotu offset hodnotu podle místa kam se hráè má pohnout
			private void AdjustX(float offset) {
				float locationX = maze.getCurrentX() + 0.5f;

				if (Math.abs(locationX - positionX) < 0.01 && Math.abs(locationX - positionX) > 0) {
					return;
				}
				
				if (positionX < locationX) {
					positionX += offset;
				}
				
				else if (positionX > locationX) {
					positionX -= offset;
				}
			}
			//metoda pro zmìnu souøadnice z
			private void AdjustZ(float offset) {
				float locationZ = maze.getCurrentZ() - 0.5f;
				
				if (Math.abs(locationZ - positionZ) < 0.01 && Math.abs(locationZ - positionZ) > 0) {
					return;
				}
				
				if (positionZ < locationZ) {
					positionZ += offset;
				}
				
				else if (positionZ > locationZ) {
					positionZ -= offset;
				}
			}
			//nastavení intervalu opakování
		}, 1000/60, 1000/60);
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClearColor(0.0f, 0.5f, 1.0f, 0.5f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		//tvorba textur
		Textures.init(gl);
	}

	@Override
	public void display(GLAutoDrawable drawable) {				
		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	
		if (maze.isView() ) {
			glu.gluLookAt(maze.getMazeWidth() / 2, 10, maze.getMazeHeight() / -2, 
						  maze.getMazeWidth() / 2,  0, maze.getMazeHeight() / -2, 
						  0, 0, -1);
		}else {
			float x = positionX;
			float y = positionY;
			float z = positionZ;

			gl.glTranslatef(-x, -y, -z);

			gl.glTranslatef(x, y, z);
			gl.glRotatef(rotAngle, 0, 1, 0);
			gl.glTranslatef(-x, -y, -z);
			gl.glEnable(GL2.GL_FOG);
			gl.glFogf(GL2.GL_FOG_DENSITY,0.50f);
			gl.glHint(GL2.GL_FOG_HINT,GL2.GL_NICEST);
		}
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		if (maze.isView() ) {
			glu.gluPerspective(100, ar, 1, 100);
		}
		else {
			glu.gluPerspective(75, ar, 0.1, 100);
		}
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		maze.draw(gl);
		gl.glDisable(GL2.GL_FOG);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {	
		ar = (float) width / (height);
		drawable.getGL().getGL2().glViewport(0, 0, width , height);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		
	}	
	//key listenery
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			 maze.turnLeft();
			 helpRotAngle -= 90;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			maze.turnRight();
			helpRotAngle += 90;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
		    maze.moveForward();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			maze.moveBackward();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_H) {
			maze.updateView();
			maze.createMaze();
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			diaStart.New();
			
			if(diaStart.isLevelOk()) {
				if(diaStart.getDiaSel().getLevel() == 0) {
					System.exit(0);
				} else {
					level = diaStart.getDiaSel().getLevel();
					diaStart.setLevelOk(false);
					setupMaze(canvas);
				}
			}
			
			if(diaStart.isOk() == false) {
				System.exit(0);
			}
		}
	}
	
	public static void invokeMaze() {
		if(diaStart.isOk()) {
		
		try {
			Object self = self().getConstructor().newInstance();
			self.getClass().getMethod("setupMaze", new Class[] { GLCanvas.class }).invoke(self, canvas);
			canvas.addGLEventListener((GLEventListener)self);
			canvas.addKeyListener((KeyListener)self);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
		} else {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		frame = new JFrame("Amazing Maze");
		// nastavení OpenGL version2
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		capabilities.setRedBits(8);
		capabilities.setBlueBits(8);
		capabilities.setGreenBits(8);
		capabilities.setAlphaBits(8);
		capabilities.setDepthBits(24);
		// tvorba canvas
		canvas = new GLCanvas(capabilities);
		
        canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//nastavení ikony
		ImageIcon img = new ImageIcon("texture/icon.jpg");
		frame.setIconImage(img.getImage());
		
		canvas.requestFocusInWindow();
		//tvorba dialogu pro start
		diaGuide = new DialogGuide();
		diaGuide.setLocationRelativeTo(frame);
		diaGuide.New();
		diaStart = new DialogStart(frame);
		diaStart.setLocationRelativeTo(frame);
		diaStart.New();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread() {
                     @Override
                     public void run() {
                        System.exit(0);
                     }
                  }.start();
			}
		});
		invokeMaze();
	}
	//getry a setry
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		MazeApp.frame = frame;
	}
	
	private static Class<?> self() {
		return new Object() { }.getClass().getEnclosingClass();
	}
	
	public DialogEnd getDiaEnd() {
		return diaEnd;
	}

	public void setDiaEnd(DialogEnd diaEnd) {
		this.diaEnd = diaEnd;
	}
}
