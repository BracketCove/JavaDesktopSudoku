package com.wissassblog.sudoku.persistence;

import com.wissassblog.sudoku.problemdomain.SudokuGame;
import com.wissassblog.sudoku.problemdomain.IStorage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;


/**
 * JSON is a simple language which is commonly used for storage and data transfer in Desktop, Web, and Mobile
 * programming. By having one simple language which can be understood by a wide variety of different platforms and
 * operating systems, this makes life easier for us programmers to have our programs communicate with each other, and
 * work on more devices.
 */
public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt"
    );

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try {


            FileOutputStream fileOutputStream =
                    new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {

        FileInputStream fileInputStream =
                new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        } catch (ClassNotFoundException e) {
            throw new IOException("File Not Found");
        }
    }
}
