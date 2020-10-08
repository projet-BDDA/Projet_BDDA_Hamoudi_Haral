package src;

import java.util.Vector;

public class DBManager {
    
    public void init() {
        DBInfo.init();
    }

    public void finish() {
        DBInfo.finish();

        System.out.println("Arrêt du programme ! Au revoir :)");
        System.exit(0);
    }

    public static void createRelation(String nomRel, int nbCol, Vector <String> nomCols, Vector <String> typeCols) {
        RelationInfo relation = new RelationInfo(nomRel, nbCol, nomCols, typeCols);
        DBInfo.addRelation(relation);
    }

    public static void processCommand(String command) {
        String [] splitCommand = command.split(" ");

        switch (splitCommand[0].toUpperCase()) {

            case "CREATE": //Demander prof si c'est une bonne idée de refaire des variables locales ici
                String nomRel = splitCommand[1]; //nom de la relation
                int nbCol = Integer.parseInt(splitCommand[2]); //le nombre de colonnes
                Vector <String> nomCols = new Vector<String>();
                Vector <String> typeCols = new Vector<String>(); //Utilisation d'un vecteur car nb arguments variables

                for (int i = 3; i < splitCommand.length; i++) {
                   typeCols.add(splitCommand[i]);
                }

                createRelation(nomRel, nbCol, nomCols, typeCols);
                break;
                
            default:
                System.out.println("Mauvaise commande ! Recommencez s'il vous plait ;)");
                break;
        }
    }
}