package src;

import java.util.Vector;

public class RelationInfo {
    private String nomRel;
    private int nbCols;
    private Vector <String> nomCols;
    private Vector <String> typeCols; //Type restreint à "int" "float" "stringT", où T=longeur du string 

    /**
     * Crée une nouvelle relation 
     * 
     * @param nomRel nom de la relation
     * @param nbCols nombre de colonne(s)
     * @param nomCols nom de colonne(s)
     * @param typeCols liste du type de(s) colonne(s)
     */
    public RelationInfo(String nomRel, int nbCols,Vector <String> nomCols, Vector <String> typeCols) {
        this.nomRel=nomRel;
        this.nbCols=nbCols;
        this.nomCols=nomCols;
        this.typeCols=typeCols; //Mieux d'utiliser un vector que tableau car on peut modifier le nb de colonne par la suite
    }

}
