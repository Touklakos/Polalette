/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 12:34:19 CET 2020
*
*/

package ihm;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import client_serveur.ClientGraphique;
import client_serveur.ServeurDeconnecteException;

/**
 * Cette classe représente des ChampTexte et un JButton qui permettent en remplissant les ChampTexte de se connecter à un serveur
 */
public class Connection extends JPanel implements ActionListener {

	private ChampTexte nom;
	private ChampTexte ip;
	private ChampTexte port;
	private JButton courant;


	private Tchat tchat;
	private Fenetre fenetre;


	/**
	 * Ce constructeur permet de créer une nouvelle connection
	 * @param tchat Le tchat qui va permettre la conversation avec le serveur
	 * @param fenetre La fenetre sur laquelle va être affiché la connection
	 */
	public Connection(Tchat tchat, Fenetre fenetre) {

		this.fenetre = fenetre;

		this.nom = new ChampTexte("Nom", 20);
		this.add(this.nom);

		this.ip = new ChampTexte("IP", 10);
		this.add(this.ip);

		this.port = new ChampTexte("Port", 5);
		this.add(this.port);

		this.courant = new JButton("Connection");
		this.courant.setMinimumSize(new Dimension(20, 20));
		this.courant.setMaximumSize(new Dimension(20, 20));
		this.add(this.courant);
		this.tchat = tchat;

		this.courant.addActionListener(this);

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

		if(!(this.nom.getText().equals(""))) {

			if(this.courant.getText().equals("Connection") && !this.nom.getText().equals("") && !this.ip.getText().equals("") && !this.port.getText().equals("")) {

				try {

					this.tchat.connecte(this.nom.getText(), this.ip.getText(), this.port.getText());
					System.out.println("Coucou");
					this.courant.setText("Deconnection");
					this.desactive();

				} catch(ServeurDeconnecteException a) {

					System.out.println("Impossible de se connecter au serveur");

				}

			} else if(this.courant.getText().equals("Deconnection")) {

				System.out.println("Au revoir");
				this.courant.setText("Connection");
				this.reactive();
				this.tchat.deconnecte();

			}

			this.fenetre.repaint();

		}

	}

}
