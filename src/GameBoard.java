import java.util.Random;

public class GameBoard {
    private Tile[][] board;
    private int size;
    private int numMines;

    public GameBoard(int size, int numMines) {
        this.size = size;
        this.numMines = numMines;
        this.board = new Tile[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        // Fill the board with empty tiles
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Tile(false);
            }
        }

        // Place mines randomly
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!board[row][col].isMine()) {
                board[row][col] = new Tile(true);
                minesPlaced++;
            }
        }
    }

    public boolean isGameOver() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Tile tile = board[row][col];
                if (tile.isRevealed() && tile.isMine()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void revealTile(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size && !board[row][col].isRevealed()) {
            board[row][col].reveal();
        }
    }

    public void displayBoard() {
        //Bomb = Rött kryss = "X ";
        //Inte undersökt ruta = "."
        //Undersökt ruta utan bomber bredvid = 0;
        //Undersökt ruta med bomber bredvid = 1+;

        System.out.println("  | 0 1 2 3 4 5 6 7");
        System.out.println("--|-----------------");
        for (int row = 0; row < size; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < size; col++) {
                Tile tile = board[row][col];
                if (tile.isRevealed()) {
                    if (tile.isMine()) {
                        System.out.print("X ");
                    } else {
                        // Display the count of adjacent mines
                        int adjacentMines = countAdjacentMines(row, col);
                        System.out.print(adjacentMines + " ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < size && c >= 0 && c < size && board[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }
}
