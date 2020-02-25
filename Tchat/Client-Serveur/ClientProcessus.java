



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


public class ClientProcessus implements Runnable{

  private Socket client;
  private PrintWriter writer;
  private BufferedInputStream reader;
  private Serveur serveur;

  private boolean enMarche;



  public ClientProcessus(Socket client, Serveur serveur) {

      this.client = client;
      this.serveur = serveur;
      this.enMarche = true;

      try {

        this.writer = new PrintWriter(this.client.getOutputStream());
        this.reader = new BufferedInputStream(this.client.getInputStream());

      } catch(IOException e) {

        System.out.println(e);

      }
  }


  public void run() {

    while(this.enMarche) {

      try {

        String temp = this.recoit();
        System.out.println(temp);

        this.serveur.traite(temp, this);

      } catch(SocketException e) {

        System.out.println(e);
        break;

      } catch(IOException e) {

        System.out.println(e);

      }

    }

    System.out.println("salut !");

  }

  public void close() {

    this.enMarche = false;

  }


  public Socket getClient() {

    return this.client;

  }



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
