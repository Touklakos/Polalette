
package client_serveur;

import javax.swing.text.JTextComponent;
import javax.swing.text.JTextComponent;

import ihm.Tchat;

/**
 * Cette classe represente un client avec une interface graphique qui veux se connecter à un tchat
 */
public class ClientGraphique extends Client {

  private Tchat tchat;

  /*
   * Ce constructeur permet de créer un nouveau client graphique
   * @param
   */
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

    this.tchat.traite(message);

  }

  @Override
  public String getNom() {

    return this.tchat.getNom();

  }

}
