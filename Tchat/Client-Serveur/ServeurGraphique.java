
package client_serveur;


public class ServeurGraphique extends Serveur {


  /**
   * Cette méthode permet de traiter un message envoyé par un client
   * @param message Le message envoyépar le client
   * @param client Le client
   */
  public void traite(String message, ProcessusEcoute processusEcoute) {

    super.traite(message + "\n", processusEcoute);

  }



}
