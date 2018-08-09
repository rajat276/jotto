import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    private static String DICTIONARY_FILE_PATH="src/sowpods.txt";
    private static String SECRET_FILE_PATH="secret.txt";
    private int lengthOfWord;

    public Dictionary(int lengthOfWord) {
        this.lengthOfWord = lengthOfWord;
    }

    public static ArrayList<String> readFromFile(String filePath) throws FileNotFoundException {
        ArrayList<String> dictionaryArray = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            dictionaryArray.add(scanner.nextLine());
        }
        return dictionaryArray;
    }

    public ArrayList<String> generateSecretFile(ArrayList<String> arrayList) throws  FileNotFoundException{
        ArrayList<String> dictionaryOfNLength = new ArrayList<>();
        for(String str : arrayList){
            if(str.length()==lengthOfWord && uniqueCharacters(str)){
                dictionaryOfNLength.add(str);
            }
        }
        return dictionaryOfNLength;
    }

    public static void ExcludeLetterFromSecretFile(ArrayList<String> strArray) throws IOException {

        String FILEPATH = SECRET_FILE_PATH;

    }

    public static void WriteToSecretFile(ArrayList<String> arrayList) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(SECRET_FILE_PATH));

        for (String str : arrayList) {
            outputWriter.write(str);
            outputWriter.newLine();

        }
        outputWriter.flush();
        outputWriter.close();
    }

    boolean uniqueCharacters(String str){
        int checker = 0;
        for (int i=0; i<str.length(); i++) {
            int bitAtIndex = str.charAt(i)-'A';
            if ((checker & (1<<bitAtIndex)) > 0)
                return false;
            checker = checker | (1<<bitAtIndex);
        }
        return true;
    }

    public static void main(String[] args){
        try {
            Dictionary dictionary = new Dictionary(4);
            ArrayList<String> dictionaryOfNLength= dictionary.generateSecretFile(readFromFile(DICTIONARY_FILE_PATH));
            for(String str: dictionaryOfNLength){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
