
package client_serveur;

/**
 * Cette interface definit les objets qui peuvent ecouter d'autres processus
 */
public interface Ecouteur {

  /**
   * Cette méthode permet de traiter un message reçu par un processus d'ecoute
   * @param message Le message envoyé par le client
   * @param processusEcoute Le processus d'ecoute qui à reçu le message
   */
  public void traite(String message, ProcessusEcoute processusEcoute);

  /**
   * Cette méthode permet de connaitre le nom de l'ecouteur
   * @return Le nom de l'ecouteur
   */
  public String getNom();

}
