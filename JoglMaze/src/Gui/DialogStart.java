package Gui;

import java.awt.Button;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;

public class DialogStart extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private boolean ok;
	private boolean levelOk = false;
	private DialogSelect diaSel = new DialogSelect();
	Button start = new Button("Start Game - Continue");
	Button end = new Button("End Game");
	Button load = new Button("Choose level");

	public DialogStart(Component a) {
		setModal(true);		
		diaSelPosition(a);
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		start.addActionListener(e -> {
			ok = true;
			dispose();
		});
		
		end.addActionListener(e -> {
			ok = false;
			dispose();
		});
		
		load.addActionListener(e -> {
			levelOk = true;
			diaSel.New();
			ok = true;
			dispose();
		});

		// add components

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(start, c);

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(load, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(end, c);

		pack();
		
	}
	
	public void New() {
		initGui();
	}
	
	private void initGui() {

		setVisible(true);

	}
	
	public void diaSelPosition(Component c) {
		diaSel.setLocationRelativeTo(c);
	}
	
	public boolean isOk() {
		return ok;
	}
	
	public boolean isLevelOk() {
		return levelOk;
	}
	
	public void setLevelOk(boolean levelOk) {
		this.levelOk = levelOk;
	}
	
	public DialogSelect getDiaSel() {
		return diaSel;
	}
}
