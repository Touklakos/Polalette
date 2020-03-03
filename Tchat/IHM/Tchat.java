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


import java.util.List;
import java.util.LinkedList;



import client_serveur.ClientGraphique;
import client_serveur.ServeurDeconnecteException;



public class Tchat extends JPanel implements ActionListener, Traiteur {


	private ZoneTexte connectes;
	private ZoneTexte discussion;
	private ZoneTexte message;
	private JButton envoyer;

	private boolean actif;
	private GroupLayout layout;

	private ClientGraphique clientGraphique;
	private String nom;

	private List<String> nomConnectes;


	public Tchat() {

		this.nomConnectes = new LinkedList<String>();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.connectes = new ZoneTexte("Connectés", 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.connectes.setEditable(false);
		this.add(this.connectes, c);

		this.discussion = new ZoneTexte("Discussion", 16, 50);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.discussion.setEditable(false);
		this.add(this.discussion, c);

		this.message = new ZoneTexte("Message", 4, 50);
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

	public void setConnectes(List<String> nomConnectes) {

		this.connectes.setText("");

		for(String s : 	nomConnectes) {

			this.connectes.setText(this.connectes.getText() + s + "\n");

		}

	}


	public void traite(String message) {

		String[] temp;

		temp = message.split(":");
		System.out.println(message);

		if(temp[0].equals("\\CONNECT")) {

			for(String s : temp) {

				if(s != temp[0]) {

					this.nomConnectes.add(s);

				}

			}

			this.setConnectes(this.nomConnectes);

		} else if(temp[0].equals("\\CLOSE")) {

			for(String s : temp) {

				if(s != temp[0]) {

					this.nomConnectes.remove(s);

				}

			}

			this.setConnectes(this.nomConnectes);

		} else {

			this.discussion.setSelectedTextColor(Color.RED);
			this.discussion.setText(this.discussion.getText() + message);

		}

	}


	public ZoneTexte getMessage() {

		return this.message;

	}

	public ZoneTexte getDisscution() {

		return this.discussion;

	}

	public ZoneTexte getConnectes() {

		return this.connectes;

	}

	public String getNom() {

		return this.nom;

	}

	public void deconnecte() {

		this.clientGraphique.envoit("\\CLOSE");
		this.clientGraphique.close();
		this.message.setText("");
		this.discussion.setText("");
		this.connectes.setText("");
		this.desactive();

	}

	public void connecte(String nom, String IP, String port) throws ServeurDeconnecteException {

		this.nom = nom;

		if(!this.actif) {

			try {

				this.clientGraphique = new ClientGraphique(this);
				this.reactive();
				this.clientGraphique.envoit("\\CONNECT:" + this.getNom());

			} catch(ServeurDeconnecteException e) {

				throw e;

			}

		}

	}

	public void envoit() {

    this.clientGraphique.envoit(this.getMessage().getText());
    this.getMessage().setText("");

  }

	@Override
	protected void paintComponent(Graphics g){


	}


	private void modifEditable(boolean b) {

		this.message.setEditable(b);
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

			this.envoit();

		}

	}

}
