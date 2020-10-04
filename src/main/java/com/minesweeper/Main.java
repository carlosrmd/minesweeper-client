package com.minesweeper;


import com.minesweeper.integration.HttpRoutesHandler;

public class Main {


    public static void main(String[] args) throws Exception {

        MinesweeperClient msp = new MinesweeperClient(new HttpRoutesHandler("http://34.72.16.104/"));
        System.out.println(msp.getUsers().get(0).getUserName());
        //System.out.println(msp.createUser("jose"));
        System.out.println(msp.getGames("carlos"));
        System.out.println(msp.getGameState("5f78c0899f6540e80b71ea93").getBoard());
        //String newGame = msp.createGame("carlos", 10, 10, 3);
        System.out.println(msp.gameAction("5f795278021e75b32b92cee0", "end", 0, 0, "CLICK").getBoard());

    }
}
