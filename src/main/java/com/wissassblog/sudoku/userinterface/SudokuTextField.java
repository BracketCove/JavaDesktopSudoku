package com.wissassblog.sudoku.userinterface;

import javafx.scene.control.TextField;

public class SudokuTextField extends TextField {
    private final int x;
    private final int y;

    public SudokuTextField(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*
    For some reason, when I override these two functions, the TextFields stop duplicating numeric inputs...
     */
    @Override
    public void replaceText(int i, int i1, String s) {
        if (!s.matches("[0-9]")) {
            super.replaceText(i, i1, s);
        }
    }


    @Override
    public void replaceSelection(String s) {
        if (!s.matches("[0-9]")) {
            super.replaceSelection(s);
        }
    }


}
