
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
   * Ce constructeur permet de créer un nouveau client
   *
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

    return "";
    
  }

  /**
   * Cette méthode permet au client d'envoyer un message au serveur
   * @param message Le message qui sera envoyé au serveur
   */
  public void envoit(String message) {

    this.ecoute.envoit(message);

  }

  /**
   * Cette méthode permet de connaitre la socket qu'utilise le client
   * @return La socket utilisé par le client
   */
  public Socket getSocket() {

    return this.client;

  }

  /**
   * Cette fonction permet au client de traiter les messages qu'il recoit du processus d'écoute
   * @param message Le message reçu
   * @param processusEcoute Le processus qui a reçu et transmis le message
   */
  public void traite(String message, ProcessusEcoute processusEcoute) {

    System.out.println(message);

  }

  /**
   * Cette méthode permet de deconnecter le client envoyant un message spécial au serveur
   */
  public void close() {

    try {

      this.envoit("CLOSE");
      this.client.close();
      this.ecoute.close();

    } catch(IOException e) {

      System.out.println(e);

    }

  }

}
