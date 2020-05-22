package com.wiseassblog.sudoku.userinterface.logic;


import com.wiseassblog.sudoku.constants.GameState;
import com.wiseassblog.sudoku.constants.Messages;
import com.wiseassblog.sudoku.computationlogic.GameLogic;
import com.wiseassblog.sudoku.problemdomain.IStorage;
import com.wiseassblog.sudoku.problemdomain.SudokuGame;
import com.wiseassblog.sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

/**
 * Since this is a single screen application, just one container (class) for the logic of the user interface is
 * necessary. Break these things up when building applications with more screens/features. Don't build God Classes!
 */

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;
    //Remember, this could be the real UserInterfaceImpl, or it could be a test class
    //which implements the same interface!
    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    /**
     * Use Case:
     * 1. Retrieve current "state" of the data from IStorage
     * 2. Update it according to the input
     * 3. Write the result to IStorage
     * @param x X coordinate of the selected input
     * @param y Y ...
     * @param input Which key was entered, One of:
     *  - Numbers 0-9
     *
     */
    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y] = input;

            gameData = new SudokuGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState
            );

            storage.updateGameData(gameData);

            //either way, update the view
            view.updateSquare(x, y, input);

            //if game is complete, show dialog
            if (gameData.getGameState() == GameState.COMPLETE) view.showDialog(Messages.GAME_COMPLETE);
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());
        } catch (IOException e) {
            view.showError(Messages.ERROR);
        }
    }
}
