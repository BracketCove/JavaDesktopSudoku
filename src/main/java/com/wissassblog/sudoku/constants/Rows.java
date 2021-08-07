package com.wissassblog.sudoku.constants;

/**
 * This enum exists to provide better legibility for the logic required to check if each Square in the
 * sudoku puzzle contains a valid value. See GameLogic.java for usage.
 *
 * Top, Middle, and Bottom rows for each square (a square consists of 3x3 "tiles", with 9 squares total in a
 * sudoku puzzle).
 *
 * The values represent the Y coordinates of each tile.
 */
public enum Rows {
    TOP,
    MIDDLE,
    BOTTOM
}
