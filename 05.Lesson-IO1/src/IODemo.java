import java.io.*;
import java.util.*;

public class IODemo {

    public static void main(String[] Args) {
        String fileA;
        String fileB;
        String outFile;
        String[] dataFromString;
        String s;

        HashMap<String, Integer> vocabulary = new HashMap<>();

        //Read properties file
        Properties prop = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader("main.properties"))) {

            prop.load(br);

            fileA = prop.getProperty("fileA");
            fileB = prop.getProperty("fileB");
            outFile = prop.getProperty("outFile");
            System.out.println(fileA);
            System.out.println(fileB);


        } catch (IOException exc) {
            System.out.println("Ошибка ввода вывода");
            return;
        }

        // Read file A and B
        try (BufferedReader fA = new BufferedReader(new FileReader(fileA));
             BufferedReader fB = new BufferedReader(new FileReader(fileB))) {

//            fillVocabulary(fA, vocabulary);
//            fillVocabulary(fB, vocabulary);

            //read file A to HashMap
            while((s = fA.readLine()) != null) {
                dataFromString = getTokens(s);
                for (int i = 0; i < dataFromString.length; i++ ) {
                    String key = dataFromString[i];
                    if (vocabulary.containsKey(key)) {
                        vocabulary.put(key, vocabulary.get(key) + 1);
                    } else {
                        vocabulary.put(key, 1);
                    }
                }
            }

            //read file B to HashMap
            while((s = fB.readLine()) != null) {
                dataFromString = getTokens(s);
                for (int i = 0; i < dataFromString.length; i++ ) {
                    String key = dataFromString[i];
                    if (vocabulary.containsKey(key)) {
                        vocabulary.put(key, vocabulary.get(key) + 1);
                    } else {
                        vocabulary.put(key, 1);
                    }
                }
            }


//            while((s = fB.readLine()) != null) {
//                System.out.println(s);
//            }



//            System.out.println("----------------------------");
//            for (HashMap.Entry entry : vocabulary.entrySet()) {
//                System.out.println(entry.getKey() + " = "
//                        + entry.getValue());
//            }

        } catch (IOException exc) {
            System.out.println("Ошибка ввода вывода");
            return;
        }

        //Get Set with keys from HashMap, convert to array, convert to ArrayList, sort ArrayList
        Set<String> keys = vocabulary.keySet();
        String[] keysArr = keys.toArray(new String[keys.size()]);
        ArrayList<String> arrList = new ArrayList<>(Arrays.asList(keysArr));
        Collections.sort(arrList);

        //Write to output file
        try(FileWriter fw = new FileWriter(outFile)) {
            for (String keyArrList: arrList
            ) {
                fw.write(keyArrList + ":" + vocabulary.get(keyArrList) + "\n");
            }

        } catch (IOException exc) {
            System.out.println("Ошибка ввода вывода " + exc);
        }

    }


    public static String[] getTokens(String str) {

        String clearString = str.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase(Locale.ROOT);
        return clearString.split("\s");
    }

}
