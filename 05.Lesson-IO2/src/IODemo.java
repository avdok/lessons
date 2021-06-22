import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.*;
import java.nio.file.*;
import java.util.stream.Stream;


public class IODemo {

    public static void main(String[] args) {
        String path;
        ArrayList<String> report = new ArrayList<>();

        if ( (args.length > 0) && (args[0].length() > 0) ) {
            path = args[0];
        } else {
            path = "/home/alex/Downloads";
        }

        ioVariant(path, report);
        nioVariant(path, report);
        writeToReport("report.txt", report);
    }

    static void writeToReport(String name, ArrayList<String> rept) {
        //Write to output file
        try(FileWriter fw = new FileWriter(name)) {
            for (String keyArrList: rept
            ) {
                fw.write(keyArrList + "\n");
            }

        } catch (IOException exc) {
            System.out.println("Ошибка ввода вывода " + exc);
        }

    }

    static void ioVariant(String name, ArrayList<String> rept) {
        HashMap<String, Long> fList = new HashMap<>();
        File dir = new File(name);
        long size = getDir(dir, 0, fList);

        rept.add("IO methods:");
        rept.add("Total size = " + (size / (1024 * 1024) ) + "Mb" );
        rept.add("======================");
        rept.add("Top 10 biggest files:");

        ArrayList<Long> arrSizes = new ArrayList<>(fList.values());
        Collections.sort(arrSizes, Comparator.reverseOrder());

        long limitSize = 0;
        if (arrSizes.size() >= 9) {
            limitSize = arrSizes.get(9);
        } else if (arrSizes.size() != 0) {
            limitSize = arrSizes.get(arrSizes.size() - 1);
        }

        int i = 0;
        for (HashMap.Entry entry : fList.entrySet()) {
            long curSize = ((Long) entry.getValue()).longValue();

            if (curSize >= limitSize) {
                rept.add(entry.getKey() + " = "
                        + curSize  + " b");
                i++;
                if (i > 9) {
                    break;
                }
            }
        }

    }

    static long getDir(File dir, int tab, HashMap<String, Long> fileList) {
        String tabbed;
        long result = 0;

        if (dir.isDirectory()) {
            File[] listOfFiles = dir.listFiles();
            try {
                for (File file : listOfFiles) {
                    if (file.isDirectory()) {
                        result = result + getDir(file, tab + 1, fileList);
                    } else {
                        result = result + file.length();
                        fileList.put(file.toString(), file.length());
                    }
                }
            } catch (NullPointerException ex) {
                System.out.println("File reading error:" + ex.toString());
            }
        } else {
            result = result + dir.length();
            fileList.put(dir.toString(), dir.length());
        }

        return result;
    }

    static void nioVariant(String name, ArrayList<String> rept) {
        HashMap<String, Long> fList = new HashMap<>();
        long size = getDirFileNIO(Paths.get(name), fList);
        rept.add("");
        rept.add("");
        rept.add("");
        rept.add("NIO methods:");
        rept.add("Total size = " + (size / (1024 * 1024) ) + "Mb" );
        rept.add("======================");
        rept.add("Top 10 biggest files:");

        ArrayList<Long> arrSizes = new ArrayList<>(fList.values());
        Collections.sort(arrSizes, Comparator.reverseOrder());

        long limitSize = 0;
        if (arrSizes.size() >= 9) {
            limitSize = arrSizes.get(9);
        } else if (arrSizes.size() != 0) {
            limitSize = arrSizes.get(arrSizes.size() - 1);
        }

        int i = 0;
        for (HashMap.Entry entry : fList.entrySet()) {
            long curSize = ((Long) entry.getValue()).longValue();

            if (curSize >= limitSize) {
                rept.add(entry.getKey() + " = "
                        + curSize  + " b");

                i++;
                if (i > 9) {
                    break;
                }

            }
        }
    }

    static long getDirFileNIO(Path fdObj, HashMap<String, Long> fileList) {
        long result = 0;

        if (Files.isDirectory(fdObj)) {
            try (DirectoryStream<Path> newDirStream = Files.newDirectoryStream(fdObj)) {
                for (Path entry : newDirStream) {
                    if (Files.isDirectory(entry)) {
                        result = result + getDirFileNIO(entry, fileList);
                    } else {
                        result = result + Files.size(entry);
                        fileList.put(entry.getFileName().toString(), Files.size(entry));
                    }

                }


            } catch (IOException exc) {
                System.out.println("IO file operation error:" + exc.toString());
            }
        } else {
            try {
                result = result + Files.size(fdObj);
                fileList.put(fdObj.getFileName().toString(), Files.size(fdObj));
            } catch (IOException exc) {
                System.out.println("IO file operation error:" + exc.toString());
            }
        }
        return result;
    }
}
