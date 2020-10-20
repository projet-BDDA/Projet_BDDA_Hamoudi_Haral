package src;

import java.nio.ByteBuffer;

public class DiskManagerTests {
    public static void main(String[] args) throws Exception{
        DBParams.DBPath = args[0];
        DBParams.pageSize = 4096;
        
        testCreateFile(0);
        testAddPage(0);
        testReadPage(new PageId(), null);
        testWritePage(new PageId(), null);

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
