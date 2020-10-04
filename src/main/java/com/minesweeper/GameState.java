package com.minesweeper;

import java.util.ArrayList;

public class GameState {

    private final ArrayList<String> board;
    private final String status;
    private final String result;
    private final float totalTime;


    public GameState(String status, ArrayList<String> board, String result, float totalTime) {
        this.board = board;
        this.status = status;
        this.result = result;
        this.totalTime = totalTime;
    }

    public GameState(String status, ArrayList<String> board) {
        this.board = board;
        this.status = status;
        this.result = "";
        this.totalTime = 0;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getBoard() {
        return board;
    }

}

