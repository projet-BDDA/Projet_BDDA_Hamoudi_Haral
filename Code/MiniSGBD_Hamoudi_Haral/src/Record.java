package src;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Record {
    RelationInfo relInfo; //correspond à la relation à laquelle « appartient » le record
    ArrayList<String> values;

    /**
     * Construit un tuple (record) pour une relation donnée
     * 
     * @param relInfo relation à laquelle le tuple est rattaché
     */
    public Record(RelationInfo relInfo) {
        this.relInfo = relInfo;
        values = new ArrayList<String>();
    }

    /**
     * Écrit les valeurs du Record dans le buffer, l’une après l’autre, à partir de position
     * 
     * @param buff le buffer dans lequel on veut écrire
     * @param position la position où l'on écrit dans le buffer
     */
    public void writeToBuffer(ByteBuffer buff, int position) {
        buff.position(position);

        for (int i = 0; i < relInfo.getNbCols(); i++) {

            switch (relInfo.getTypeCols().get(i)) {
                case "int":
                    try {
                        int valueInt = Integer.parseInt(values.get(i));
                        buff.putInt(valueInt);
                    } catch (NumberFormatException e) {
                        System.out.println("La valeur "+values.get(i)+" n'est pas un nombre entier valide !");
                    }
                    

                    break;

                case "float" :
                    try {

                        float valueFloat = Float.parseFloat(values.get(i));
                        buff.putFloat(valueFloat);

                    } catch (NumberFormatException e) {
                        System.out.println("La valeur "+values.get(i)+" n'est pas un nombre réel valide !");
                    }
                    
                    break;

                default:
                    int sizeString = Integer.parseInt(values.get(i).substring(6)); //Retourne la taille du string

                    /**
                     * On ajoute des espaces si la taille réelle du string est inférieur à la taille max
                     * Si l'on dépasse la taille max du string, alors on ne prend pas en compte le reste du string
                     */
                    for (int j = 0; j < sizeString; j++) {
                        if (j < values.get(i).length()) {
                            buff.putChar(values.get(i).charAt(j));
                        } else {
                            buff.putChar(' ');
                        }
                    }
                    
                    break;
            }
        }
    }

    /**
     * Lit les valeurs du Record depuis le buffer, l’une après l’autre, à partir de position
     * 
     * @param buff le buffer que l'on veut lire
     * @param position la position où l'on écrit dans le buffer
     */
    public void readFromBuffer(ByteBuffer buff, int position) {
        buff.position(position);

        for (int i = 0; i < relInfo.getNbCols(); i++) {

            switch (relInfo.getTypeCols().get(i)) {
                case "int":
                    String intToString = Integer.toString(buff.getInt());
                    values.add(i, intToString);
                    buff.position(buff.position() + Integer.BYTES); //On change la position selon le nbr de byte d'un int

                    break;
            
                case "float":
                    String floatToString = Float.toString(buff.getFloat());
                    values.add(i, floatToString);
                    buff.position(buff.position() + Float.BYTES); //On change la position selon le nbr de byte d'un float

                    break;
                default:
                    int sizeString = Integer.parseInt(values.get(i).substring(6)); //Retourne la taille du string

                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < sizeString; j++) {
                        sb.append(buff.getChar());
                        buff.position(buff.position() + Character.BYTES);
                    }

                    values.add(i, sb.toString());

                    break;
            }
        }
    }
}
