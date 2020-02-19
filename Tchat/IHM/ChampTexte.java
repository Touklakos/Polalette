/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 12:35:55 CET 2020
*
*/

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChampTexte extends JPanel{


	private JLabel label;
	private JTextField textField;


	public ChampTexte(String label, int taille) {

		this.setMinimumSize(new Dimension(taille, taille));
		this.setMaximumSize(new Dimension(taille, taille));


		this.label = new JLabel(label);
		this.add(this.label);

		this.textField = new JTextField(taille);
		this.add(this.textField);

	}

	public void setEditable(boolean b) {

		this.textField.setEditable(b);

	}

	@Override
	protected void paintComponent(Graphics g){


	}

}
