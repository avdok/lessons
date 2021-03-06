import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Chat {
    static final String filePropertiesName;

    static {
        filePropertiesName = "chat.properties";
    }

    public static void main(String[] args) {
        String ipAddr;
        String portNum;
        Properties fileProp = new Properties();

        //Прочитаем файл свойств, чтобы узнать адрес и порт сервера
        try (FileInputStream fileProperties = new FileInputStream(filePropertiesName)) {
            fileProp.load(fileProperties);
            ipAddr = fileProp.getProperty("address");
            portNum = fileProp.getProperty("port");
            System.out.println("Server on " + ipAddr + ":" + portNum);

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println("Can`t find properties file " + filePropertiesName);
            return;
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(e.toString());
            return;
        }


        //Спросим что запустить - сервер или клиент
        if (args.length > 0) {
            //Стартуем сервер
            System.out.println("Server starts.");
           startServer(ipAddr, portNum, args[0]);
        } else {
            //Стартуем клиента
            System.out.println("Client starts.");
            startClient(ipAddr, portNum, "Petya");
        }

    }

    static void startServer(String ipAddr, String portNum, String helloWord) {
        int a = 0;
        String input, cmd, user;

        try {
            ServerSocket server = new ServerSocket(40000);
            Socket clientConn = server.accept();
            BufferedReader serverInput = new BufferedReader(
                                         new InputStreamReader(clientConn.getInputStream()));
            while ( (input = serverInput.readLine()) != null )  {
                if (input.equals("MSG")) {
                    System.out.print(serverInput.readLine() + ": ");
                    System.out.println(serverInput.readLine());
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
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startClient(String ipAddr, String portNum, String userName) {
//        Scanner scanner = new Scanner(System.in);
        String userString;

        try {
            Socket clientSocket = new Socket(ipAddr, 40000);
            PrintWriter outStr = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter text: ");
            while ( (userString = userInput.readLine()) != null) {
                if (userString.charAt(0) == '/') {
                    outStr.println("CMD");
                    outStr.println(userName);
                    outStr.println(userString);
                } else {
                    outStr.println("MSG");
                    outStr.println(userName);
                    outStr.println(userString);
                }
            }
            outStr.close();
            clientSocket.close();
            userInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
