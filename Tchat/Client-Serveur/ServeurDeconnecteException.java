
package client_serveur;


/**
 * Cette exception est levé quand un client essaye de se conecter alors que le serveur n'est pas disponible
 */
public class ServeurDeconnecteException extends Exception {

  public ServeurDeconnecteException() {

    super();

  }

  public ServeurDeconnecteException(String s) {

    super(s);

  }

}
