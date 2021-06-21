import java.io.File;
import java.util.*;

public class IODemo {

    public static void main(String[] args) {
//        File dir = new File("/home/alex/git/lessons/05.Lesson-IO2");
        File dir = new File("/home/alex/Downloads");
        HashMap<String, Long> fList = new HashMap<>();

        long size = getDir(dir, 0, fList);

        System.out.println("Total size = " + (size / (1024 * 1024) ) + "Mb" );
        System.out.println("======================");
        System.out.println("Top 10 biggest files:");

        ArrayList<Long> arrSizes = new ArrayList<>(fList.values());
        Collections.sort(arrSizes, Comparator.reverseOrder());

        long limitSize = 0;
        if (arrSizes.size() >= 9) {
            limitSize = arrSizes.get(9);
        } else if (arrSizes.size() != 0) {
            limitSize = arrSizes.get(arrSizes.size() - 1);
        }

        int i = 0;
        for (Map.Entry entry : fList.entrySet()) {
            long curSize = ((Long) entry.getValue()).longValue();

            if (curSize >= limitSize) {
                System.out.println(entry.getKey() + " = "
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

        char[] spaces = new char[tab * 4];
        Arrays.fill(spaces, ' ');
        tabbed = new String(spaces);

        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (File file : listFiles) {
                if (file.isDirectory()) {
//                    System.out.println(tabbed + file.toString() + "  --it is directory");
                    result = result + getDir(file, tab + 1, fileList);
                } else {
                    result = result + file.length();
                    fileList.put(file.toString(), file.length());
//                    System.out.print(tabbed + file.toString());
//                    System.out.println("  --size " + file.length());
                }
            }
            //System.out.println(Arrays.toString(listFiles));
        } else {
            result = result + dir.length();
            fileList.put(dir.toString(), dir.length());
//            System.out.print(tabbed + dir.toString());
//            System.out.println("  --size " + dir.length());
        }

        return result;
    }
}
