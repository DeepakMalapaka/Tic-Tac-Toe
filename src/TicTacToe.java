import java.util.*;

public class TicTacToe {
    static char mat[][] = new char[3][3];
    // Fill with dummy values;
    static {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(mat[i], '-');
        }
    }
    static boolean chanceA = true;

    // For marking the board
    public static boolean markTheBoard(int x, int y) {
        char mark = (chanceA) ? 'X' : 'O';
        if (mat[x][y] == '-') {
            mat[x][y] = mark;
            return true;
        }
        System.out.println("Cannot mark at already used box!");
        return false;
    }

    // For printing the horizontal border
    public static void printHori() {
        System.out.println();
        System.out.print("  +");
        for (int i = 0; i < 3; i++) {
            System.out.print("-----+");
        }
        System.out.println();
    }

    // For printing the board
    public static void printTheBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++)
            System.out.printf("%-5s%d", "", i);
        printHori();
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] == '-') {
                    System.out.printf("%-5s|", "");
                } else {
                    System.out.printf("  %c  |", mat[i][j]);
                }
            }
            printHori();
        }
        System.out.println();
    }

    // For checking the game status if won some player or not
    public static boolean checkGameStatus(int x, int y) {
        char currSymbol = mat[x][y];
        // Check for the row of add element
        boolean win = true;
        for (int i = 0; i < 3; i++) {
            if (currSymbol != mat[x][i]) {
                win = false;
                break;
            }
        }
        if (win)
            return true;
        // Check for the column of add element
        win = true;
        for (int i = 0; i < 3; i++) {
            if (currSymbol != mat[i][y]) {
                win = false;
                break;
            }
        }
        if (win)
            return true;

        // Check for the first diagonal
        if (x == y) {
            win = true;
            for (int i = 0; i < 3; i++) {
                if (currSymbol != mat[i][i]) {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }
        // Check for the second diagonal
        if (x + y == 2) {
            win = true;
            for (int i = 2; i >= 0; i--) {
                if (currSymbol != mat[i][2 - i]) {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        boolean gameWon = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the names of player-A and player-B:");
        String playerA = sc.next();
        String playerB = sc.next();

        int isFilled = 0;
        System.out.println("\t\t\t\t\tGAME STARTED!");
        printTheBoard();
        while (isFilled < 9) {
            String name = (chanceA) ? playerA : playerB;
            String symbol = (chanceA) ? "(X)" : "(O)";
            System.out.println(name + "'s" + symbol + " turn to play");
            // Removes the unwanted input before input
            if (!sc.hasNextInt()) {
                System.out.println("Please enter numeric coordinates!");
                sc.next();
                continue;
            }
            // Taking the input;
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x < 0 || x > 2 || y < 0 || y > 2) {
                System.out.println("Please enter the coordinates with the range[0-2]:");
                continue;
            }
            // Marking the board
            if (!markTheBoard(x, y)) {
                continue;
            }
            isFilled++;
            // Printing the board
            printTheBoard();

            // We need to check the game wining by every move
            boolean hasWon = checkGameStatus(x, y);
            if (hasWon) {
                gameWon = true;
                String nameWon = (chanceA) ? playerA : playerB;
                String symbolWon = (chanceA) ? "X" : "O";
                System.out.println(nameWon + "(" + symbolWon + ") won the game...! Hurray");
                break;
            }
            // AtLast change the chance of the player
            chanceA = !chanceA;
        }
        if (!gameWon && isFilled == 9)
            System.out.println("It's a DRAW! Nobody won the game");
        System.out.println("\t\t\t\t\tGAME COMPLETED!");
        sc.close();
    }
}
