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


public class ZoneTexte extends JPanel {

	private static final int TAILLE_AREA_X = 10;
	private static final int TAILLE_AREA_Y = 30;

	private JLabel label;
	private JTextPane textArea;


	public ZoneTexte(String label, int tailleX, int tailleY) {

		this.setLayout(new BorderLayout());

		this.label = new JLabel(label, SwingConstants.CENTER);
		this.add(this.label, BorderLayout.NORTH);

		this.textArea = new JTextPane();
		this.textArea.setSize(tailleX, tailleY);
		this.textArea.setPreferredSize(new Dimension(tailleX, tailleY));
		this.add(new JScrollPane(this.textArea), BorderLayout.CENTER);

	}

	public void setSelectedTextColor(Color c) {

		this.textArea.setSelectedTextColor(c);

	}


	public void setEditable(boolean b) {

		this.textArea.setEditable(b);

	}

	public JTextPane getJTextArea() {

		return this.textArea;

	}


	public String getText() {

		return this.textArea.getText();

	}

	public void setText(String s) {

		this.textArea.setText(s);

	}

	@Override
	protected void paintComponent(Graphics g){


	}

}
