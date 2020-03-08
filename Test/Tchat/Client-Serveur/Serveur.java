
package client_serveur;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Hashtable;


/**
 * Cette classe représente le serveur du tchat<br>
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
   * Ce constructeur permet de créer un nouveau serveur<br>
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
  private void envoitAll(String message) {

    Set<ProcessusEcoute> temp = this.clients;

    for(ProcessusEcoute s : temp) {

      s.envoit(message);

    }

  }


  @Override
  public String getNom() {

    return "Serveur";

  }

  private void connectAll(ProcessusEcoute nouveau) {

    Set<ProcessusEcoute> temp = this.clients;
    String tempString = MotCle.CONNECT.getCommand() + ":";

    for(ProcessusEcoute s : temp) {

      tempString += this.nomClients.get(s) + ":";

    }

    for(ProcessusEcoute s : temp) {

      s.envoit(tempString);

    }

  }

  private void deconnecte(ProcessusEcoute dernier) {

    if(!(this.nomClients.containsKey(dernier))) {

      dernier.envoit(MotCle.CLOSE.getCommand() + ":" + this.nomClients.get(dernier));

    } else {

      Set<ProcessusEcoute> temp = this.clients;

      for(ProcessusEcoute s : temp) {

        if(s != dernier) {

          s.envoit(MotCle.CLOSE.getCommand() + ":" + this.nomClients.get(dernier));
          dernier.envoit(MotCle.CLOSE.getCommand() + ":" + this.nomClients.get(s));

        } else {

          dernier.envoit(MotCle.CLOSE.getCommand() + ":" + this.nomClients.get(dernier));

        }

      }

    }

    dernier.close();
    this.clients.remove(dernier);
    this.nomClients.remove(dernier);

  }

  private void refuse(ProcessusEcoute processusEcoute, String raison) {

    processusEcoute.envoit(MotCle.REFUSE.getCommand() + ":" + raison);
    processusEcoute.close();
    this.clients.remove(processusEcoute);

  }

  private boolean aRecuCommande(String message, MotCle commande) {

    System.out.println("Le message du regexp : " + message);
    System.out.println("La regexp : " + commande.getRegexp());

    if(message.matches(commande.getRegexp())) {

      return true;

    }

    return false;

  }

  private void traiteClose(ProcessusEcoute processusEcoute) {
    this.deconnecte(processusEcoute);
  }

  private void traiteConnect(String message, ProcessusEcoute processusEcoute) {

    String[] temp = message.split(":");


    if(this.nomClients.contains(temp[1])) {

      this.refuse(processusEcoute, "Le nom existe deja");

    } else {

    //  if(this.nomClients.containsKey(processusEcoute)) {
    //
    //    this.nomClients.remove(processusEcoute);
    //
    //  }

      this.nomClients.put(processusEcoute, temp[1]);
      this.connectAll(processusEcoute);

    }

  }

  private void traiteMsg(String message, ProcessusEcoute processusEcoute) {

    String[] temp = message.split(":");
    for(ProcessusEcoute p : this.nomClients.keySet()) {
      if(this.nomClients.get(p).equals(temp[1])) {

        p.envoit(this.nomClients.get(processusEcoute) + ":" + temp[2] + "\n");
        processusEcoute.envoit(this.nomClients.get(processusEcoute) + ":" + temp[2] + "\n");

      }
    }
  }

  private void traiteHelp(ProcessusEcoute processusEcoute) {

    String messageCommande = new String("");

    for(MotCle m : MotCle.commandeUtilisateur()) {
      messageCommande += "\n" + m.getCommand() + "\n" + m.getDescription() + "\n";
    }

    processusEcoute.envoit(this.getNom() + ": Liste des commandes utilisable\n" + messageCommande);

  }


  private void traiteNormal(String message, ProcessusEcoute processusEcoute) {

    String[] temp = message.split(":");

    this.envoitAll(this.nomClients.get(processusEcoute) + ":" + temp[1] + "\n");

  }

  @Override
  public void traite(String message, ProcessusEcoute processusEcoute) {

    System.out.print(message);

    if(this.aRecuCommande(message, MotCle.CLOSE)) this.traiteClose(processusEcoute);
    else if(this.aRecuCommande(message, MotCle.CONNECT)) this.traiteConnect(message, processusEcoute);
    else if(this.aRecuCommande(message, MotCle.MSG)) this.traiteMsg(message, processusEcoute);
    else if(this.aRecuCommande(message, MotCle.HELP)) this.traiteHelp(processusEcoute);
    else if(this.aRecuCommande(message, MotCle.NORMAL)) this.traiteNormal(message, processusEcoute);
    else this.deconnecte(processusEcoute);

  }

  /**
   * Cette méthode permet d'ouvrir le serveur
   */
  public void open() {

    while(this.enMarche) {

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
