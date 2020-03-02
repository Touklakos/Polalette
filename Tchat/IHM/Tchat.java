/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:16:57 CET 2020
*
*/

package ihm;


import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;



import client_serveur.ClientGraphique;
import client_serveur.ServeurDeconnecteException;



public class Tchat extends JPanel implements ActionListener {


	private ZoneTexte connectes;
	private ZoneTexte discussion;
	private ZoneTexte message;
	private JButton envoyer;

	private boolean actif;
	private GroupLayout layout;

	private ClientGraphique clientGraphique;
	private String nom;


	public Tchat() {




		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.connectes = new ZoneTexte("Connectés");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.add(this.connectes, c);

		this.discussion = new ZoneTexte("Discussion");
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.add(this.discussion, c);

		this.message = new ZoneTexte("Message");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.add(this.message, c);

		this.envoyer = new JButton("Envoyer");
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.add(this.envoyer, c);


		this.envoyer.addActionListener(this);
		this.desactive();

	}

	public void deconnecte() {

		this.clientGraphique.close();
		this.desactive();

	}

	public void connecte(String nom, String ip, String port) throws ServeurDeconnecteException {

		this.nom = nom;

		if(!this.actif) {

			try {

				this.clientGraphique = new ClientGraphique(this.message.getJTextArea(), this.discussion.getJTextArea(), this.nom);
				this.reactive();

			} catch(ServeurDeconnecteException e) {

				throw e;

			}

		}

	}

	@Override
	protected void paintComponent(Graphics g){


	}

	private void modifEditable(boolean b) {

		this.connectes.setEditable(b);
		this.discussion.setEditable(b);
		this.message.setEditable(b);

	}

	private modifEditable(boolean b) {

		this.modifEditable(b);
		this.actif = b;
		this.setVisible(b);
	}

	private void desactive() {

		modifEditable(false);

	}

	private void reactive() {

		modifEditable(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object evt = e.getSource();

		if(evt == this.envoyer) {

			this.clientGraphique.envoit();

		}

	}

}
