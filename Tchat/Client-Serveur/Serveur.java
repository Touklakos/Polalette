
package client_serveur;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Hashtable;


/*
 * Cette classe représente le serveur du tchat
 * Quand on est connecté à celui-ci et que l'on envoit un message il sera transmis à touts les autres clients connectés
 */
public class Serveur implements Ecouteur {

  private static final int FILE_ATTENTE = 100;

  /**
   * Le port par defaut qu'utilisera le serveur
   */
  private static final int PORT_DEFAUT = 2048;

  /**
   * L'IP par defaut surlequel sera le serveur par defaut(Sa machine)
   */
  private static final String IP_DEFAUT = "127.0.0.1";


  private ServerSocket serveur;
  private Set<ProcessusEcoute> clients;
  private Hashtable<ProcessusEcoute, String> nomClients;


  private boolean enMarche = true;

  /**
   * Ce constructeur permet de créer un nouveau serveur
   *
   * Les paramètres(adresse IP, port) par defaut seront pris
   */
  public Serveur() {

    this(IP_DEFAUT, PORT_DEFAUT);

  }

  /**
   * Ce constructeur permet de créer un nouveau client avec les paramètres saisis
   * @param IP L'adresse IP auquel se connectera le client
   * @param port Le port qu'utilisera le client
   */
  public Serveur(String IP, int port) {

    try {

      this.serveur = new ServerSocket(port, FILE_ATTENTE, InetAddress.getByName(IP));

    } catch(UnknownHostException e) {

      System.out.println(e);

    } catch(IOException e) {

      System.out.println(e);

    }

    this.clients = new LinkedHashSet<ProcessusEcoute>();
    this.nomClients = new Hashtable<ProcessusEcoute, String>();

  }


  /**
   * Cette méthode permet d'envoyer un message à chaque client
   * @param message Le messag à envoyer
   */
  public void envoitAll(String message) {

    Set<ProcessusEcoute> temp = this.clients;

    for(ProcessusEcoute s : temp) {

      s.envoit(message);

    }

  }

  /**
   * Cette méthode permet de connaitre le nom du serveur
   * @return Le nom du serveur
   */
  @Override
  public String getNom() {

    return "Serveur";

  }

  public void connectAll(ProcessusEcoute nouveau) {

    Set<ProcessusEcoute> temp = this.clients;
    String tempString = "\\CONNECT:";

    for(ProcessusEcoute s : temp) {

      if(s != nouveau) {

        System.out.println(this.nomClients.get(nouveau));
        System.out.println(this.nomClients.get(s));

        s.envoit("\\CONNECT:" + this.nomClients.get(nouveau));
        tempString += this.nomClients.get(s) + ":";

      } else {

        tempString += this.nomClients.get(nouveau) + ":";

      }

    }

    System.out.println("allo ? : " + tempString);

    nouveau.envoit(tempString);

  }

  public void deconnecte(ProcessusEcoute dernier) {

    Set<ProcessusEcoute> temp = this.clients;

    for(ProcessusEcoute s : temp) {

      if(s != dernier) {

        s.envoit("\\CLOSE:" + this.nomClients.get(dernier));
        dernier.envoit("\\CLOSE:" + this.nomClients.get(s));

      } else {

        dernier.envoit("\\CLOSE:" + this.nomClients.get(dernier));

      }

    }

    dernier.close();
    this.clients.remove(dernier);
    this.nomClients.remove(dernier);

  }

  /**
   * Cette méthode permet de traiter un message envoyé par un client
   * @param message Le message envoyé par le client
   * @param client Le client
   */
  public void traite(String message, ProcessusEcoute processusEcoute) {

    String[] temp = message.split(":");
    System.out.print(message);

    if(message.equals("\\CLOSE")) {

      System.out.println("oscour");

      this.deconnecte(processusEcoute);

    } else if(temp[0].equals("\\CONNECT")) {

      this.nomClients.put(processusEcoute, temp[1]);
      this.connectAll(processusEcoute);

    } else {

      this.envoitAll(this.nomClients.get(processusEcoute) + " : " + message + "\n");

    }

  }

  /**
   * Cette méthode permet d'ouvrir le serveur
   */
  public void open() {

    while(enMarche) {

      try {

        Socket client = this.serveur.accept();
        System.out.println("Connection reçu");

        ProcessusEcoute processusEcoute = new ProcessusEcoute(client, this);
        this.clients.add(processusEcoute);

        Thread t = new Thread(processusEcoute);
        t.start();

      } catch(IOException e) {

        System.out.println(e);

      }

    }

  }

}
