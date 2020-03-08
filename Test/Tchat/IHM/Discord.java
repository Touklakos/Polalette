/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:23:00 CET 2020
*
*/

package ihm;


import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Cette classe représente une Connection et un Tchat<br>
 * Nommé "Discord" en référence au Chat vocal et textuel du même nom
 */
public class Discord extends JPanel {

	private Connection connection;
	private Tchat tchat;

	/**
	 * Ce constructeur permet de créer un nouveau discord
	 * @param fenetre La fenetre sur laquelle va être affiché le discord
	 */
	public Discord(Fenetre fenetre) {

		this.tchat = new Tchat(fenetre);
		this.connection = new Connection(this.tchat, fenetre);
		this.add(this.connection);
		this.add(this.tchat);

	}

	@Override
	protected void paintComponent(Graphics g){


	}

}
