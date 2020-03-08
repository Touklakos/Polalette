
package client_serveur;

import java.util.List;
import java.util.LinkedList;

/**
 * Cette énumération énumère les messages spéciaux que peuvent s'envoyer les serveur et clients
 */
public enum MotCle {

  /**
   * La constante à utiliser quand on veut se deconnecter
   */
  CLOSE ("CLOSE", "^CLOSE", "Permet de se deconnecter du serveur", false),

  /**
   * La constante à utiliser quand on veut se connecter
   */
  CONNECT ("CONNECT", "^CONNECT:.+", "Permet de se connecter au serveur", false),

  /**
   * La constante à utiliser quand l'utilisateur veut voir les commandes auquel il a accès
   */
  HELP ("HELP", "^HELP", "Permet d'afficher les commandes", true),

  /**
   * La constante à utiliser quand l'utilisateur veut envoyer un message privé
   */
  MSG ("MSG", "^MSG:.+:.+", "Permet d'envoyer un message privé\nUtilisation :\n\\MSG:nom du destinataire:Le message", false),

  /**
   * La constante à utiliser quand le serveur refuse un client
   */
  REFUSE ("REFUSE", "^REFUSE:.+", "Permet au serveur de refuser des clients pour les raisons qu'il définis", false),

  /**
   * La constante à utiliser quand le client veut envoyer un message normal au serveur
   */
  NORMAL ("NORMAL", "^NORMAL:.+", "Permet au serveur de refuser des clients pour les raisons qu'il définis", false);


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

  /**
   * Cette méthode permet de connaitre la commande à utiliser pour envoyer des messages spéciaux
   * @return La commande à utiliser
   */
  public String getCommand(){

   return this.commande;

  }

  /**
   * Cette méthode permet de connaitre l'expression régulière qui correspond à l'utilisation de la commande
   * @return L'expression régulière qui doit être suivi pour utiliser la commande
   */
  public String getRegexp(){

   return this.regexp;

  }

  /**
   * Cette méthode permet de connaitre la description de la commande
   * @return La description de la commande
   */
  public String getDescription(){

   return this.description;

  }

  /**
   * Cette méthode permet de savoir si la commande est une commande utilisateur
   * @return true si la commande peut être utilisé par l'utilisateur, false sinon
   */
  public boolean estCommandeUtilisateur(){

   return this.commandeUtilisateur;

  }

  /**
   * Cette méthode permet connaitre les commandes utilisateur
   * @return La liste des commandes utilisateur
   */
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
