package Gui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;

public class DialogSelect extends JDialog {
private static final long serialVersionUID = 1L;
	
	private int level = 0;
	Button one = new Button("Level 1");
	Button two = new Button("Level 2");
	Button three = new Button("Level 3");

	public DialogSelect() {
		setModal(true); // modalni rezim okna
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		

		one.addActionListener(e -> {
			level = 1;
			dispose();
		});
		
		three.addActionListener(e -> {
			level = 3;
			dispose();
		});
		
		two.addActionListener(e -> {
			level = 2;
			dispose();
		});

		// pridame potrebne komponenty

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		add(one, c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		add(two, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		add(three, c);

		pack();
	}
	
	public void New() {
		initGui();
	}
	
	private void initGui() {

		setVisible(true);

	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

}
