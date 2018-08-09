import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class JottoBot {

    String secretWord;

    String currentGuessedWord;

    List<String> wordsDictionary = new ArrayList<>();

    public JottoBot(Integer difficulty) {

        Random random = new Random();
        try {
            String DICTIONARY_FILE_PATH = "src/sowpods.txt";
            wordsDictionary = new Dictionary(difficulty+3).generateSecretFile(Dictionary.readFromFile(DICTIONARY_FILE_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.secretWord = wordsDictionary.get(random.nextInt(wordsDictionary.size()));
    }

    //Guess the next word to play
    public String guessNextWord() {
        String guessWord = this.wordsDictionary.get(new Random().nextInt(wordsDictionary.size()));
        this.currentGuessedWord = guessWord;
        return guessWord;
    }

    // Updates the dictionary for the current guessed word and number of matches
    public void updateForMatches(Integer scoreMatches) {
        wordsDictionary.remove(wordsDictionary.indexOf(this.currentGuessedWord));
        wordsDictionary = wordsDictionary.stream().filter(word ->
           calculateWordMatches(word, this.currentGuessedWord) == scoreMatches
        ).collect(Collectors.toList());
    }

    //Calculates CommonCharacterMatch of word wrt secretWord
    public Integer calculateWordMatches (String word) {
        return  calculateWordMatches(this.secretWord, word);
    }

    public static Integer calculateWordMatches (String wordA, String wordB) {
        Set<Character> commonCharacters = new HashSet<>();
        for (Character c: wordA.toCharArray()){
            if (wordB.indexOf(c) >= 0){
                commonCharacters.add(c);
            }
        }
        return commonCharacters.size();
    }


    public boolean isSecretWord (String word) {
        return word.equals(this.secretWord);
    }

    public static void main (String[] args) {
        JottoBot player = new JottoBot(1);
        while (player.wordsDictionary.size() > 1) {
            System.out.println(player.wordsDictionary.size());
            String disp = "";
            for (int i=0; i<player.wordsDictionary.size(); i++)
                disp += player.wordsDictionary.get(i) + " ";
            System.out.println(disp);
            System.out.println(player.guessNextWord());
            //System.out.println(player.calculateScore(player.currentGuessedWord, "WORD"));
            Scanner sc = new Scanner(System.in);
            Integer score = sc.nextInt();
            player.updateForMatches(score);

        }

        System.out.println("Final Guess: " + player.wordsDictionary.get(0));
    }

}
