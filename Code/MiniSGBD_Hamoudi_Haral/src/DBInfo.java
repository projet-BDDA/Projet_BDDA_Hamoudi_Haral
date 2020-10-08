package src;

import java.util.Vector;

public class DBInfo {
    private static int nbRel = 0;
    private static Vector <RelationInfo> listRels = new Vector <RelationInfo> ();
    
    public static void init() {
        //ça doit faire quoi ??
    }

    public static void finish() {
        //ça doit faire quoi ??
    }

    public static void addRelation(RelationInfo relation) {
        listRels.add(relation);
        nbRel++;
    }
}
