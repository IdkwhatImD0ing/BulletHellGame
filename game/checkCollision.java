package game;

public class checkCollision implements Runnable {
    Board currentBoard;

    public checkCollision(Board board) {
        currentBoard = board;
    }

    @Override
    public void run() {

        currentBoard.checkCollisions();

    }

}
