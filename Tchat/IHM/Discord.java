/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:23:00 CET 2020
*
*/

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class Discord extends JPanel {

	private Connection connection;
	private Tchat tchat;

	public Discord() {

	//	this.setLayout(new GridLayout(2, 1, 10, 10));

		this.tchat = new Tchat();
		

		this.connection = new Connection(this.tchat);
		this.add(this.connection);
		this.add(this.tchat);
		
	}

	@Override
	protected void paintComponent(Graphics g){
		
		
	}
  
	
}