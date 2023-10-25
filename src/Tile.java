import java.util.Random;
import java.util.Scanner;

public class Tile {
    private boolean isMine;
    private boolean isRevealed;

    public Tile(boolean isMine) {
        this.isMine = isMine;
        this.isRevealed = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        isRevealed = true;
    }
}