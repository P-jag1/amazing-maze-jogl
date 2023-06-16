package Gui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JDialog;

public class DialogFinish extends JDialog {
	private static final long serialVersionUID = 1L;
	Button end = new Button("Ok");

	public DialogFinish() {
		setModal(true); // modalni rezim okna
		
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		end.addActionListener(e -> {
			System.exit(0);
		});
		// pridame potrebne komponenty

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 1;
		add(new Label("Dokonèili jste úspìšnì Amazing Maze. Blahopøejeme!"), c);
		
		c.gridx = 1;
		c.gridy = 2;
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
}
