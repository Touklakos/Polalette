/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:17:19 CET 2020
*
*/


import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class ZoneTexte extends JPanel {
	
	private static final int TAILLE_AREA_X = 10;
	private static final int TAILLE_AREA_Y = 20;

	private JLabel label;
	private JTextArea textArea;


	public ZoneTexte(String label) {

		this.setLayout(new BorderLayout());

		this.label = new JLabel(label, SwingConstants.CENTER);
		this.add(this.label, BorderLayout.NORTH);
		
		this.textArea = new JTextArea(TAILLE_AREA_X, TAILLE_AREA_Y);
		this.add(this.textArea, BorderLayout.CENTER);
		
	}

	
	public void setEditable(boolean b) {

		this.textArea.setEditable(b);
		
	}

	@Override
	protected void paintComponent(Graphics g){
		
		
	}
	
}