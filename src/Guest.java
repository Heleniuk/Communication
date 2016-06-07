import java.io.*;
import java.net.Socket;

class Guest implements Runnable {
    private Socket guestSocket;

    Guest(Socket guestSocket) {
        this.guestSocket = guestSocket;
    }

    @Override
    public void run() {

        try (
                //installing the streams
                OutputStream out = guestSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(guestSocket.getInputStream()));
        ) {

            //reading the guest's request
            String request = in.readLine();

            //dispatching the request
            Dispatcher dispatcher = new Dispatcher();
            HttpResponse response = dispatcher.dispatch(request);

            //serving the response
            out.write(response.getFullResponse());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
