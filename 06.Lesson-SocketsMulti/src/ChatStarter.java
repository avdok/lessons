import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class ChatStarter {
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
            System.out.println(e);
            return;
        }


        //Спросим что запустить - сервер или клиент
        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            //Стартуем сервер
            System.out.println("Server starts.");
            startServer(ipAddr, portNum, args[0]);
        } else if (args.length > 0 && args[0].equalsIgnoreCase("client")) {
            //Стартуем клиента
            System.out.println("Client starts.");
            if (args.length > 1) {
                startClient(ipAddr, portNum, args[1]);
            } else {
                startClient(ipAddr, portNum, "Anonymous");
            }
        } else {
            System.out.println("Usage:");
            System.out.println("ChatStarter param name");
            System.out.println("-param is server or client");
            System.out.println("-name is name for client");
        }

    }

    static void startServer(String ipAddr, String portNum, String helloWord) {
        int a = 0;
        String input, cmd, user;
        boolean serverIsRun = true;
        ArrayList<ServerThread> clientPull = new ArrayList<>();

        try {
            ServerSocket server = new ServerSocket(Integer.parseInt(portNum));
            while (serverIsRun) {
                Socket clientConn = server.accept();
                ServerThread st = new ServerThread(clientConn);
                clientPull.add(st);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            ServerSocket server = new ServerSocket(40000);
//            Socket clientConn = server.accept();
//            BufferedReader serverInput = new BufferedReader(
//                    new InputStreamReader(clientConn.getInputStream()));
//            while ( (input = serverInput.readLine()) != null )  {
//                if (input.equals("MSG")) {
//                    System.out.print(serverInput.readLine() + ": ");
//                    System.out.println(serverInput.readLine());
//                } else if (input.equals("CMD")) {
//
//                    user = serverInput.readLine();
//                    cmd = serverInput.readLine();
//
//                    System.out.print("Command from ");
//                    System.out.print(user + ": ");
//                    System.out.println(cmd);
//                    if (cmd.equalsIgnoreCase("/exit")) {
//                        break;
//                    }
//
//                }
////                System.out.print(input);
//            }
//            clientConn.close();
//            server.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    static void startClient(String ipAddr, String portNum, String userName) {
//        Scanner scanner = new Scanner(System.in);
        String userString;

        try {
            Socket clientSocket = new Socket(ipAddr, Integer.parseInt(portNum));

            //Запустим поток для отображения информации с сервера
            ClientThread ct = new ClientThread(clientSocket);

            PrintWriter outStr = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter text: ");
            outStr.println("CMD");
            outStr.println(userName);
            outStr.println("connected");
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
