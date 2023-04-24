// Board class for our general connect4 game logic
// Initializes internal board
// handles board methods like validMove, move, checkWinner(buggy)
// it also handles the reversal of a move, and starting a new game


public class Board {
    private int[][] board; // 0 = unoccupied, 1 = player1, 2 = player2
    public int currentPlayer = 1;

    public Board() {
        board = new int[6][7];
    }

    public boolean validMove(int row, int col) {
        if (board[row][col] != 0) return false;

        if (row != 5) {
            if (row == 0) {
                if (board[row + 1][col] != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (board[row + 1][col] != 0 && board[row - 1][col] == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void move(int row, int col, int player) {
        board[row][col] = player;
        currentPlayer = switchPlayer(player);
    }

    public int checkWinner() {
        int row;
        int col;
        int count = 0;

        // Horizontal and vertical checks
        for (row = 0; row < 6; row++) {
            for (col = 0; col < 7; col++) {
                if (board[row][col] != 0) {
                    // Horizontal Check
                    if (col <= 3) {
                        for (int a = col; a <= col + 3; a++) {
                            if (board[row][a] == board[row][col]) {
                                count++;
                            }
                        }
                        if (count == 4) {
                            return board[row][col];
                        } else {
                            count = 0;
                        }
                    }

                    // Vertical Check
                    if (row <= 2) {
                        for (int b = row; b <= row + 3; b++) {
                            if (board[b][col] == board[row][col]) {
                                count++;
                            }
                        }
                        if (count == 4) {
                            return board[row][col];
                        } else {
                            count = 0;
                        }
                    }
                }
            }
        }

        // Diagonal checks
        for (row = 0; row <= 2; row++) {
            for (col = 0; col <= 3; col++) {
                if (board[row][col] != 0) {
                    // Right-up Diagonal Check
                    for (int a = 0; a < 4; a++) {
                        if (board[row + a][col + a] == board[row][col]) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                }
            }
        }

        for (row = 5; row >= 3; row--) {
            for (col = 0; col <= 3; col++) {
                if (board[row][col] != 0) {
                    // Right-down Diagonal Check
                    for (int a = 0; a < 4; a++) {
                        if (board[row - a][col + a] == board[row][col]) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                }
            }
        }
        // No one won
        return 0;
    }

    public void reverseMove(int x, int y) {
        board[x][y] = 0;
        currentPlayer = switchPlayer(currentPlayer);
    }

    public void newGame() {
        currentPlayer = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                board[row][col] = 0;
            }
        }
    }

    public int switchPlayer(int x) {
        if (x == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
