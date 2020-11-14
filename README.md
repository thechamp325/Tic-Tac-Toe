# TicTacToe using MVC paradigm
TicTacToe.java is a basic Java implementation of the well-known Tic Tac Toe game using MVC approach.

### Design Choices
#### Model (M) - Contains the current state of the game and logic for checking win or tie
#### View (V) - Contains the graphical user interface for interacting with the game
#### Controller (C) - Contains the interface between View and Model
#### Computer Mode - Contains an AI bot built using Minimax Algorithm
### Game Flow

The driver class creates instances of `Model`, `View` and `Controller` classes. The game starts with the `View` populating the graphical user interface and adding buttons for control. The `Adapter` then monitors the button for an event, and defines the `actionPerformed` for the `ActionListeners`. The `Adapter` class transfers control to the `Controller` class which notifies the `Model` that a move has been made on the (x,y) coordinate of the tic tac toe grid. The `Model` then updates its board state and accordingly updates the `View`. It also checks after every move if a winning condition has been achieved by a player or whether the game has tied and notifies the `View` with an appropriate message. The `Reset` button allows the user to clear the baord and start a new game.

### Classes and Functionality

#### App class
This is the Driver class for the TicTacToe game. It creates objects of Model, View and Controller classes and aggregates them.

#### Model Class
The model class is where the current state of the game as well as the winning logic resides. The model class calls the view to update the gui according to the current state of the game.

Members:
```java
private View v
private int playerId
private int movesCount
private char[][] board
private String message
private boolean mode
```

Methods (apart from getters and setters:
```java
public Model()
public void registerView(View v)
public void PlayMove(int x, int y)
public boolean isWinner(int x, int y)
public void ResetModel()
```

#### View Class
The View class is responsible for setting up the gui and displaying the state of the game on the gui as informed by the model.

Members:
```java
private Adapter adapter
private JFrame gui
private JButton[][] blocks
private JButton reset
private JTextArea playerturn
private Computer comp
```

Methods:
```java
public View() 
public void setActionListener(Controller c)
public void initialize ()
public boolean isReset(ActionEvent e) 
public boolean isHnh(ActionEvent e)
public boolean isHnc(ActionEvent e)
public ArrayList<Integer> getPosition(ActionEvent e) 
public void update(int row, int column, char symbol, String message)
public void isWinner(int row, int column, char symbol, String message)
public void resetGame()
public void setComp(Computer comp)
public void compPlay(Computer comp)

```

#### Controller Class
The Controller class is responsible for requesting the model to update its state whenever there is an event on a button on the game board.

Members:
```java
private Model m
```

Methods:
```java
public void setModel(Model m) 
public void setRequest(ArrayList<Integer> position) 
public void setRequest()
public void setHnh()
public void sethnc()

```

#### Adapter Class implements ActionListener
The adapter class acts as an interface between view and controller to allow decoupling. It listens for actions on the buttons and invokes the controller class to take appropriate action.

Members:
```java
private Controller c
private View v
```

Methods:
```java
public Adapter(Controller c, View v)
public void actionPerformed(ActionEvent e)
```

#### Computer Class
The Computer Class is an AI Bot implemented using Minimax Algorithm. It generates the best possible move and feeds it to the View.

Members:
```java
private Model m
private char[][] board
private final int playerId=2
static class Move
static char player
static char opponent
```

Methods:
```java
public void setModel(Model m)
public ArrayList<Integer> getMove()
public Move findBestMove()
public int minimax(int depth,boolean isMax)
public boolean isMovesLeft()
public int evaluate()
public void actionPerformed(ActionEvent e)
```


### How to build and test (from Terminal):

1. Make sure that you have Apache Maven installed.

2. Run `mvn clean install` in the root directory, which contains the `pom.xml` file.

3. Compiled java classes will be in the `target` directory.

4. Run `mvn test` to run all unit tests.

### How to run (from Terminal):

1. Build the project using `mvn clean package`
2. Then Run the jar using `java -cp bin TicTacToe`

### How to clean up (from Terminal):

1. Run `mvn clean` to clean the project (i.e. delete all generated files).
