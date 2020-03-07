
package client_serveur;

/**
 * Cette interface definit les objets qui peuvent ecouter d'autres processus
 */
public interface Ecouteur {

  public void traite(String message, ProcessusEcoute client);
  public String getNom();

}
