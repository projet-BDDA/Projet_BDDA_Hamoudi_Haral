package src;

import java.util.Vector;

public final class DBManager {

    private static DBManager INSTANCE; //Instance unique du DBManager

    private DBManager() {

    }

    public static DBManager getInstance() { //Permet d'obtenir l'unique instance de DBManager
        if (INSTANCE == null){
            INSTANCE = new DBManager();
        }

        return INSTANCE;
    }

    public void init() {
        DBInfo.getInstance().init();
    }

    public void finish() {
        DBInfo.getInstance().finish();
    }

    /**
     * Crée une relation et l'insert dans la DBInfo
     * 
     * @param nomRel Nom de la relation
     * @param nbCol Nombre de colonnes de la relation
     * @param nomCols Nom des colonnes de la reation
     * @param typeCols Type des colonnes de la relation
     */
    private void createRelation(String nomRel, int nbCol, Vector <String> nomCols, Vector <String> typeCols) {
        RelationInfo relation = new RelationInfo(nomRel, nbCol, nomCols, typeCols);
        DBInfo.getInstance().addRelation(relation);
    }

    /**
     * Gère les différentes commandes du LDD (langage de définition de données) et LMD (langage de manipulation de données)
     * 
     * @param command Commande de l'utilisateur
     */
    public void processCommand(String command) {
        String [] splitCommand = command.split(" ");

        switch (splitCommand[0]) {

            case "CREATEREL":
                String nomRel = splitCommand[1];
                int nbCol = 0;
                Vector <String> nomCols = new Vector<String>();
                Vector <String> typeCols = new Vector<String>();

                for (String string : splitCommand) { //Ajoute le nom et le type des colonnes + calcul le nb de colonnes
                    if ((!string.equals("CREATEREL")) && (!string.equals(nomRel))) {
                        String [] splitNomTypeCols = string.split(":");
                        nomCols.add(splitNomTypeCols[0]);
                        typeCols.add(splitNomTypeCols[1]);
                        nbCol++;
                    }
                }

                createRelation(nomRel, nbCol, nomCols, typeCols);
                break;
                
            default:
                System.out.println("Mauvaise commande ! Recommencez s'il vous plait");
                break;
        }
    }
}