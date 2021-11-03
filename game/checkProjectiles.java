package game;

public class checkProjectiles implements Runnable {
    private Board currentBoard;

    public checkProjectiles(Board board) {
        currentBoard = board;
    }

    @Override
    public void run() {

        currentBoard.updateProjectiles();

    }

}
