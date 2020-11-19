package src;

public class BufferManagerTests {
    public static void main(String[] args) {
        DBParams.DBPath = args[0];
        DBParams.pageSize = 4096;
        DBParams.frameCount = 2;


    }
}
