
package client_serveur;



import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import java.util.LinkedHashSet;


/**
 * Cette classe permet de recevoir et d'emettre des messages avec un socket
 */
public class ProcessusEcoute implements Runnable{

  private Socket ecoute;
  private PrintWriter writer;
  private BufferedInputStream reader;

  private Ecouteur ecouteur;

  private boolean enMarche;


  /**
   * Cette méthode permet de créer un nouveau processus d'écoute<br>
   * Après la création de l'objet il doit être mis dans un Thread puis lancé
   * @param ecoute La socket d'emission et de récéption de message
   * @param ecouteur L'objet qui va traiter les messages reçu
   */
  public ProcessusEcoute(Socket ecoute, Ecouteur ecouteur) {

      this.ecoute = ecoute;
      this.ecouteur = ecouteur;
      this.enMarche = true;

      try {

        this.writer = new PrintWriter(this.ecoute.getOutputStream());
        this.reader = new BufferedInputStream(this.ecoute.getInputStream());

      } catch(IOException e) {

        System.out.println(e);

      }
  }

  /**
   * Cette méthode est appelé automatiquement quand le Thread est lancé
   */
  public void run() {

    while(this.enMarche) {

      try {

        String temp = this.recoit();
        this.ecouteur.traite(temp, this);

      } catch(SocketException e) {

        System.out.println(e);
        break;

      } catch(IOException e) {

        System.out.println(e);

      }

    }

    System.out.println("salut !");

  }

  /**
   * Cette méthode permet d'arrêter le processus d'écoute
   */
  public void close() {

    this.enMarche = false;

  }


  /**
   * Cette méthode permet de connaître le socket du processus
   * @return Le socket
   */
  public Socket getEcoute() {

    return this.ecoute;

  }

  /**
   * Cette méthode permet de connaitre le nom de l'ecouteur
   * @return Le nom de l'ecouteur
   */
  public String getNom() {

    return this.ecouteur.getNom();

  }


  /**
   * Cette méthode permet d'envoyer un message au socket
   * @param message Le message à envoyer
   */
  public void envoit(String message) {

    this.writer.write(message);
    this.writer.flush();

  }

  private String recoit() throws IOException {

    try {

      String reponse = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      if(stream != -1) {
        reponse = new String(b, 0, stream);
      }
      return reponse;

    } catch(IOException e) {

      throw e;

    }
  }
}
