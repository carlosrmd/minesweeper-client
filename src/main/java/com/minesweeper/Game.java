package com.minesweeper;

import java.util.ArrayList;

public class Game {

    private String gameId;
    private String startedAt;
    private String userName;
    private ArrayList<String> board;
    private final String status;
    private String result;
    private float totalTime;


    public Game(String gameId, String status, String result, float totalTime, String startedAt, String userName) {
        this.gameId = gameId;
        this.status = status;
        this.result = result;
        this.totalTime = totalTime;
        this.startedAt = startedAt;
        this.userName = userName;
    }

    public Game(String gameId, String status, String startedAt, String userName) {
        this.gameId = gameId;
        this.status = status;
        this.startedAt = startedAt;
        this.userName = userName;
    }

    public Game(String status, ArrayList<String> board, String result, float totalTime) {
        this.board = board;
        this.status = status;
        this.result = result;
        this.totalTime = totalTime;
    }

    public Game(String status, ArrayList<String> board) {
        this.board = board;
        this.status = status;
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
