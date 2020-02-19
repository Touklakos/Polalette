/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 12:34:19 CET 2020
*
*/

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;


public class Connection extends JPanel implements ActionListener {

	private ChampTexte nom;
	private ChampTexte ip;
	private ChampTexte port;
	private JButton courant;


	private Tchat tchat;


	public Connection(Tchat tchat) {


	//	this.setLayout(new GridLayout(2,2,5,5));


		this.nom = new ChampTexte("Nom", 20);
		this.add(this.nom);

		this.ip = new ChampTexte("IP", 10);
		this.add(this.ip);

		this.port = new ChampTexte("Port",5);
		this.add(this.port);

		this.courant = new JButton("Connection");
		this.courant.setMinimumSize(new Dimension(20, 20));
		this.courant.setMaximumSize(new Dimension(20, 20));
		this.add(this.courant);
		this.tchat = tchat;

		this.courant.addActionListener(this);
		this.courant.addActionListener(this.tchat);

	}

	@Override
	protected void paintComponent(Graphics g){


	}

	private void modifEditable(boolean b) {

		this.nom.setEditable(b);
		this.ip.setEditable(b);
		this.port.setEditable(b);

	}

	private void desactive() {

		this.modifEditable(false);

	}

	private void reactive() {

		this.modifEditable(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(this.courant.getText() == "Connection") {

			System.out.println("Coucou");
			this.courant.setText("Deconnection");
			this.desactive();

		} else if(this.courant.getText() == "Deconnection") {

			System.out.println("Au revoir");
			this.courant.setText("Connection");
			this.reactive();

		}

	}

}
