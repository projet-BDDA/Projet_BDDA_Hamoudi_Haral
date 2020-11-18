package src;

import java.nio.ByteBuffer;

public class DiskManagerTests {
    public static void main(String[] args) throws Exception{
        DBParams.DBPath = args[0];
        DBParams.pageSize = 4096;
        
        ByteBuffer buffer = ByteBuffer.allocate(DBParams.pageSize);

        testCreateFile(1);
        testAddPage(1);
        PageId pageId = new PageId(1, 1);
        buffer.putInt(100);
        testWritePage(pageId, buffer);
        buffer.clear();
        testReadPage(pageId, buffer);
        
    }

    private static void testCreateFile(int fileIdx) {
        DiskManager.createFile(fileIdx);
    }

    private static void testAddPage(int fileIdx) {
        DiskManager.addPage(fileIdx);
    }

    private static void testReadPage(PageId pageId, ByteBuffer buff) {
        DiskManager.readPage(pageId, buff);
    }

    private static void testWritePage(PageId pageId, ByteBuffer buff) {
        DiskManager.writePage(pageId, buff);
    }
}
