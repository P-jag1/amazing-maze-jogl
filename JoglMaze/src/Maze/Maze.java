package Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.jogamp.opengl.GL2;

import Geometry.Vertex3D;
import Gui.DialogEnd;
import Gui.DialogPoints;

public class Maze {

	private boolean view;	
	private int startZ;
	private int startX;
	private int orientation;
	int mazeWidth = 0; 
	int	mazeHeight = 0;
	private char[][] map;
	private int[][] pointControl;
	private Component[][] maze;
	private Component[][] maze2;
	private Component[][] maze3;
	private DialogEnd diaEnd;
	private DialogPoints diaPoint;
	private int pocetBodu = 0;

	public Maze(String mazeFile, DialogEnd diaEnd) {
		createMap(mazeFile);
		createMaze();
		view = false;
		this.diaEnd = diaEnd;
	}

	private void createMap(String mazeFile) {
		BufferedReader input;
		String mazeText = "";
		String line;
		int row, column;
		
		try {
			input = new BufferedReader(new FileReader(mazeFile));
			line = input.readLine();			
			while (line != null) {
				mazeWidth = Math.max(line.length(), mazeWidth);
				
				if (line.length() > 0) {
					mazeHeight++;
					mazeText += line;
				}
				
				if (line != null){
					mazeText += '\n';
				}
				
				line = input.readLine();
			}
			
			map = new char[mazeHeight][mazeWidth];
			pointControl = new int[mazeHeight][mazeWidth];
			
			for (row = 0; row <= mazeHeight - 1; row++){
				for (column = 0; column < mazeWidth; column++){
					map[row][column] = ' ';
				}
			}	
			
			 fillMapWithValues(mazeText);
			
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	private void fillMapWithValues(String mazeText) {		
		int row = 0; 
		int	column = 0;
		
		for (int i = 0; i <= mazeText.length() - 1; i++) {
			char pom = mazeText.charAt(i);			
			if (pom >= '0' && pom <= '2') {
				map[row][column] = pom;
				column++;
			} else if (pom == ' ') {
				column++;
			} else if ("S".indexOf("" + pom) >= 0) {
				startZ = row;
				startX = column;	
				column++;
			} else if (pom == '\n') {
				row++;
				column = 0;
			} else {
				System.err.println("!!!Error cannot read: " + pom + " !!!");
			}
		}
	}

	public void createMaze() {
		maze = new Component[mazeHeight][mazeWidth];
		maze2 = new Component[mazeHeight][mazeWidth];
		maze3 = new Component[mazeHeight][mazeWidth];
		
		for (int i = mazeHeight - 1, z = 0; i >= 0 ; i--, z--) {
			for (int x = 0; x <= mazeWidth - 1; x++) {
				Vertex3D origin = new Vertex3D(x, 0, z);				
				char pom = map[i][x];
				if (pom == '0' || pom == ' ' || pom == '2'){
					maze[i][x] = new Floor(origin, 0);
					pointControl[i][x] = 0;
					if(view != true) {
						maze2[i][x] = new Ceiling(origin, 2);
					}
					if(pom == '2') {
						maze3[i][x] = new GoldBrick(origin, 3);
						pointControl[i][x] = 1;
					} 
					if(pom == '0') {
						maze3[i][x] = new Doors(origin, 4);						
					}
				}else if(pom == '1') {
					maze[i][x] = new Wall(origin, 1);
					maze3[i][x] = null;
					pointControl[i][x] = 0;
					if(view != true) {
					maze2[i][x] = null;
					}
				} else {
					System.err.println("Error in pom: " + pom);
				}
			}
		}
	}

	public void draw(GL2 gl) {		
		for (int i = 0; i < mazeHeight; i++) {
			for (int j = 0; j < mazeWidth; j++) {
				maze[i][j].draw(gl);
				if(maze2[i][j] != null) {	
					maze2[i][j].draw(gl);
				}
				if(maze3[i][j] != null) {	
					maze3[i][j].draw(gl);
				}
			}
		}
	}
	

	public void turnLeft() {
		orientation = (orientation + 4 - 1) % 4;
	}

	public void turnRight() {
		orientation = (orientation + 1) % 4;
	}

	public boolean moveForward() {
		return move(orientation);
	}

	public boolean moveBackward() {
		return move((orientation + 2) % 4);
	}

	private boolean move(int ori) {
		boolean result = false;
		System.out.println(orientation);

		switch (ori) {
		case 0:
			if (startZ > 0) {
				if (map[startZ - 1][startX] == ' ' || map[startZ - 1][startX] == '0' 
						|| map[startZ - 1][startX] == '2') {
					if(map[startZ - 1][startX] == '0') {
						startZ -= 1;
						result = true;
						diaPoint = new DialogPoints(pocetBodu);
						diaPoint.setLocationRelativeTo(null);
						diaPoint.New();
						diaEnd.New();
						pocetBodu = 0;
					} else if( map[startZ - 1][startX] == '2') {
						if(	pointControl[startZ - 1][startX] == 1) {
							pocetBodu++;
							pointControl[startZ - 1][startX] = 0;
						}
						maze3[startZ - 1][startX] = null;
						startZ -= 1;
						result = true;
					} else {
						startZ -= 1;
						result = true;
					}
				}
			}
			break;
		case 1:
			if (map[startZ][startX + 1] == ' ' || map[startZ][startX + 1] == '0'
					|| map[startZ][startX + 1] == '2') {
				if(map[startZ][startX + 1] == '0') {
					startX += 1;
					result = true;
					diaPoint = new DialogPoints(pocetBodu);
					diaPoint.setLocationRelativeTo(null);
					diaPoint.New();
					diaEnd.New();
					pocetBodu = 0;
				} else if( map[startZ][startX + 1] == '2') {
					if(	pointControl[startZ][startX + 1] == 1) {						
							pocetBodu++;
							pointControl[startZ][startX + 1] = 0;
					}
					maze3[startZ][startX + 1] = null;
					startX += 1;
					result = true;
				} else {
					startX += 1;
					result = true;
				}
			}
			break;
		case 2:
			if (startZ < map.length - 1) {
				if (map[startZ + 1][startX] == ' ' || map[startZ + 1][startX] == '0' 
						|| map[startZ + 1][startX] == '2') {
					if(map[startZ + 1][startX] == '0') {
						startZ += 1;
						result = true;
						diaPoint = new DialogPoints(pocetBodu);
						diaPoint.setLocationRelativeTo(null);
						diaPoint.New();				
						diaEnd.New();
						pocetBodu = 0;
					} else if( map[startZ + 1][startX] == '2') {
						if(	pointControl[startZ + 1][startX] == 1) {
							pocetBodu++;
							pointControl[startZ + 1][startX] = 0;
						}
						maze3[startZ + 1][startX] = null;
						startZ += 1;
						result = true;
					} else {
						startZ += 1;
						result = true;
					}
				}
			}
			break;
		case 3:
			if (startX > 0) {
				if (map[startZ][startX - 1] == ' ' || map[startZ][startX - 1] == '0'
						|| map[startZ][startX - 1] == '2') {
					if(map[startZ][startX - 1] == '0') {
						startX -= 1;
						result = true;
						diaPoint = new DialogPoints(pocetBodu);
						diaPoint.setLocationRelativeTo(null);
						diaPoint.New();
						diaEnd.New();
						pocetBodu = 0;
					} else if( map[startZ][startX - 1] == '2') {
						if(	pointControl[startZ][startX - 1] == 1) {
								pocetBodu++;
								pointControl[startZ][startX - 1] = 0;
						}
						maze3[startZ][startX - 1] = null;
						startX -= 1;
						result = true;
					} else {
						startX -= 1;
						result = true;
					}
				}
			}
			break;
		}

		return result;
	}

	public int getCurrentZ() { 
		return startZ - getMazeHeight() + 1; 
	}
	
	public int getCurrentX() {
		return startX; 
	}
	
	public int getMazeHeight() { 
		return mazeHeight; 
	}
	public int getMazeWidth() { 
		return mazeWidth;
	}

	public boolean isView() { 
		return view; 
	}
	
	public void updateView() { 
		view = !view; 
	}
}
