

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
 * Cette classe permet à un client de recevoir des messages et de les transmettres
 */
public class ClientEcoute implements Runnable{

  private Client client;
  private BufferedInputStream reader;
  private boolean enMarche;


  public ClientEcoute(Client client) {

    this.client = client;
    this.enMarche = true;

    try {

      this.reader = new BufferedInputStream(this.client.getSocket().getInputStream());

    } catch(IOException e) {

      System.out.println(e);

    }

  }

  public void run() {

    while(this.enMarche) {

      try {

        System.out.println(this.recoit());

      } catch(IOException e) {

        System.out.println(e);

      }

    }

  }

  public void close() {

    this.enMarche = false;

  }

  public String recoit() throws IOException {

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
