
package client_serveur;

import javax.swing.text.JTextComponent;
import javax.swing.text.JTextComponent;

import ihm.Traiteur;

/**
 * Cette classe represente un client avec une interface graphique qui veux se connecter à un traiteur
 */
public class ClientGraphique extends Client {

  private Traiteur traiteur;

  /*
   * Ce constructeur permet de créer un nouveau client graphique
   * @param
   */
  public ClientGraphique(Traiteur traiteur) throws ServeurDeconnecteException {

    super();
    this.traiteur = traiteur;

  }

  public ClientGraphique(String IP, int port, Traiteur traiteur) throws ServeurDeconnecteException {

    super(IP, port);
    this.traiteur = traiteur;

  }


  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    this.traiteur.traite(message);

  }

  @Override
  public String getNom() {

    return this.traiteur.getNom();

  }

}
