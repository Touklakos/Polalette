



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

  private Set<Socket> clients;



  public ClientProcessus(Socket client) {

      this.client = client;
      this.clients = new LinkedHashSet<Socket>();


  }


  public void run() {

    while(!this.client.isClosed()) {

      System.out.println("yolo");

      try {

        this.writer = new PrintWriter(this.client.getOutputStream());
        this.reader = new BufferedInputStream(this.client.getInputStream());


        String temp = this.recoit();
        if(temp.equals("CLOSE")) {
          this.client.close();
        } else {
          this.envoitAll(temp);
        }
        Thread.sleep(5000);

      } catch(SocketException e) {

        System.out.println(e);
        break;

      } catch(IOException e) {

        System.out.println(e);

      } catch(InterruptedException e) {

        System.out.println(e);

      }

    }

  }

  public void ajoute(Socket c) {

    this.clients.add(c);

  }

  public void retire(Socket c) {

    this.clients.remove(c);
    if(c.equals(this.client)) {
      try {

        c.close();

      } catch(IOException e) {

        System.out.println(e);

      }

    }

  }



  private void envoit(String message) {

    this.writer.write(message);
    this.writer.flush();

  }

  private void envoitAll(String message) {

    Set<Socket> temp = this.clients;

    for(Socket s : temp) {

      try {

        this.writer = new PrintWriter(s.getOutputStream());
        this.writer.write(message);
        this.writer.flush();

      } catch(IOException e) {

        System.out.println(e);
        this.retire(s);

      }

    }

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
