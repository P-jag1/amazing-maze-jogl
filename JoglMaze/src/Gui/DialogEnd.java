package Gui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JDialog;

public class DialogEnd extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private boolean endOk = false;
	private boolean nextLevel = false;
	private boolean replay = false;
	Button rep = new Button("Try again");
	Button end = new Button("End Game");
	Button con = new Button("Next Level");

	public DialogEnd() {
		setModal(true);
		
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		end.addActionListener(e -> {
			endOk = true;
			dispose();
		});
		
		con.addActionListener(e -> {
			nextLevel = true;
			dispose();
		});
		
		rep.addActionListener(e -> {
			replay = true;
			dispose();
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		add(new Label("You found the exit, congratulations!"), c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		add(rep, c);

		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		add(con, c);
		
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		add(end, c);

		pack();

	}
	
	public void New() {
		initGui();
	}
	
	private void initGui() {

		setVisible(true);

	}
	
	public boolean isEndOk() {
		return endOk;
	}

	public void setEndOk(boolean endOk) {
		this.endOk = endOk;
	}
	
	public boolean isNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(boolean nextLevel) {
		this.nextLevel = nextLevel;
	}
	
	public boolean isReplay() {
		return replay;
	}

	public void setReplay(boolean replay) {
		this.replay = replay;
	}	
}
