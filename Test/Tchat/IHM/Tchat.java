/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:16:57 CET 2020
*
*/

package ihm;


import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;


import java.util.List;
import java.util.LinkedList;
import java.util.Hashtable;



import client_serveur.ClientGraphique;
import client_serveur.ServeurDeconnecteException;
import client_serveur.MotCle;


/**
 * Cette classe représente une "Tchating room"<br>
 * Fonctionnalité :<br>
 * -- Envoyer des messages généraux<br>
 * -- Envoyer des messages privés<br>
 */
public class Tchat extends JPanel implements ActionListener, Traiteur {


	private static final String MESSAGE_PRIVE = "message privé";
	private static final String MESSAGE_GROUPE = "groupe";

	private static int nb = 1;


	private ZoneTexte connectes;
	private ZoneTexte discussion;
	private ZoneTexte message;
	private JComboBox<String> typeMessage;
	private JComboBox<String> cibleMsg;
	private JButton envoyer;

	private boolean actif;
	private GroupLayout layout;

	private ClientGraphique clientGraphique;
	private String nom;

	private Fenetre fenetre;


	private Hashtable<String, Color> nomConnectes;


	private static Color newColor() {

		Tchat.nb++;
		if(Tchat.nb > 7) Tchat.nb = 1;

		if(Tchat.nb == 1) return Color.BLUE;
		else if(Tchat.nb == 2) return Color.CYAN;
		else if(Tchat.nb == 3) return Color.GREEN;
		else if(Tchat.nb == 4) return Color.MAGENTA;
		else if(Tchat.nb == 5) return Color.ORANGE;
		else if(Tchat.nb == 6) return Color.PINK;
		else if(Tchat.nb == 7) return Color.RED;
		else return Color.BLACK;

	}


	/**
	 * Ce constructeur permet de créer un nouveau Tchat
	 * @param fenetre Le fenetre sur laquelle le Tchat s'affiche
	 */
	public Tchat(Fenetre fenetre) {

		this.fenetre = fenetre;

		this.nomConnectes = new Hashtable<String, Color>();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.connectes = new ZoneTexte("Connectés", 0, 0, 100, 300);
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

		this.discussion = new ZoneTexte("Discussion", 0, 0, 500, 250);
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

		this.message = new ZoneTexte("Message", 0, 0, 500, 50);
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

		this.typeMessage = new JComboBox<String>();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.typeMessage.setSize(new Dimension(3, 6));
		this.typeMessage.addItem(Tchat.MESSAGE_GROUPE);
		this.typeMessage.addItem(Tchat.MESSAGE_PRIVE);
		this.add(this.typeMessage, c);

		this.cibleMsg = new JComboBox<String>();
		c.gridx = 2;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 15, 0, 0);
		c.fill = GridBagConstraints.BOTH;
		this.typeMessage.setSize(new Dimension(3, 6));
		this.cibleMsg.addItem("");
		this.cibleMsg.setEditable(false);
		this.cibleMsg.setEnabled(false);
		this.add(this.cibleMsg, c);

		this.envoyer = new JButton("Envoyer");
		c.gridx = 3;
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
		this.typeMessage.addActionListener(this);
		this.desactive();

	}


	private void setConnectes(Hashtable<String, Color> nomConnectes) {

		this.connectes.setText("");

		for(String s : 	nomConnectes.keySet()) {

			this.connectes.append(s + "\n", nomConnectes.get(s));

		}

	}


  private boolean aRecuCommande(String message, MotCle commande) {

    String[] temp = message.split(":");
    if(temp[0].equals(commande.getCommand())) {

      return true;

    }

    return false;

  }

	private void traiteRefuse(String message) {

		this.discussion.setText(message);

  }

	private void traiteClose(String message) {

		String[] temp = message.split(":");
		for(String s : temp) {
			if(s != temp[0]) {
				this.nomConnectes.remove(s);
				this.cibleMsg.removeItem(s);
			}
		}
		this.setConnectes(this.nomConnectes);
		this.fenetre.repaint();
  }

  private void traiteConnect(String message) {

		Object save = this.cibleMsg.getSelectedItem();
		this.cibleMsg.removeAllItems();
    String[] temp = message.split(":");
		for(String s : temp) {
			if(s != temp[0]) {
				if(!(this.nomConnectes.containsKey(s))) {
					this.nomConnectes.put(s, Tchat.newColor());
				}
				if(!(s.equals(this.getNom()))) {
					this.cibleMsg.insertItemAt(s, this.cibleMsg.getItemCount());
				}
			}
		}
		this.cibleMsg.setSelectedItem(save);
		this.setConnectes(this.nomConnectes);
		this.fenetre.repaint();
  }

	@Override
	public void traite(String message) {

		System.out.println(message);
		String[] temp = message.split(":");


		if(this.aRecuCommande(message, MotCle.CONNECT)) this.traiteConnect(message);
		else if(this.aRecuCommande(message, MotCle.CLOSE)) this.traiteClose(message);
		else if(this.aRecuCommande(message, MotCle.REFUSE)) this.traiteRefuse(message);

		else {

			this.discussion.append(message + "\n", this.nomConnectes.get(temp[0]));

		}

	}


	/**
	 * Cette méthode permet de renvoyer la zone de texte qui contient les messages à envoyer
	 * @return La zone de texte
	 */
	public ZoneTexte getMessage() {

		return this.message;

	}

	/**
	 * Cette méthode permet de renvoyer la zone de texte qui contient les messages reçu
	 * @return La zone de texte
	 */
	public ZoneTexte getDisscution() {

		return this.discussion;

	}

	/**
	 * Cette méthode permet de renvoyer la zone de texte qui contient les personnes connectés
	 * @return La zone de texte
	 */
	public ZoneTexte getConnectes() {

		return this.connectes;

	}

	/**
	 * Cette méthode permet de connaitre le nom de la personne qui utilise le Tchat
	 * @return Le nom de la personne
	 */
	public String getNom() {

		return this.nom;

	}

	/**
	 * Cette méthode permet au Tchat de se deconnecter
	 */
	public void deconnecte() {

		this.clientGraphique.envoit(MotCle.CLOSE.getCommand());
		this.clientGraphique.close();
		this.message.setText("");
		this.discussion.setText("");
		this.connectes.setText("");
		this.nomConnectes.clear();
		this.cibleMsg.removeAllItems();
		this.desactive();

	}

	/**
	 * Cette méthode permet au Tchat de se connecter
	 * @param nom Le nom qu'utilisera le Tchat
	 * @param IP L'adresse IP sur laquelle va se connecter le Tchat
	 * @param port Le port sur lequel va se connecter le Tchat
	 * @throws ServeurDeconnecteException Si le Tchat ne peut pas se connecter
	 */
	public void connecte(String nom, String IP, String port) throws ServeurDeconnecteException {

		this.nom = nom;

		if(!this.actif) {

			try {

				this.clientGraphique = new ClientGraphique(IP, Integer.parseInt(port), this);
				this.reactive();
				this.clientGraphique.envoit(MotCle.CONNECT.getCommand() + ":" + this.getNom());

			} catch(ServeurDeconnecteException e) {

				throw e;

			}

		}

	}


	private void envoit() {

		if(this.typeMessage.getSelectedItem().equals(Tchat.MESSAGE_PRIVE)) {

			this.clientGraphique.envoit(MotCle.MSG.getCommand() + ":" + this.cibleMsg.getSelectedItem() + ":" + this.getMessage().getText());
			this.getMessage().setText("");

		} else if(this.typeMessage.getSelectedItem().equals(Tchat.MESSAGE_GROUPE)) {

			this.clientGraphique.envoit(MotCle.NORMAL.getCommand() + ":" + this.getMessage().getText());
			this.getMessage().setText("");

		}

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

		} else if(evt == this.typeMessage) {

			if(this.typeMessage.getSelectedItem().equals(Tchat.MESSAGE_PRIVE)) {

				this.cibleMsg.removeItem("");
				this.cibleMsg.setEnabled(true);

			} else if(this.typeMessage.getSelectedItem().equals(Tchat.MESSAGE_GROUPE)) {

				this.cibleMsg.addItem("");
				this.cibleMsg.setSelectedItem("");
				this.cibleMsg.setEnabled(false);

			}

		}

	}

}
