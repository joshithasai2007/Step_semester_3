
import java.util.*;

public class Problem1 {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock")
                && computerMove.equalsIgnoreCase("Scissors"))
                || (playerMove.equalsIgnoreCase("Paper")
                && computerMove.equalsIgnoreCase("Rock"))
                || (playerMove.equalsIgnoreCase("Scissors")
                && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] player = new String[5];
        String[] computer = new String[5];
        String[] result = new String[5];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter Rock, Paper, or Scissors: ");
            player[i] = sc.next();

            computer[i] = moves[new Random().nextInt(3)];
            result[i] = playRound(player[i], computer[i]);

            System.out.println("Computer: " + computer[i]
                    + " | Result: " + result[i]);

            if (result[i].equals("Player Wins")) {
                wins++;
            } else if (result[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("\nRound | Player | Computer | Result");

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + " | " + player[i]
                    + " | " + computer[i] + " | " + result[i]);
        }

        double percentage = wins * 100.0 / 5;

        System.out.println("Wins: " + wins + " | Losses: " + losses
                + " | Draws: " + draws);
        System.out.println("Win Percentage: " + percentage + "%");

        sc.close();
    }
}