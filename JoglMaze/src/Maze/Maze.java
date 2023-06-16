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
	//atribut ur�uj�c� orientaci pohledu postavy hr��e
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
	//metoda, kter� namapuje soubor a vytvo�� dvourozm�rn� pole, kter� p�edstavuje mapu bludi�t�
	//tak� n�m vytvo�� String napln�n� �daji ze souboru 
	private void createMap(String mazeFile) {
		BufferedReader input;
		//String, do kter�ho se bude ukl�d�t text ze souboru
		String mazeText = "";
		String line;
		int row, column;
		
		try {
			input = new BufferedReader(new FileReader(mazeFile));
			//p�e�ten� prvn�ho ��dku souboru
			line = input.readLine();			
			while (line != null) {
				//vytvo�en� ���ky bludi�t�
				mazeWidth = Math.max(line.length(), mazeWidth);
				
				if (line.length() > 0) {
					//v��ka bludi�t� se p�i��t� podle po�tu p�e�ten�ch ��dk�
					mazeHeight++;
					mazeText += line;
				}
				
				if (line != null){
					//hl�d� n�m kde kon�� a za��n� dal�� ��dek v mazeTextu
					mazeText += '\n';
				}
				
				line = input.readLine();
			}
			//vytvo�en� mapy bludi�t�
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
	//metoda pro napln�n� mapy bludi�t� p��slu�n�mi hodnotami
	private void fillMapWithValues(String mazeText) {		
		int row = 0; 
		int	column = 0;
		//proch�z�me text bludi�t�
		//pomoc� podm�nek p�i�ad�me ka�d�mu m�stu v map� dann� znak
		for (int i = 0; i <= mazeText.length() - 1; i++) {
			char pom = mazeText.charAt(i);			
			if (pom >= '0' && pom <= '2') {
				map[row][column] = pom;
				column++;
			} else if (pom == ' ') {
				column++;
			} else if ("S".indexOf("" + pom) >= 0) {
				//nastaven� po��te�n�ch hodnot postavy hr��e prob�hne a� se v prohled�v�n� naraz� na znak 'S'
				startZ = row;
				startX = column;	
				column++;
			} else if (pom == '\n') {
				row++;
				column = 0;
			} else {
				System.err.println("!!!Chyba p�i �ten� souboru ve znaku: " + pom + " !!!");
			}
		}
	}
	//metoda pro vytvo�en� bludi�t� (jeho komponent)
	public void createMaze() {
		maze = new Component[mazeHeight][mazeWidth];
		maze2 = new Component[mazeHeight][mazeWidth];
		maze3 = new Component[mazeHeight][mazeWidth];
		
		for (int i = mazeHeight - 1, z = 0; i >= 0 ; i--, z--) {
			for (int x = 0; x <= mazeWidth - 1; x++) {
				//vytvo�en� p�vodn�ho vertexu pro komponentu
				Vertex3D origin = new Vertex3D(x, 0, z);				
				char pom = map[i][x];
				//podle znaku z mapy se rozhodne jak� komponenta se vytvo��
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
					System.err.println("Chyba v pomocn�: " + pom);
				}
			}
		}
	}
	//metoda slou��c� pro vykreslen� komponent
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
	
	//metody pro zm�nu orientace
	//rozhas orientace je v�dy od 0-3, proto�e hr�� se m��e d�vat jenom do 4 sm�r�
	//metody zaji��uj�, �e orientace bude v�dy v tomto rozhasu
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
	//metoda, kter� podle orientace uprav� lokaci hr��e
	private boolean move(int ori) {
		boolean result = false;
		System.out.println(orientation);
		//podle orientace hr��e (��slo v rozmez� od 0-3) se vyhodnot� jedna z case podm�nek
		//n�sledn� se v p��pad� speci�ln�ch pol� provede ur�it� operace
		//nakonec se uprav� pozice hr��e na dal�� pole v bludi�ti
		//prob�h� zde i �e�en� se sb�r�n�m p�edm�t�
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
	//getry a setry
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
