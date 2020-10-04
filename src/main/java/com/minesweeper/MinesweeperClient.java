package com.minesweeper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minesweeper.integration.HttpRoutesHandler;

import java.io.IOException;
import java.util.ArrayList;

public class MinesweeperClient {

    private HttpRoutesHandler client;

    public MinesweeperClient(HttpRoutesHandler client) {
        this.client = client;
    }

    public ArrayList<User> getUsers() throws IOException, InterruptedException {
        return this.client.getUsers();
    }

    public String createUser(String userName) throws IOException, InterruptedException {
        return this.client.postUser(userName);
    }

    public ArrayList<Game> getGames(String userId) throws IOException, InterruptedException {
        return this.client.getGames(userId);
    }

    public String createGame(String userName,  int rows, int columns, int mines) throws IOException, InterruptedException {
        return this.client.postGame(userName, rows, columns, mines);
    }

    public Game getGameState(String gameId) throws IOException, InterruptedException {
        return this.client.getGameState(gameId);
    }

    public Game gameAction(String gameId, String action, int cellRow, int cellColumn, String moveType) throws IOException, InterruptedException {
        return this.client.putGameAction(gameId, action, cellRow, cellColumn, moveType);
    }



}
