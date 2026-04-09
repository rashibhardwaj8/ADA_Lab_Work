public class NQueens {

    static int N = 4;

    // Print the board
    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if it's safe to place queen
    static boolean isValid(int[][] board, int row, int col) {

        // Check left row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Upper diagonal
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }

        // Lower diagonal
        i = row;
        j = col;
        while (i < N && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    // Solve using backtracking
    static boolean solveNQueen(int[][] board, int col) {
        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 1;

                if (solveNQueen(board, col + 1)) {
                    return true;
                }

                board[i][col] = 0; // Backtrack
            }
        }
        return false;
    }

    // Main function
    static boolean checkSolution() {
        int[][] board = new int[N][N];

        if (!solveNQueen(board, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printBoard(board);
        return true;
    }

    public static void main(String[] args) {
        checkSolution();
    }
}
