package com.wissassblog.sudoku.userinterface;

import com.wissassblog.sudoku.constants.GameState;
import com.wissassblog.sudoku.problemdomain.Coordinates;
import com.wissassblog.sudoku.problemdomain.SudokuGame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Manages the window, and displays a pop up notification when the user completes the puzzle.
 */
public class BadUserInterfaceImpl implements IUserInterfaceContract.View,
        EventHandler<KeyEvent> {
    private final Stage stage;
    private final Group root;

    private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;

    private IUserInterfaceContract.EventListener listener;
    

    public BadUserInterfaceImpl(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.textFieldCoordinates = new HashMap<>();

        Scene scene = new Scene(root, 668, 668);

        Text t = new Text(235, 690, "Sudoku");
        t.setFill(Color.WHITE);
        Font tf = new Font(43);
        t.setFont(tf);
        root.getChildren().add(t);
        Color c = Color.rgb(0, 150, 136);
        scene.setFill(c);
        stage.setScene(scene);

        Rectangle bg = new Rectangle();
        bg.setX(50);
        bg.setY(50);
        bg.setWidth(576);
        bg.setHeight(576);
        bg.setFill(Color.rgb(224, 242, 241));
        root.getChildren().add(bg);

        final int xOrigin = 50;
        final int yOrigin = 50;
        //how much to move the x or y value after each loop
        final int xAndYDelta = 64;

        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xOrigin + xIndex * xAndYDelta;
                int y = yOrigin + yIndex * xAndYDelta;
                //draw it
                SudokuTextField stf = new SudokuTextField(xIndex, yIndex);
                Font numberFont = new Font(32);
                stf.setFont(numberFont);
                stf.setAlignment(Pos.CENTER);
                stf.setLayoutX(x);
                stf.setLayoutY(y);
                stf.setPrefHeight(64);
                stf.setPrefWidth(64);
                stf.setBackground(Background.EMPTY);
                stf.setOnKeyPressed(this);
                textFieldCoordinates.put(new Coordinates(xIndex, yIndex), stf);
                root.getChildren().add(stf);
            }
        }

        int xAndY = 114;
        int index = 0;
        while (index < 8) {
            Rectangle vl = new Rectangle();

            vl.setX(xAndY + 64 * index);
            vl.setY(50);
            vl.setHeight(576);
            if (index == 2 || index == 5) {
                vl.setWidth(3);
            } else {
                vl.setWidth(2);
            }
            vl.setFill(Color.BLACK);

            Rectangle hl = new Rectangle();
            hl.setY(xAndY + 64 * index);
            hl.setX(50);
            hl.setWidth(576);
            if (index == 2 || index == 5) {
                hl.setHeight(3);
            } else {
                hl.setHeight(2);
            }
            hl.setFill(Color.BLACK);

            root.getChildren().addAll(
                    vl,
                    hl
            );

            index++;
        }

        stage.show();

    }


    @Override
    public void setListener(IUserInterfaceContract.EventListener listener) {
        this.listener = listener;
    }

    /**
     * Each time the user makes an input (which can be 0 to delete a number), we update the user
     * interface appropriately.
     */
    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x, y));
        String value = Integer.toString(
                input
        );

        if (value.equals("0")) value = "";

        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(SudokuGame game) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                TextField tile = textFieldCoordinates.get(new Coordinates(xIndex, yIndex));

                String value = Integer.toString(
                        game.getCopyOfGridState()[xIndex][yIndex]
                );

                if (value.equals("0")) value = "";
                tile.setText(
                        value
                );

                //If a given tile has a non-zero value and the state of the game is GameState.NEW, then mark
                //the tile as read only. Otherwise, ensure that it is NOT read only.
                if (game.getGameState() == GameState.NEW){
                    if (value.equals("")) {
                        tile.setStyle("-fx-opacity: 1;");
                        tile.setDisable(false);
                    } else {
                        tile.setStyle("-fx-opacity: 0.8;");
                        tile.setDisable(true);
                    }
                }
            }
        }
    }

    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK) listener.onDialogClick();
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        dialog.showAndWait();
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().equals("0")
                    || event.getText().equals("1")
                    || event.getText().equals("2")
                    || event.getText().equals("3")
                    || event.getText().equals("4")
                    || event.getText().equals("5")
                    || event.getText().equals("6")
                    || event.getText().equals("7")
                    || event.getText().equals("8")
                    || event.getText().equals("9")
            ) {
                int value = Integer.parseInt(event.getText());
                listener.onSudokuInput(
                        ((SudokuTextField) event.getSource()).getX(),
                        ((SudokuTextField) event.getSource()).getY(),
                        value
                );
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                listener.onSudokuInput(
                        ((SudokuTextField) event.getSource()).getX(),
                        ((SudokuTextField) event.getSource()).getY(),
                        0
                );
            } else {
                ((TextField)event.getSource()).setText("");
            }
        }

        event.consume();
    }
}
