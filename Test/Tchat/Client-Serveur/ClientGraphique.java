
package client_serveur;

import javax.swing.text.JTextComponent;
import javax.swing.text.JTextComponent;

import ihm.Traiteur;

/**
 * Cette classe represente un client qui vas délaisser le traitement des messages à un traiteur
 */
public class ClientGraphique extends Client {

  private Traiteur traiteur;

  /**
   * Ce constructeur permet de créer un nouveau client graphique<br>
   * Les paramètres(adresse IP, port) par defaut seront pris
   * @param traiteur L'objet qui va traiter les messages
   */
  public ClientGraphique(Traiteur traiteur) throws ServeurDeconnecteException {

    super();
    this.traiteur = traiteur;

  }

  /**
   * Ce constructeur permet de créer un nouveau client graphique
   * @param IP L'adresse IP auquel se connectera le client
   * @param port Le port qu'utilisera le client
   * @param traiteur L'objet qui va traiter les messages
   * @throws ServeurDeconnecteException Si le serveur n'est pas disponible
   */
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
