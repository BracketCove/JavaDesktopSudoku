package com.wissassblog.sudoku.computationlogic;

//Collection of static functions which may be used to determine events and new Game states.

import com.wissassblog.sudoku.constants.GameState;
import com.wissassblog.sudoku.constants.Rows;
import com.wissassblog.sudoku.problemdomain.SudokuGame;

import java.util.*;

import static com.wissassblog.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

/**
 * Q: Why isn't this a class hidden behind an interface?
 * A: It requires no external libraries, nor do I ever plan to switch to using external libraries.
 */
public class GameLogic {

    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    /**
     * Check to see if the incoming state (what the values of each square happen to be) of the game is either Active
     * (i.e. Unsolved) or Complete (i.e. Solved).
     *
     * @param grid A virtual representation of a sudoku puzzle, which may or may not be solved.
     * @return Either GameState.Active or GameState.Complete, based on analysis of solvedSudoku.
     * <p>
     * Rules:
     * - A number may not be repeated among Rows, e.g.:
     * - [0, 0] == [0-8, 1] not allowed
     * - [0, 0] == [3, 4] allowed
     * - A number may not be repeated among Columns, e.g.:
     * - [0-8, 1] == [0, 0] not allowed
     * - [0, 0] == [3, 4] allowed
     * - A number may not be repeated within respective GRID_BOUNDARYxGRID_BOUNDARY regions within the Sudoku Puzzle
     * - [0, 0] == [1, 2] not allowed
     * - [0, 0] == [3, 4] allowed
     */
    public static GameState checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    /**
     * Traverse all tiles and determine if any all are not 0.
     * Note: GRID_BOUNDARY = GRID_BOUNDARY
     *
     * @param grid
     * @return
     */
    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }

    /**
     * Checks the if the current complete or incomplete state of the game is still a valid state of a Sudoku game,
     * relative to columns, rows, and squares.
     *
     * @param grid
     * @return
     */
    public static boolean sudokuIsInvalid(int[][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (squaresAreInvalid(grid)) return true;
        else return false;
    }


    /**
     * For the purposes of giving specific names to specific things, a "Square" is one of the 3x3 portions of the
     * Sudoku puzzle, containing GRID_BOUNDARY "Tiles".
     * <p>
     * Example square:
     * [0][0], [1][0], [2][0]
     * [0][1], [1][1], [2][1]
     * [0][2], [1][2], [2][2]
     * <p>
     * How can I solve this problem elegantly?
     * 1. Compare every single element in the array to every other element in the array? (hell no)
     * 2. Use some dope problem solving skills to select for each square
     * and compare them individually. (sounds much better to me)
     * <p>
     * Ranges:
     * [0][0] - [2][2], [3][0] - [5][2], [6][0] - [8][2]
     * <p>
     * [0][3] - [2][2], [3][3] - [5][5], [6][3] - [8][5]
     * <p>
     * [0][6] - [2][2], [3][0] - [5][2], [6][0] - [8][8]
     *
     * @param grid A copy of the Sudoku Game's grid state to compare against
     * @return
     */
    public static boolean squaresAreInvalid(int[][] grid) {
        //top three squares
        if (rowOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        //middle three
        if (rowOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        //bottom three
        if (rowOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                //x FIRST = 0
                if (squareIsInvalid(0, 0, grid)) return true;
                //x SECOND = 3
                if (squareIsInvalid(0, 3, grid)) return true;
                //x THIRD = 6
                if (squareIsInvalid(0, 6, grid)) return true;

                //Otherwise squares appear to be valid
                return false;

            case MIDDLE:
                if (squareIsInvalid(3, 0, grid)) return true;
                if (squareIsInvalid(3, 3, grid)) return true;
                if (squareIsInvalid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if (squareIsInvalid(6, 0, grid)) return true;
                if (squareIsInvalid(6, 3, grid)) return true;
                if (squareIsInvalid(6, 6, grid)) return true;
                return false;

            default:
                return false;
        }
    }

    public static boolean squareIsInvalid(int yIndex, int xIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {

            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }

            //reset x to original value by subtracting by 2
            xIndex -= 3;

            yIndex++;
        }

        //if square has repeats, return true
        if (collectionHasRepeats(square)) return true;
        return false;
    }

    public static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    public static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    public static boolean collectionHasRepeats(List<Integer> collection) {
        //count occurrences of integers from 1-GRID_BOUNDARY. If Collections.frequency returns a value greater than 1,
        //then the square is invalid (i.e. a non-zero number has been repeated in a square)
        for (int index = 1; index <= GRID_BOUNDARY; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }
}
