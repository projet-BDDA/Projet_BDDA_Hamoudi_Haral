package src;


import java.nio.ByteBuffer;

public class DiskManager {
    public static void createFile (int fileIdx) {  // crée (dans le sous-dossier DB) un fichier Data_fileIdx.rf
		
    }

    public static PageId addPage (int fileIdx) { // rajoute une page au fichier spécifié par fileIdx
        return null;
    }

    public static void readPage (int pageId, ByteBuffer buff) { // remplir le buff avec le contenu disque de la page identifiée par pageID

    }

    public static void writePage (int pageId, ByteBuffer buff)  { // écrit le contenu de buff dans le fichier et à la position pageId

    }
}
