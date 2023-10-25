import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int size = 8; // Change the board size as needed
        int numMines = 10; // Change the number of mines as needed

        GameBoard gameBoard = new GameBoard(size, numMines);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        while (!gameBoard.isGameOver()) {
            gameBoard.displayBoard();
            System.out.print("Enter row and column (e.g., '3 4'): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            gameBoard.revealTile(row, col);
        }

        gameBoard.displayBoard();
        System.out.println("Game over! You hit a mine.");
        scanner.close();
    }
}