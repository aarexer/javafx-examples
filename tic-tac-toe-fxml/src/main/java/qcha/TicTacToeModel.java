package qcha;

public final class TicTacToeModel {
    private boolean isPlayerXTurn = true;
    private String[][] board = new String[3][3];
    private boolean isGameOver = false;

    public boolean checkWinner(int row, int column) {
        if (isPlayerXTurn) {
            board[row][column] = "X";
        } else {
            board[row][column] = "Y";
        }

        // Returns true if any of the diagonal
        // is crossed with the same player's move
        if (board[0][0] != null && board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2]))
            return true;

        if (board[0][2] != null && board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0]))
            return true;

        // Returns true if any of the column
        // is crossed with the same player's move
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i].equals(board[1][i]) &&
                    board[1][i].equals(board[2][i]))
                return true;
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) &&
                    board[i][1].equals(board[i][2]))
                return true;
        }

        return false;
    }

    public void reset() {
        isPlayerXTurn = true;
        isGameOver = false;
        board = new String[3][3];
    }

    public boolean isPlayerXTurn() {
        return isPlayerXTurn;
    }

    public void setPlayerXTurn(boolean isPlayerXTurn) {
        this.isPlayerXTurn = isPlayerXTurn;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public void exit() {
        System.exit(0);
    }
}
