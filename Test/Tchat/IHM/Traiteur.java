
package ihm;

/**
 * Cette interface definit les objets qui peuvent traiter des messages
 */
public interface Traiteur {

  /**
   * Cette méthode permet de traiter un message reçu
   * @param message Le message envoyé par le client
   */
  public void traite(String message);

  /**
   * Cette méthode permet de connaitre le nom du traiteur
   * @return Le nom du traiteur
   */
  public String getNom();

}
