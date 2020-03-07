
package client_serveur;



import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Cette classe represente un client qui veux se connecter à un tchat
 */
public class Client implements Ecouteur {

  /**
   * Le port par defaut qu'utilisera le client
   */
  private static final int PORT_DEFAUT = 2048;

  /**
   * L'IP par defaut auquel le client se connectera par defaut(Sa machine)
   */
  private static final String IP_DEFAUT = "127.0.0.1";



  private Socket client;

  private ProcessusEcoute ecoute;

  /**
   * Ce constructeur permet de créer un nouveau client<br>
   * Les paramètres(adresse IP, port) par defaut seront pris
   * @throws ServeurDeconnecteException Si le serveur n'est pas disponible
   */
  public Client() throws ServeurDeconnecteException {

    this(IP_DEFAUT, PORT_DEFAUT);

  }

  /**
   * Ce constructeur permet de créer un nouveau client avec les paramètres saisis
   * @param IP L'adresse IP auquel se connectera le client
   * @param port Le port qu'utilisera le client
   * @throws ServeurDeconnecteException Si le serveur n'est pas disponible
   */
  public Client(String IP, int port) throws ServeurDeconnecteException {

    try {

      this.client = new Socket(IP, port);
      this.ecoute = new ProcessusEcoute(this.client, this);

      Thread t = new Thread(this.ecoute);
      t.start();

    } catch(UnknownHostException e) {

      System.out.println(e);

    } catch(IOException e) {

      throw new ServeurDeconnecteException("public Client(String IP, int port) throws ServeurDeconnecteException {");

    }

  }

  @Override
  public String getNom() {

    return "Client";

  }


  /**
   * Cette méthode permet au client d'envoyer un message au serveur
   * @param message Le message qui sera envoyé au serveur
   */
  public void envoit(String message) {

    this.ecoute.envoit(message);

  }


  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    System.out.println(message);

  }

  /**
   * Cette méthode permet de deconnecter le client en envoyant un message spécial au serveur
   */
  public void close() {

    try {

      this.envoit(MotCle.CLOSE.getCommand());
      this.client.close();
      this.ecoute.close();

    } catch(IOException e) {

      System.out.println(e);

    }

  }

}
