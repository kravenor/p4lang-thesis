public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Client(), "Client-" + i);
            t.start();
        }
    }

}