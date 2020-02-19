

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.LinkedHashSet;


public class Serveur {

  private static final int FILE_ATTENTE = 100;
  private static final int PORT_DEFAUT = 2048;
  private static final String IP_DEFAUT = "127.0.0.1";


  private ServerSocket serveur;
  private Set<ClientProcessus> clients;

  private boolean enMarche = true;

  public Serveur() {

    this(IP_DEFAUT, PORT_DEFAUT);

  }


  public Serveur(String IP, int port) {

    try {

      this.serveur = new ServerSocket(port, FILE_ATTENTE, InetAddress.getByName(IP));

    } catch(UnknownHostException e) {

      System.out.println(e);

    } catch(IOException e) {

      System.out.println(e);

    }

    this.clients = new LinkedHashSet<ClientProcessus>();

  }

  public void open() {

    while(enMarche) {

      try {

        Socket client = this.serveur.accept();
        System.out.println("Connection reçu");
        ClientProcessus clientProcessus = new ClientProcessus(client);
        Thread t = new Thread(clientProcessus);
        this.clients.add(clientProcessus);
        for(ClientProcessus c : clients) {

          c.ajoute(client);

        }
        t.start();

      } catch(IOException e) {

        System.out.println(e);

      }

    }

  }

}
