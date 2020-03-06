
package client_serveur;

import java.util.List;
import java.util.LinkedList;

public enum MotCle {

  CLOSE ("CLOSE", "CLOSE", "Permet de se deconnecter du serveur", false),
  CONNECT ("CONNECT", "CONNECT:.+", "Permet de se connecter au serveur", false),
  HELP ("HELP", "HELP", "Permet d'afficher les commandes", true),
  MSG ("MSG", "MSG:.+:.+", "Permet d'envoyer un message privé\nUtilisation :\n\\MSG:nom du destinataire:Le message", true);


  private String commande = "";
  private String regexp = "";
  private String description = "";
  private boolean commandeUtilisateur = false;


  MotCle(String commande, String regexp, String description, boolean commandeUtilisateur){

    this.commande = commande;
    this.regexp = regexp;
    this.description = description;
    this.commandeUtilisateur = commandeUtilisateur;

  }

  public String getCommand(){

   return this.commande;

  }

  public String getRegexp(){

   return this.regexp;

  }

  public String getDescription(){

   return this.description;

  }

  public boolean estCommandeUtilisateur(){

   return this.commandeUtilisateur;

  }

  public static List<MotCle> commandeUtilisateur(){

    List<MotCle> motsCle = new LinkedList<MotCle>();

    for(MotCle m : MotCle.values()) {

      if(m.estCommandeUtilisateur()) {

        motsCle.add(m);

      }

    }

    return motsCle;

  }

}
