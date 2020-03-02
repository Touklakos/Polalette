
package client_serveur;

import javax.swing.text.JTextComponent;
import javax.swing.text.JTextComponent;

import ihm.Tchat;


public class ClientGraphique extends Client {

  private Tchat tchat;

  public ClientGraphique(Tchat tchat) throws ServeurDeconnecteException {

    super();
    this.tchat = tchat;

  }

  public ClientGraphique(String IP, int port, Tchat tchat) throws ServeurDeconnecteException {

    super(IP, port);
    this.tchat = tchat;


  }


  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    this.tchat.getDisscution().setText(this.tchat.getDisscution().getText() + message);

  }

  public void envoit() {

    super.envoit(this.tchat.getMessage().getText());
    this.tchat.getMessage().setText("");

  }

  @Override
  public String getNom() {

    return this.tchat.getNom();

  }

}
