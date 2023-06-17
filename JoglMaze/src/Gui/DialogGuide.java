package Gui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JDialog;

public class DialogGuide extends JDialog {

	private static final long serialVersionUID = 1L;
	Button ok = new Button("Ok");

	public DialogGuide() {
		setModal(true);
		
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ok.addActionListener(e -> {
			dispose();
		});

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Welcome to Amazing Maze"), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("For Fun Project"), c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Controls W - walk forward, S - walk backward , A - turn left, D - turn rigth."), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Press H - Hint"), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("ESC - main menu"), c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Goal of the game is to walk through the maze and collect as many gold bars as possible"), c);
		
		c.gridx = 1;
		c.gridy = 7;
		c.anchor = GridBagConstraints.CENTER;
		add(ok, c);

		pack();
	}
	
	public void New() {
		initGui();
	}
	
	private void initGui() {

		setVisible(true);

	}	
}
