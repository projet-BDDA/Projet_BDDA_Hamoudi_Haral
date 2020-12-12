package src;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class HeapFile {
    private RelationInfo relInfo;

    /**
     * Construit un Heap File pour une relation donnée
     * 
     * @param relInfo relation à lié avec un Heap File
     */
    public HeapFile(RelationInfo relInfo) {
        this.relInfo = relInfo;
    }

    /**
     * Gère la création de fichier disque correspondant au Heap File
     */
    public void createNewOnDisk() {
        int fileIdx = relInfo.getFileIdx();
		DiskManager.createFile(fileIdx);
        PageId newPageId = DiskManager.addPage(fileIdx);

        ByteBuffer bufferHeaderPage = BufferManager.getInstance().getPage(newPageId);
        
        if (bufferHeaderPage != null) {
            for (int i = 0; i < bufferHeaderPage.capacity(); i += Integer.BYTES) {
                bufferHeaderPage.putInt(i, 0);
            }
        }

        BufferManager.getInstance().freePage(newPageId, true);
    }

    /**
     * Rajoute une page de données et renvoie la page ajoutée
     * 
     * @return retourne la page ajoutée au fichier
     */
    private PageId addDataPage() {
        int fileIdx = relInfo.getFileIdx();
        PageId newPageId = DiskManager.addPage(fileIdx);

        PageId headerPage = new PageId(fileIdx, 0);
        ByteBuffer bufferHeaderPage = BufferManager.getInstance().getPage(headerPage);
        bufferHeaderPage.putInt(0,bufferHeaderPage.getInt(0)+1); //entier N, correspondant au nombre de pages de données dans le fichier, mis à l'indice 0 de la Header Page

        /*
        System.out.println("Dans addDataPage, nb page = "+bufferHeaderPage.getInt(0));
        System.out.println("(addDataPage) PageId de newPageId = "+newPageId.getPageIdx());
        */

        bufferHeaderPage.putInt(bufferHeaderPage.getInt(0) * Integer.BYTES, relInfo.getSlotCount()); // Ajout du nombre de slot dispo au dernière indice de la Header Page
        BufferManager.getInstance().freePage(headerPage, true);
        
        /*
        ByteBuffer bufferHeaderPageTest = BufferManager.getInstance().getPage(headerPage);
        System.out.println("Dans addDataPage, nb page (2eme get) = "+bufferHeaderPageTest.getInt(0));
        BufferManager.getInstance().freePage(headerPage, false);
        /*

        /*
        ByteBuffer bufferPage = BufferManager.getInstance().getPage(newPageId);
        for (int i = 0; i < relInfo.getSlotCount(); i += Byte.BYTES) {
            bufferPage.put(i, (byte)0);
        }
        BufferManager.getInstance().freePage(newPageId, true);
        */
        return newPageId;
    }

    /**
     * Trouve le  PageId d'une page de données sur laquelle il reste des cases libres
     * 
     * @return retourne le pageId de la page de données libre, null si aucune page n'est libre
     */
    private PageId getFreeDataPageId() {
        int fileIdx = relInfo.getFileIdx();
        PageId headerPage = new PageId(fileIdx, 0);
        ByteBuffer bufferHeaderPage = BufferManager.getInstance().getPage(headerPage);

        int sautParIntByte = Integer.BYTES;

        for (int i = 1; i <= bufferHeaderPage.getInt(0); i++) {

            if (bufferHeaderPage.getInt(sautParIntByte) > 0) {
                BufferManager.getInstance().freePage(headerPage, false);
                return new PageId(fileIdx, i);
            }

            sautParIntByte += Integer.BYTES;
        }

        BufferManager.getInstance().freePage(headerPage, false);

        return null;
    }

    /**
     * Cette méthode doit écrire l’enregistrement record dans la page de données identifiée par pageId, 
     * et renvoyer son Rid
     * 
     * @param record tuple que l'on souhaite enregistrer
     * @param pageId pageId à laquelle on souhaite enregistrer le record
     * @return retourne l'identifiant du record enregistré
     */
    private Rid writeRecordToDataPage(Record record, PageId pageId) {
        int bytemapPosition = 0;
        ByteBuffer bufferPage = BufferManager.getInstance().getPage(pageId);

        while (bytemapPosition < record.getRelInfo().getSlotCount()) {

            if (bufferPage.get(bytemapPosition) == 0) {
                //int position = record.getRelInfo().getSlotCount() * bytemapPosition; //se qui était écrit de base !
                int position = record.getRelInfo().getRecordSize() * (bytemapPosition + 1);
                record.writeToBuffer(bufferPage, position);
                bufferPage.put(bytemapPosition, (byte)1);

                break;
            } else {
                bytemapPosition += Byte.BYTES;
            }

        }

        BufferManager.getInstance().freePage(pageId, true);

        PageId headerPage = new PageId(pageId.getFileIdx(), 0);
        ByteBuffer headerPageBuffer = BufferManager.getInstance().getPage(headerPage);
		int positionHeaderPage = pageId.getPageIdx()*Integer.BYTES;
        headerPageBuffer.putInt(positionHeaderPage, headerPageBuffer.getInt(positionHeaderPage)-1); //On décrémente le nombre de slots dispo selon la pageId
        BufferManager.getInstance().freePage(headerPage, true);

        

        
        return new Rid(pageId, bytemapPosition);
    }

    /**
     * retourne la liste des records stockés dans la page identifiée par pageId.
     * 
     * @param pageId identifiant de page
     * @return retourne la liste de record
     */
    private ArrayList<Record> getRecordsInDataPage(PageId pageId) {
        ByteBuffer bufferPage = BufferManager.getInstance().getPage(pageId);
        ArrayList<Record> listRecords = new ArrayList<Record>();
        ArrayList<String> listStrings = new ArrayList<String>();

        //System.out.println("je suis dans getRecordDataPage");
        /*System.out.println("fileIdx = "+pageId.getFileIdx()+" pageId = "+pageId.getPageIdx());
        System.out.println("nb slots = "+relInfo.getSlotCount());*/

        for (int bytemapPosition = 0; bytemapPosition < relInfo.getSlotCount(); bytemapPosition += Byte.BYTES) { 
            bufferPage.position(bytemapPosition);
            //System.out.println(bufferPage.get());
            if (bufferPage.get(bytemapPosition) == 1) {
                int positionElements = (bytemapPosition + 1) * relInfo.getRecordSize();
                bufferPage.position(positionElements);
                //bufferPage.position(bytemapPosition); //Autre

                //System.out.println("j'ai trouvé une page rempli (getRecordDataPage)");

                for (int i = 0; i < relInfo.getNbCols(); i++) {

                    if (relInfo.getTypeCols().get(i).equals("int")) {
                        String conversion = Integer.toString(bufferPage.getInt());
                        bufferPage.position(bufferPage.position() + Integer.BYTES);
                        listStrings.add(conversion);
                    } else if (relInfo.getTypeCols().get(i).equals("float")) {
                        String conversion = Float.toString(bufferPage.getFloat());
                        bufferPage.position(bufferPage.position() + Float.BYTES);
                        listStrings.add(conversion);
                    } else {
                        int sizeString = Integer.parseInt(relInfo.getTypeCols().get(i).substring(6));
                        StringBuilder sb = new StringBuilder();

                        for (int j = 0; j < sizeString; j++) {
                            sb.append(bufferPage.getChar());
                            bufferPage.position(bufferPage.position() + Character.BYTES); //bufferPage.position() + Character.BYTES
                        }

                        listStrings.add(sb.toString());
                    }
                }

                listRecords.add(new Record(relInfo, listStrings));
            }
        }

        BufferManager.getInstance().freePage(pageId, false);
        return listRecords;
    }

    /**
     * Permet l'insertion d'un record dans une page de données non pleine
     * 
     * @param record record que l'on veut insérer
     * @return retourne le RID du record
     */
    public Rid insertRecord(Record record) {
        PageId FreePage = getFreeDataPageId();

        if(FreePage == null) { //Si aucune page n'est libre on en rajoute une
			FreePage = addDataPage();
        }

		return writeRecordToDataPage(record, FreePage);
    }

    /**
     * Permet de lister tous les records du Heap File 
     * 
     * @return retourne la liste de records
     */
    public ArrayList<Record> getAllRecords() {
        ArrayList<Record> listRecords = new ArrayList<Record>();
        ByteBuffer bufferHeaderPage = BufferManager.getInstance().getPage(new PageId(relInfo.getFileIdx(), 0)); //Correspond au buffer de la header page
        int nbPages = bufferHeaderPage.getInt(0);

        int fileIdx = relInfo.getFileIdx();

        for (int i = 1; i <= nbPages; i++) {
            PageId pageId = new PageId(fileIdx, i);
            listRecords.addAll(getRecordsInDataPage(pageId));
        }

        BufferManager.getInstance().freePage(new PageId(relInfo.getFileIdx(), 0), false);
        return listRecords;
    }

    /**
     * Accesseur de la relation lié au Heap File
     * 
     * @return retourne la relation
     */
    public RelationInfo getRelInfo() {
        return relInfo;
    }
}
