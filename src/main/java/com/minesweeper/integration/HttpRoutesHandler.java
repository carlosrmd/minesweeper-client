package com.minesweeper.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minesweeper.Game;
import com.minesweeper.User;
import com.minesweeper.integration.models.GamesResponse;
import com.minesweeper.integration.models.UsersResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpRoutesHandler {

    private final String baseUri;

    private ArrayList<String> convertBoard(String[][] board) {
        ArrayList<String> convertedBoard = new ArrayList<>();
        for(String[] row: board) {
            convertedBoard.add(String.join("", row));
        }
        return convertedBoard;
    }

    public HttpRoutesHandler(String baseUri) { this.baseUri = baseUri; }

    public ArrayList<User> getUsers() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "users"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body();
            UsersResponse[] ur = objectMapper.readValue(body, UsersResponse[].class);
            ArrayList<User> userList = new ArrayList<>();
            for(UsersResponse user: ur) {
                userList.add(new User(user.getUser_id(), user.getUser_name()));
            }
            return userList;
        }
        return null;
    }

    public String postUser(String userName) throws IOException, InterruptedException {
        HashMap<String, String> values = new HashMap<>();
        values.put("user_name", userName);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return userName;
        }

        return null;
    }

    public ArrayList<Game> getGames(String userId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "games?user_id=" + userId))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body();

            GamesResponse[] gr = objectMapper.readValue(body, GamesResponse[].class);
            ArrayList<Game> gameList = new ArrayList<>();
            for(GamesResponse game: gr) {
                if(game.getStatus().equals("FINISHED")) {
                    gameList.add(
                            new Game(
                                    game.getGame_id(),
                                    game.getStatus(),
                                    game.getResult(),
                                    game.getTotal_time(),
                                    game.getStarted_at(),
                                    game.getUser_name()
                            )
                    );
                } else {
                    gameList.add(
                            new Game(
                                    game.getGame_id(),
                                    game.getStatus(),
                                    game.getStarted_at(),
                                    game.getUser_name()
                            )
                    );
                }
            }
            return gameList;
        }

        return new ArrayList<>();
    }

    public Game getGameState(String gameId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "games/" + gameId + "/state"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        if(response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = response.body();

            GamesResponse gr = objectMapper.readValue(body, GamesResponse.class);

            if(gr.getStatus().equals("FINISHED")) {
                return new Game(gr.getStatus(), convertBoard(gr.getBoard()), gr.getResult(), gr.getTotal_time());
            } else {
                return new Game(gr.getStatus(), convertBoard(gr.getBoard()));
            }

        }

        return null;
    }

    public String postGame(String userName,  int rows, int columns, int mines) throws IOException, InterruptedException {
        HashMap<String, Object> values = new HashMap<>();
        values.put("user_name", userName);
        values.put("n_rows", rows);
        values.put("n_cols", columns);
        values.put("n_mines", mines);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "games"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200) {
            objectMapper = new ObjectMapper();
            String body = response.body();

            GamesResponse gr = objectMapper.readValue(body, GamesResponse.class);

            return gr.getGame_id();
        }
        return null;
    }

    public Game putGameAction(String gameId, String action, int cellRow, int cellColumn, String moveType) throws IOException, InterruptedException {
        HashMap<String, Object> values = new HashMap<>();
        values.put("row", cellRow);
        values.put("column", cellColumn);
        values.put("move_type", moveType);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUri + "games/" + gameId + "/" + action))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        if(response.statusCode() == 200) {
            objectMapper = new ObjectMapper();
            String body = response.body();

            GamesResponse gr = objectMapper.readValue(body, GamesResponse.class);

            if(gr.getStatus().equals("FINISHED")) {
                return new Game(gr.getStatus(), convertBoard(gr.getBoard()), gr.getResult(), gr.getTotal_time());
            } else {
                return new Game(gr.getStatus(), convertBoard(gr.getBoard()));
            }

        }

        return null;
    }

}
