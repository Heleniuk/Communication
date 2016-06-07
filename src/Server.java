import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {

    private static final String CONTENT_ROOT = "D:\\test\\Communication\\static_resources";
    private static final int PORT = 8080;
    private static final int POOL_SIZE = 15;

    public static String getContentRoot() {
        return CONTENT_ROOT;
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

        try {

            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket = null;

            while(true) {
                clientSocket = serverSocket.accept();

                if(clientSocket != null) {
                    Guest guest = new Guest(clientSocket);
                    pool.execute(guest);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            pool.shutdown();
        }

    }

}
