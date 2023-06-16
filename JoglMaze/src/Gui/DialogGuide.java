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
		setModal(true); // modalni rezim okna
		
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ok.addActionListener(e -> {
			dispose();
		});
		// pridame potrebne komponenty

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("V�tejte ve h�e Amazing Maze."), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Hra byla vytvo�ena jako semestr�ln� projekt do p�edm�tu PGRF2."), c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Postavu ovl�d�te pomoc� W - dop�edu, S - dozadu , A - oto�it se doleva o 90�, D - oto�it se doprava o 90�."), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Stisknut�m kl�vesy H - Hint se pod�v�te na blusi�t� z vrchu"), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Stisknut�m kl�vesy Esc otev�ete znovu hlavn� nab�dku"), c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("C�lem hry je naj�t v�chod ven z bludi�t� a p�itom posb�rat co nejv�ce zlat�ch cihli�ek."), c);
		
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
