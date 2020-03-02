
package client_serveur;

import javax.swing.text.JTextComponent;

public class ClientGraphique extends Client {

  private JTextComponent recup;
  private JTextComponent envoit;
  private String nom;

  public ClientGraphique(JTextComponent recup, JTextComponent envoit, String nom) throws ServeurDeconnecteException {

    super();
    this.recup = recup;
    this.envoit = envoit;
    this.nom = nom;

  }

  @Override
  public String getNom() {

    return this.nom;

  }


  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    this.envoit.setText(this.envoit.getText() + message);

  }

  public void envoit() {

    super.envoit(this.nom + " : " + this.recup.getText());
    this.recup.setText("");

  }
}
