/**
* @author LeNomDeLEtudiant
* @version 0.1 : Date : Mon Feb 17 13:16:57 CET 2020
*
*/

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



public class Tchat extends JPanel implements ActionListener {


	private ZoneTexte connectes;
	private ZoneTexte discussion;
	private ZoneTexte message;
	private JButton envoyer;

	private boolean actif;
	private GroupLayout layout;

	
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
		
		
		this.desactive();
		
	}

	@Override
	protected void paintComponent(Graphics g){

		
	}

	private void modifEditable(boolean b) {

		this.connectes.setEditable(b);
		this.discussion.setEditable(b);
		this.message.setEditable(b);
		
	}

	private void desactive() {

		this.modifEditable(false);
		this.actif = false;
		this.setVisible(false);
		this.repaint();
		
	}
	
	private void reactive() {

		this.modifEditable(true);
		this.actif = true;
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(!this.actif) {

			this.reactive();
			
		} else if(this.actif) {

			this.desactive();
			
		}
 
	}
	
}