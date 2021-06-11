public class SyncCompile {

    public synchronized void codeBlockSync() {
        synchronized (this) {
            System.out.println("aa");
        }
    }

    public synchronized void normalMethodSync() {
        System.out.println("aa");
    }

    public static synchronized void staticMethodSync() {
        System.out.println("aa");
    }

    public void classSync() {
        synchronized (SyncCompile.class) {
            System.out.println("aa");
        }
    }
}
