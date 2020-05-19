import com.wissassblog.sudoku.constants.GameState;
import com.wissassblog.sudoku.problemdomain.SudokuGame;

class TestData {
    //No access modifier means Package-Private access, i.e. only tests within src/test/java
    /**
     * Note: Arrays which do not use an initializer have all elements assigned to 0 by default.
     * This is convenient, as 0 represents an empty square in the sudoku puzzle.
     * generate a valid new puzzle with 30 solved squares
     * @return
     */
    static SudokuGame getValidStart(){
        int[][] validStart = new int[9][9];

        //first row
        validStart[0][0] = 6;
        validStart[4][0] = 4;
        validStart[7][0] = 1;

        //...
        validStart[0][1] = 9;
        validStart[2][1] = 3;
        validStart[7][1] = 2;

        //...
        validStart[1][2] = 5;
        validStart[5][2] = 7;

        validStart[1][3] = 3;
        validStart[3][3] = 2;
        validStart[4][3] = 1;
        validStart[5][3] = 9;
        validStart[8][3] = 4;

        validStart[0][4] = 1;
        validStart[1][4] = 6;
        validStart[2][4] = 2;
        validStart[7][4] = 9;

        validStart[1][5] = 7;
        validStart[4][5] = 8;
        validStart[6][5] = 1;
        validStart[8][5] = 2;

        validStart[1][6] = 4;
        validStart[6][6] = 8;

        validStart[0][7] = 7;
        validStart[1][7] = 2;
        validStart[2][7] = 6;
        validStart[5][7] = 1;
        validStart[6][7] = 3;

        validStart[4][8] = 7;
        validStart[8][8] = 6;

        SudokuGame validStartGame = new SudokuGame(GameState.ACTIVE, validStart);
        return validStartGame;
    }

    static SudokuGame getSolved(){
        int[][] solved = new int[9][9];

        //first group of 9
        solved[0][0] = 6;
        solved[1][0] = 8;
        solved[2][0] = 7;
        solved[0][1] = 9;
        solved[1][1] = 1;
        solved[2][1] = 3;
        solved[0][2] = 2;
        solved[1][2] = 5;
        solved[2][2] = 4;


        //...
        solved[3][0] = 3;
        solved[4][0] = 4;
        solved[5][0] = 2;
        solved[3][1] = 5;
        solved[4][1] = 6;
        solved[5][1] = 8;
        solved[3][2] = 1;
        solved[4][2] = 9;
        solved[5][2] = 7;


        //...
        solved[6][0] = 9;
        solved[7][0] = 1;
        solved[8][0] = 5;
        solved[6][1] = 4;
        solved[7][1] = 2;
        solved[8][1] = 7;
        solved[6][2] = 6;
        solved[7][2] = 8;
        solved[8][2] = 3;

        solved[0][3] = 5;
        solved[1][3] = 3;
        solved[2][3] = 8;
        solved[0][4] = 1;
        solved[1][4] = 6;
        solved[2][4] = 2;
        solved[0][5] = 4;
        solved[1][5] = 7;
        solved[2][5] = 9;

        solved[3][3] = 2;
        solved[4][3] = 1;
        solved[5][3] = 9;
        solved[3][4] = 7;
        solved[4][4] = 3;
        solved[5][4] = 4;
        solved[3][5] = 6;
        solved[4][5] = 8;
        solved[5][5] = 5;

        solved[6][3] = 7;
        solved[7][3] = 6;
        solved[8][3] = 4;
        solved[6][4] = 5;
        solved[7][4] = 9;
        solved[8][4] = 8;
        solved[6][5] = 1;
        solved[7][5] = 3;
        solved[8][5] = 2;

        solved[0][6] = 3;
        solved[1][6] = 4;
        solved[2][6] = 5;
        solved[0][7] = 7;
        solved[1][7] = 2;
        solved[2][7] = 6;
        solved[0][8] = 8;
        solved[1][8] = 9;
        solved[2][8] = 1;

        solved[3][6] = 9;
        solved[4][6] = 2;
        solved[5][6] = 6;
        solved[3][7] = 8;
        solved[4][7] = 5;
        solved[5][7] = 1;
        solved[3][8] = 4;
        solved[4][8] = 7;
        solved[5][8] = 3;

        solved[6][6] = 8;
        solved[7][6] = 7;
        solved[8][6] = 1;
        solved[6][7] = 3;
        solved[7][7] = 4;
        solved[8][7] = 9;
        solved[6][8] = 2;
        solved[7][8] = 5;
        solved[8][8] = 6;

        SudokuGame solvedGame = new SudokuGame(GameState.COMPLETE, solved);

        return solvedGame;
    }

    static SudokuGame getInvalid(){
        int[][] invalid = new int[9][9];

        //first group of 9
        invalid[0][0] = 2;
        invalid[1][0] = 2;
        invalid[2][0] = 2;
        invalid[0][1] = 2;
        invalid[1][1] = 2;
        invalid[2][1] = 2;
        invalid[0][2] = 2;
        invalid[1][2] = 2;
        invalid[2][2] = 2;

        SudokuGame invalidGame = new SudokuGame(GameState.ACTIVE, invalid);

        return invalidGame;
    }
}
