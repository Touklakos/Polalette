

/**
 * Cette interface definit les objets qui peuvent ecouter d'autre processus
 */
public interface Ecouteur {

  public void traite(String message, ProcessusEcoute client);

}
