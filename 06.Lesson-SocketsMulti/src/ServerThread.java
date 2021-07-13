import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable{
    public static int count = 0;
    private Thread t;
    private boolean isRun;
    private Socket clientConn;


    ServerThread(Socket cC) {
        this.clientConn = cC;
        t = new Thread(this, "Server" + count);
        count++;
        t.start();
    }

    @Override
    public void run() {
        String input, cmd, user, msg;
        try (BufferedReader serverInput = new BufferedReader(
                     new InputStreamReader(clientConn.getInputStream()));
             PrintWriter outStr = new PrintWriter(clientConn.getOutputStream(), true)
            ) {
                while ( (input = serverInput.readLine()) != null )  {
                    if (input.equals("MSG")) {

                        user = serverInput.readLine();
                        msg = serverInput.readLine();

                        System.out.print(user + ": ");
                        System.out.println(msg);

                        outStr.print(user + ": ");
                        outStr.println(msg);

                    } else if (input.equals("CMD")) {

                        user = serverInput.readLine();
                        cmd = serverInput.readLine();

                        System.out.print("Command from ");
                        System.out.print(user + ": ");
                        System.out.println(cmd);
                        if (cmd.equalsIgnoreCase("/exit")) {
                            break;
                        }

                    }
    //                System.out.print(input);
                }
                clientConn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
