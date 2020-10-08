package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         //A quoi sert les .init() ?
        String command = "";
        System.out.println(args[0]);
        do {
            System.out.println("Entrez votre commande : ");
            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            switch (command) {
                case "exit":
                    sc.close(); //Ferme le flux d'entrer
                    
                     //Fin de l'exécution du programme
                    break;
            
                default:
                    DBManager.processCommand(command);
                    break;
            }
        } while (true);
    }
}
