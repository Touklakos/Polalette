/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:17:19 CET 2020
*
*/

package ihm;


import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


/**
 * Cette classe représente une JLabel lié à un JTextPane
 * @see JLabel
 * @see JTextPane
 */
public class ZoneTexte extends JPanel {

	private static final int TAILLE_AREA_X = 10;
	private static final int TAILLE_AREA_Y = 30;

	private JLabel label;
	private JTextPane textArea;

	/**
	 * Ce constructeur permet de créer une nouvelle ZoneTexte
	 * @param label La chaine qui figurera dans le JLabel
	 * @param posX La position en abscisse du JTextField
	 * @param posY La position en ordonnée du JTextField
	 * @param tailleX La taille en abscisse du JTextField
	 * @param tailleY La taille en ordonnée du JTextField
	 */
	public ZoneTexte(String label, int posX, int posY, int tailleX, int tailleY) {

		this.setLayout(new BorderLayout());


		JPanel temp = new JPanel();
		temp.setLayout(null);

		this.label = new JLabel(label, SwingConstants.CENTER);
		this.add(this.label, BorderLayout.NORTH);

		this.textArea = new JTextPane();
		this.textArea.setBounds(posX, posY, tailleX, tailleY);
		temp.add(this.textArea);
		temp.setPreferredSize(new Dimension(tailleX, tailleY));
		this.add(new JScrollPane(temp), BorderLayout.CENTER);

	}

	/**
	 * Cette méthode appelle la méthode du JTextPane
	 * @see javax.swing.JTextPane#setEditable(boolean)
	 */
	public void setEditable(boolean b) {

		this.textArea.setEditable(b);

	}

	/**
	 * Cette méthode permet de connaitre le JTextPane du la ZoneTexte
	 * @return Le JTextPane
	 */
	public JTextPane getJTextArea() {

		return this.textArea;

	}

	/**
	 * Cette méthode permet d'ajouter du texte avec une certaine couleur
	 * @param message Le message à ajouter
	 * @param color La couleur du message
	 */
	public void append(String message, Color color) {

		this.textArea.setEditable(true);

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
		this.textArea.setCharacterAttributes(aset, false);
		this.textArea.replaceSelection(message);

		this.textArea.setEditable(false);


	}

	/**
	 * Cette méthode permet de connaitre le texte dans la ZoneTexte
	 * @return Le texte présente dans la ZoneTexte
	 */
	public String getText() {

		return this.textArea.getText();

	}

	/**
	 * Cette méthode permet de modifier le texte dans la ZoneTexte
	 * @param s Le texte à mettre dans la ZoneTexte
	 */
	public void setText(String s) {

		this.textArea.setText(s);

	}

	@Override
	protected void paintComponent(Graphics g){


	}

}
