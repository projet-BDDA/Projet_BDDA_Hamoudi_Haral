package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DiskManager {
    public static void createFile (int fileIdx) {  // crée (dans le sous-dossier DB) un fichier Data_fileIdx.rf
        String pathnameString = "./"+DBParams.DBPath+"/Data_"+fileIdx+".rf";
        System.out.println(pathnameString);
        File inputFile = new File(pathnameString);

        if (!inputFile.exists()) {
            System.out.println("Ce fichier n'existe pas ! On va le créer pour toi !");
            
            try {
                inputFile.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
            
        } 
        else {
            try {
                FileInputStream fileStream = new FileInputStream(inputFile); //Permet d'utiliser getChannel qui renvoie un canal
                FileChannel fileChannel = fileStream.getChannel(); //Utilise un buffer pour pour lire / écrire dans un fichier bin
                ByteBuffer buffer = ByteBuffer.allocate(DBParams.pageSize); //ByteBuffer est un conteneur de taille fixe d'octets

                
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static PageId addPage (int fileIdx) { // rajoute une page au fichier spécifié par fileIdx
        return null;
    }

    public static void readPage (int pageId, ByteBuffer buff) { // remplir le buff avec le contenu disque de la page identifiée par pageID

    }

    public static void writePage (int pageId, ByteBuffer buff)  { // écrit le contenu de buff dans le fichier et à la position pageId

    }
}
