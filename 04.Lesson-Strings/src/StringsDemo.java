public class StringsDemo {
     static public void main(String[] Args) {
        String word1 = "Baby";
        String word2 = "Apples";
        String resultWord = word1.substring(0, word1.length() / 2) +
                word2.substring(word2.length() / 2);
        System.out.println("1 ======");
        System.out.println(word1 + " + " + word2 + " = " + resultWord);
    }
}
