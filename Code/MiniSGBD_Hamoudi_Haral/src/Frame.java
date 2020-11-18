package src;

import java.nio.ByteBuffer;

public class Frame {
    private ByteBuffer buffer;
    private PageId pageId;
    private int pint_count;
    private boolean dirty;

    /**
     * Construit une case (frame) contenant les pages disque.
     * le pint_count est initialisé à 0 et le dirty à false.
     * 
     * @param buffer correspond à la zone mémoire de la case (prendra les pages de la case)
     * @param pageId le PageId de la page qui s’y trouve chargée (si la case n’est pas libre)
     */
    public Frame(ByteBuffer buffer, PageId pageId) {
        this.buffer = buffer;
        this.pageId = pageId;
        pint_count = 0;
        dirty = false;
    }
}
