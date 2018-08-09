import java.util.Scanner;

public class Jotto {

    JottoBot playerA;
    JottoBot playerB;



    public Jotto (Integer difficulty, JottoBot playerA, JottoBot playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void playWithHuman() {
        System.out.println("Computer has chosen a word ");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Make a guess:");
            String humanInput = sc.nextLine();
            if (playerA.isSecretWord(humanInput)) {
                System.out.println("You Win!");
                break;
            }
            System.out.println("Matches: " + playerA.calculateWordMatches(humanInput));

            // Computer's Turn
            String computerGuess = playerA.guessNextWord();
            System.out.println("Computer's guess: " + computerGuess);
            humanInput = sc.nextLine();
            if (humanInput.equals("Y")){
                System.out.println("Computer Wins!!");
                break;
            }
            playerA.updateForMatches(Integer.parseInt(humanInput));
            System.out.println("----------------------------------------------------------------------");


        }
    }

    public void playGame() {
        System.out.println("ComputerA has choosen a word: "  + playerA.secretWord);
        System.out.println("ComputerB has choosen a word: "  + playerB.secretWord);

//        Scanner sc = new Scanner(System.in);
        while (true) {
//          System.out.println("Make Your Guess: ");
            String humanInput = playerB.guessNextWord();
            System.out.println("ComputerB Guessed: " + humanInput + " | Matches: " + playerA.calculateWordMatches(humanInput));
            if (playerA.isSecretWord(humanInput)){
                System.out.println("ComputerB Wins!!");
                break;
            }
            playerB.updateForMatches(playerA.calculateWordMatches(humanInput));

//          System.out.println("Number of Matches: " + playerA.calculateWordMatches(humanInput));

            System.out.println("ComputerA guessed: " + playerA.guessNextWord() + " | Matches: " + playerB.calculateWordMatches(playerA.currentGuessedWord));
            if (playerB.isSecretWord(playerA.currentGuessedWord)) {
                System.out.println("ComputerA Wins !!");
                break;
            }
            //System.out.println("Enter score: ");

            playerA.updateForMatches(playerB.calculateWordMatches(playerA.currentGuessedWord));
            System.out.println("----------------------------------------------------------------------");
//            String computerMatches = sc.nextLine();
//
//            if (computerMatches.equals("Y")){
//                System.out.println("Computer Won!! ");
//                break;
//            } else {
//                playerA.updateForMatches(Integer.parseInt(computerMatches));
//            }


        }
    }

    public static void main (String[] args) {
        Integer difficulty = 1;

        JottoBot computer = new JottoBot(difficulty);
        System.out.println(computer.secretWord);

        JottoBot human = new JottoBot(difficulty);
        System.out.println(human.secretWord);
        Jotto jotto = new Jotto(1, computer, human);

        jotto.playGame();

    }

}
