package src;

import java.nio.ByteBuffer;

public class Frame {
    private ByteBuffer buffer;
    private PageId pageId;
    private int pin_count;
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
        pin_count = 0;
        dirty = false;
    }

    public void incrementPinCount() {
        pin_count++;
    }

    public void decrementPinCount() {
        if (pin_count > 0) {
            pin_count--;
        }
    }

    /**
     * Réinitialise la frame pour la vider
     */
    public void resetFrame() {
        pageId = null;
        dirty = false;
		pin_count = 0;
    }
    
    /**
     * Accesseur du flag dirty
     * 
     * @return retourne la valeur de dirty (true ou false)
     */
    public boolean getDirty() {
        return dirty;
    }

    /**
     * Accesseur du pin_count
     * 
     * @return retourne la valeur du pin_count
     */
    public int getPinCount() {
        return pin_count;
    }

    /**
     * Accesseur du pageId de la frame
     * 
     * @return retourne le pageId
     */
    public PageId getPageId() {
        return pageId;
    }

    /**
     * Accesseur du buffer de la frame
     * 
     * @return retourne le buffer
     */
    public ByteBuffer getByteBuffer() {
        return buffer;
    }
}
