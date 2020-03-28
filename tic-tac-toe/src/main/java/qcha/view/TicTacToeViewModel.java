package qcha.view;

import static qcha.Constants.TILES_IN_COLUMN;
import static qcha.Constants.TILES_IN_ROW;

public final class TicTacToeViewModel {
    private boolean isXPlayerTurn = true;
    private boolean isGameOver = false;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean play) {
        isGameOver = play;
    }

    public boolean isXPlayerTurn() {
        return isXPlayerTurn;
    }

    public void setXPlayerTurn(boolean isXPlayerTurn) {
        this.isXPlayerTurn = isXPlayerTurn;
    }

    public void exit() {
        System.exit(0);
    }

    public boolean checkWinner(String[][] board) {
        // Returns true if any of the diagonal
        // is crossed with the same player's move
        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2]))
            return true;

        if (board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0]) &&
                !board[0][2].isEmpty())
            return true;

        // Returns true if any of the column
        // is crossed with the same player's move
        for (int i = 0; i < TILES_IN_COLUMN; i++) {
            if (!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) &&
                    board[1][i].equals(board[2][i]))
                return true;
        }

        for (int i = 0; i < TILES_IN_ROW; i++) {
            if (!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) &&
                    board[i][1].equals(board[i][2]))
                return true;
        }

        return false;
    }
}
