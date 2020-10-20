package src;

import java.util.Vector;

public final class DBInfo {

    private static DBInfo INSTANCE;
    private int nbRel;
    private Vector <RelationInfo> listRels;

    private DBInfo() {
        this.nbRel = 0;
        this.listRels = new Vector <RelationInfo> ();
    }

    public static DBInfo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBInfo();
        }

        return INSTANCE;
    }
    
    public void init() {
        //ça doit faire quoi ??
    }

    public void finish() {
        //ça doit faire quoi ??
    }

    /**
     * Ajoute une relation dans la liste de relation et incrémente le nombre de relation
     * 
     * @param relation Relation (de type modèle relationnel)
     */
    public void addRelation(RelationInfo relation) {
        listRels.add(relation);
        nbRel++;
    }
}
