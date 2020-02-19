/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 12:18:05 CET 2020
*
*/

import java.awt.Dimension;
import javax.swing.JFrame;

public class Fenetre extends JFrame{

	private Discord discord;

	public Fenetre() {

		this.discord = new Discord();
		this.setTitle("Window");
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(this.discord);
		this.setVisible(true);
		
	}

	
}