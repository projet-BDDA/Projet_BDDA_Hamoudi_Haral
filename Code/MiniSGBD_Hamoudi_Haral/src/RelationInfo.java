package src;

import java.io.Serializable;
import java.util.ArrayList;

public class RelationInfo implements Serializable {
    private String nomRel;
    private int nbCols;
    private ArrayList <String> nomCols;
    private ArrayList <String> typeCols; //Type restreint à "int" "float" "stringT", où T=longeur du string 

    /**
     * Crée une nouvelle relation 
     * 
     * @param nomRel nom de la relation
     * @param nbCols nombre de colonne(s)
     * @param nomCols nom de colonne(s)
     * @param typeCols liste du type de(s) colonne(s)
     */
    public RelationInfo(String nomRel, int nbCols,ArrayList <String> nomCols, ArrayList <String> typeCols) {
        this.nomRel=nomRel;
        this.nbCols=nbCols;
        this.nomCols=nomCols;
        this.typeCols=typeCols; //Mieux d'utiliser un ArrayList que tableau car on peut modifier le nb de colonne par la suite
    }

    /**
     * Accesseur du nombre de colonnes
     * 
     * @return retourne le nombre de colonnes
     */
    public int getNbCols() {
        return nbCols;
    }

    /**
     * Accesseur de la liste de type des colonnes
     * 
     * @return retourne la liste de type des colonnes
     */
    public ArrayList<String> getTypeCols() {
        return typeCols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(nomRel+"\n");
        sb.append(nbCols+"\n");
        
        for (int i = 0; i < nomCols.size(); i++) {
            sb.append(nomCols.get(i)+":"+typeCols.get(i)+" ");
        }

        return sb.toString();
    }
}
