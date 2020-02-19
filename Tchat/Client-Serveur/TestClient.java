
import java.io.IOException;

public class TestClient {

  public static void main(String[] args) {

    Client c = new Client();
    try {

      c.envoit("bonjour");
      c.recoit();
      c.envoit("au revoir");
      c.recoit();

      c.close();

    } catch(IOException e) {

      System.out.println(e);

    }

  }

}
