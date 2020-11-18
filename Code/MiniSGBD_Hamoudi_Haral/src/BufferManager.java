package src;

import java.nio.ByteBuffer;

public class BufferManager {
    private static BufferManager INSTANCE;

    private BufferManager() {

    }

    /**
     * Permet d'obtenir l'unique instance du BufferManager
     * 
     * @return retourne l'instance du BufferManager
     */
    public static BufferManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BufferManager();
        }

        return INSTANCE;
    }

    /**
     * Cette méthode doit répondre à une demande de page venant des couches plus hautes, et donc 
     * retourner un des buffers associés à une frame. Le buffer sera rempli avec le contenu de la page désignée par l’argument pageId.
     * 
     * Attention : ne pas créer de buffer supplémentaire, « récupérer » simplement celui qui correspond à la bonne frame, 
     * après l’avoir rempli si besoin par un appel au DiskManager.
     * 
     * Attention aussi : cette méthode devra s’occuper du remplacement du contenu d’une frame si
     * besoin (donc politique de remplacement).
     * 
     * @param pageId identifiant de la page
     * @return
     */
    public ByteBuffer getPage (PageId pageId) {
        return null;
    }

    /**
     * Cette méthode devra décrémenter le pin_count et actualiser le flag dirty de la page (et aussi
     * potentiellement actualiser des informations concernant la politique de remplacement).
     * 
     * @param pageId identifiant de la page
     * @param valdirty vérifie si la page a été modifiée
     */
    public void freePage (PageId pageId, boolean valdirty) {

    }

    /**
     *  Cette méthode s’occupe de :
     * ◦ l’écriture de toutes les pages dont le flag dirty = 1 sur disque
     * ◦ la remise à 0 de tous les flags/informations et contenus des buffers (buffer pool « vide »)
     */
    public void flushAll() {

    }
}
