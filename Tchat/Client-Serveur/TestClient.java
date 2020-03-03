/*
import java.util.Scanner;
import client_serveur.Client;
import client_serveur.ServeurDeconnecteException;

public class TestClient {

  public static void main(String[] args) {

    try {

      Client c = new Client();

      String s = new String();
      Scanner sc = new Scanner(System.in);

      while(!s.equals("\\CLOSE")) {

        s = sc.nextLine();
        c.envoit(s);

      }

      c.close();

    } catch(ServeurDeconnecteException e) {

      System.out.println("Impossible de se connecter au serveur");

    }

  }

}*/
