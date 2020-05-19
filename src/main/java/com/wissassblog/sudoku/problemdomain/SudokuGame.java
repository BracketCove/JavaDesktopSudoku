package com.wissassblog.sudoku.problemdomain;

import com.wissassblog.sudoku.computationlogic.SudokuUtilities;
import com.wissassblog.sudoku.constants.GameState;

import java.io.Serializable;

/**
 * Remember, a program contains representations of real world objects (i.e. Money, User, Game). These things
 * contain real world information (although they may also contain useless/stub/test information), and are necessary
 * for a program which does anything practical.
 *
 * A game of sudoku is a board, which contain 81 squares.
 *
 */
public class SudokuGame implements Serializable {
    private final GameState gameState;
    private final int[][] gridState;

    /**
     * To make it easier to work with Arrays (where the first index position is 0 instead of 1, and so on),
     * Grid coordinates will be represented with x and y index values ranging from 0 (inclusive) to 8 (inclusive).
     */
    public static final int GRID_BOUNDARY = 9;

    /**
     * I suppose that the most fundamental states required to represent a sudoku game, are an active state and a
     * complete state. The game will start in Active state, and when a Complete state is achieved (based on GameLogic),
     * then a special UI screen will be displayed by the user interface.
     *
     * To avoid Shared Mutable State (Shared change-able data), which causes many problems, I have decided to make this
     * class Immutable (meaning that once I created an instance of it, the values may only be read via getGameState()
     * and getGridState() functions, a.k.a. methods. Each time the gridState changes, a new SudokuGame object is created
     * by taking the old one, applying some functions to each, and generated a new one.
     *
     * @param gameState I have decided to make the initial potential states of the game to be an ENUM (a set of custom
     *                  constant values which I give legible names to), one of:
     *                  - GameState.Complete
     *                  - GameState.Active
     *
     * @param gridState The state of the sudoku game. If certain conditions are met (all locations in the gridstate
     *                  are filled in with the proper value), GameLogic must change gameState.
     *                  Examples:
     *                  - gridState[1,1] Top left square
     *                  - gridState[3,9] 3rd from the left, bottom row
     *                  - gridState[9,9] Bottom right square
     */
    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }

}
