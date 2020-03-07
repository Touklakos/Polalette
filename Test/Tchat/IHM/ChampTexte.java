/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 12:35:55 CET 2020
*
*/

package ihm;


import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * Cette classe représente une JLabel lié à un JtextField
 * @see JLabel
 * @see JTextField
 */
public class ChampTexte extends JPanel{


	private JLabel label;
	private JTextField textField;

	/**
	 * Ce constructeur permet de créer un nouveau champ texte
	 * @param label La chaine qui figurera dans le JLabel
	 * @param taille La taille du JTextField
	 */
	public ChampTexte(String label, int taille) {

		this.setMinimumSize(new Dimension(taille, taille));
		this.setMaximumSize(new Dimension(taille, taille));


		this.label = new JLabel(label);
		this.add(this.label);

		this.textField = new JTextField(taille);
		this.add(this.textField);

	}

	/**
	 * Cette méthode appelle la méthode du JTextField
	 * @see javax.swing.JTextField#setEditable(boolean)
	 */
	public void setEditable(boolean b) {

		this.textField.setEditable(b);

	}

	/**
	 * Cette méthode appelle la méthode du JTextField
	 * @see javax.swing.JTextField#getText()
	 */
	public String getText() {

		return this.textField.getText();

	}

	@Override
	protected void paintComponent(Graphics g){


	}

}
