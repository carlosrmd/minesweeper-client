# minesweeper-client
Java library for accessing minesweeper RESTful API endpoints 

### Dependencies

* Java 11
* Maven

### Library compiling

Download project

```
$ git clone https://github.com/carlosrmd/minesweeper-client.git
$ cd minesweeper-client
```

Build package

```
$ mvn package
```

Package will be at `target/minesweeper-1.0-SNAPSHOT.jar`. Then add to your project as library.

### Library usage

Create `MinesweeperClient` by importing it and the `HttpRoutesHandler` class as well

```java
import com.minesweeper.MinesweeperClient;
import com.minesweeper.integration.HttpRoutesHandler;
```

Initialize the object by passing `HttpRoutesHandler` object as dependency injection.

```java
MinesweeperClient msp = new MinesweeperClient(new HttpRoutesHandler("http://34.72.16.104/"));
```

If you're developing a minesweeper java game you can use the library the following way:

##### Example of displaying registered users

```java
public void displayRegisteredUsers() {
    ArrayList<User> users = msp.getUsers();
    return displayUsersView(users)
}
```

##### Example of creation of new user

```java
public void onClickUserCreation(String userName) {
    msp.createUser(userName);
}
```

##### Example of creating new game

```java
public void onCLickGameCreation(Params params) {
    String newGameId = msp.createGame(params.getUserName(), params.getRowsAmount(), params.getColumnsAmount(), params.getMinesAmount());
    return newGameId;
}
```

##### Example of performing uncovering action while playing game
```java
public void onCLickCell(int cellRow, int cellColumn) {
    Game updatedGameState = msp.gameAction(currentGame.getGameId(), "move", cellRow, cellColumn, "CLICK");
    return updatedGameState;
}
```
