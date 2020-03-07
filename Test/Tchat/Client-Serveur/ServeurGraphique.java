
package client_serveur;


/**
 * Cette classe représente le serveur du tchat<br>
 * Quand on est connecté à celui-ci et que l'on envoit un message il sera transmis à touts les autres clients connectés
 * @deprecated
 */
public class ServeurGraphique extends Serveur {


  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    super.traite(message, processusEcoute);

  }

}
