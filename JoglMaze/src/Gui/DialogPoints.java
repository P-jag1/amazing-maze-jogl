package Gui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JDialog;

public class DialogPoints extends JDialog{
private static final long serialVersionUID = 1L;
	
	private int body =  0;	
	Button ok = new Button("Ok");

	public DialogPoints(int body) {
		setModal(true); 
		this.body = body;
		
		setTitle("Amazing Maze");

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		ok.addActionListener(e -> {
			dispose();
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(new Label("You managed to collect " + getBody() +"/10 of golden bars" ), c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(ok, c);
		
		pack();

	}
	
	public void New() {
		initGui();
	}
	
	private void initGui() {
		pack();
		setVisible(true);

	}
	
	public int getBody() {
		return body;
	}

	public void setBody(int body) {
		this.body = body;
	}
}
