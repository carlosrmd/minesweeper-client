package com.minesweeper.integration.models;

import java.util.ArrayList;

public class GamesResponse {

    private String game_id;
    private String started_at;
    private String user_name;
    private String[][] board;
    private String status;
    private String result;
    private float total_time;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal_time(float total_time) {
        this.total_time = total_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public float getTotal_time() {
        return total_time;
    }

    public String getGame_id() {
        return game_id;
    }

    public String getStarted_at() {
        return started_at;
    }

    public String[][] getBoard() {
        return board;
    }
}
