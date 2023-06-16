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
		add(new Label("Vítejte ve høe Amazing Maze."), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Hra byla vytvoøena jako semestrální projekt do pøedmìtu PGRF2."), c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Postavu ovládáte pomocí W - dopøedu, S - dozadu , A - otoèit se doleva o 90°, D - otoèit se doprava o 90°."), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Stisknutím klávesy H - Hint se podíváte na blusištì z vrchu"), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Stisknutím klávesy Esc otevøete znovu hlavní nabídku"), c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("Cílem hry je najít východ ven z bludištì a pøitom posbírat co nejvíce zlatých cihlièek."), c);
		
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
