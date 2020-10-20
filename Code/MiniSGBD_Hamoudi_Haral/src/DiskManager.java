package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DiskManager {
    /**
     * Crée (dans le sous-dossier DB) un fichier Data_fileIdx.rf
     * 
     * @param fileIdx Entier correspondant à un identifiant de fichier
     */
    public static void createFile (int fileIdx) {
        String pathnameString = "./"+DBParams.DBPath+"/Data_"+fileIdx+".rf";
        File inputFile = new File(pathnameString);

        if (!inputFile.exists()) {
            
            try {
                inputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            
            System.out.println("Fichier Data_"+fileIdx+".rf correctement créé");
        } 
        else {
            System.out.println("Le fichier Data_"+fileIdx+".rf existe déjà !");
        }
    }

    /**
     * Rajoute une page (d'octets) au fichier spécifié par fileIdx
     * 
     * @param fileIdx Entier correspondant à un identifiant de fichier
     * @return Retourne un PageId correspondant à la page nouvellement rajoutée
     */
    public static PageId addPage (int fileIdx) { 
        return null;
    }

    /**
     * Remplit le buffer avec le contenu disque de la page identifiée par pageID
     * 
     * @param pageId  Identifiant de page
     * @param buff Un buffer
     */
    public static void readPage (PageId pageId, ByteBuffer buff) {
        String pathnameString = "./"+DBParams.DBPath+"/Data_"+pageId.getFileIdx()+".rf";
        File inputFile = new File(pathnameString);

        try {
            FileInputStream fileStream = new FileInputStream(inputFile); //Permet d'utiliser getChannel qui renvoie un canal
            FileChannel fileChannel = fileStream.getChannel(); //Utilise un buffer pour lire / écrire dans un fichier bin
            ByteBuffer buffer = ByteBuffer.allocate(DBParams.pageSize);

            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                 byte b = buffer.get();
                 System.out.print((char) b);
                }
                buffer.clear();
               }
            
            fileChannel.close();
            fileStream.close();;
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }

    /**
     * Ecrit le contenu du buffer dans le fichier et à la position voulu grâce à pageId
     * 
     * @param pageId Identifiant de page
     * @param buff Un buffer
     */
    public static void writePage (PageId pageId, ByteBuffer buff)  {

    }
}
