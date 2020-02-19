

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

  private static final int FILE_ATTENTE = 10;
  private static final int PORT_DEFAUT = 2048;
  private static final String IP_DEFAUT = "127.0.0.1";


  private Socket client;
  private PrintWriter writer;
  private BufferedInputStream reader;

  public Client() {

    this(IP_DEFAUT, PORT_DEFAUT);

  }


  public Client(String IP, int port) {

    try {

      this.client = new Socket(IP, port);
      this.writer = new PrintWriter(this.client.getOutputStream(), true);
      this.reader = new BufferedInputStream(this.client.getInputStream());

    } catch(UnknownHostException e) {

      System.out.println(e);

    } catch(IOException e) {

      System.out.println(e);

    }

  }

  public void envoit(String message) {

    this.writer.write(message);
    this.writer.flush();

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

  public void close() {

    try {

      this.envoit("CLOSE");
      this.client.close();

    } catch(IOException e) {

      System.out.println(e);

    }

  }

}
