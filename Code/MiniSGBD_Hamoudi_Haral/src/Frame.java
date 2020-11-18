package src;

import java.nio.ByteBuffer;

public class Frame {
    private ByteBuffer buffer;
    private PageId pageId;
    private int pint_count;
    private boolean dirty;

    public Frame(ByteBuffer buffer, PageId pageId) {
        this.buffer = buffer;
        this.pageId = pageId;
        pint_count = 0;
        dirty = false;
    }
}
