package src;

import java.util.Vector;

public class RelationInfo {
    private String nomRel;
    private int nbCols;
    private Vector <String> nomCols;
    private Vector <String> typeCols; //Type restreint à "int" "float" "stringT", où T=longeur du string 

    public RelationInfo(String nomRel, int nbCols,Vector <String> nomCols, Vector <String> typeCols) {
        this.nomRel=nomRel;
        this.nbCols=nbCols;
        this.nomCols=nomCols;
        this.typeCols=typeCols; //Mieux d'utiliser un vector que tableau car on peut modifier le nb de colonne par la suite
    }

}
