import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread implements Runnable{
    Thread t;
    boolean isRun;
    Socket socket;

    ClientThread(Socket sS) {
        this.socket = sS;
        t = new Thread(this, "Client");
        t.start();
    }


    @Override
    public void run() {
        String input, cmd, user;
        try (BufferedReader serverInput = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))
        ) {
            while ( (input = serverInput.readLine()) != null )  {
                System.out.println(input);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
