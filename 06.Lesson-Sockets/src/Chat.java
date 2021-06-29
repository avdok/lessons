import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
            startServer(ipAddr, portNum, args[0]);
        } else {
            //Стартуем клиента
            startClient(ipAddr, portNum, args[0]);
        }

    }

    static void startServer(String ipAddr, String portNum, String helloWord) {
        //создать сокет

        //слушать адрес - порт

    }

    static void startClient(String ipAddr, String portNum, String helloWord) {

    }


}
