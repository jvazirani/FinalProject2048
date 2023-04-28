import java.util.Scanner;

public class Game {
    private GameViewer window;
    private Tile[][] board;

    public Game(){
        this.board = new Tile[4][4];
        for(int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = new Tile(i, j);
            }
        }
    }

    public int generateNewVal(){
        // Generate random num between 1 and 10
        int random = (int) ((Math.random() * 10) + 1);
        // Generate a 2 90% of the time, so random will be greater than 1 90% of the time
        if (random > 1){
            return 2;
        }
        // Other 10% of the time generate a 4
        return 4;
    }

    // Generates a new tile on a random spot on the board
    public void newTile(){
        int randXcoordinate = (int) (Math.random() * 3);
        int randYcoordinate = (int) (Math.random() * 3);
        // While there is not a space on the board, keep generating values
        while (!(this.board[randXcoordinate][randYcoordinate].isEmpty())){
            randXcoordinate = (int) (Math.random() * 3);
            randYcoordinate = (int) (Math.random() * 3);
        }
        board[randXcoordinate][randYcoordinate].setValue(generateNewVal());

    }

    // Method to shift the squares to the right depending on the tile
    public void shiftUp(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; i++){
                // If the space above the value is empty, move it up first
                if (board[i + 1][j].getValue() == 0){
                    board[i + 1][j].setValue(board[i][j].getValue());
                    board[i][j].setValue(0);
                }
                // If a tiles value is equal to the one above it, combine them
                if (board[i][j].getValue() == board[i + 1][j].getValue()){
                    board[i + 1][j].setValue(board[i + 1][j].getValue() * 2);
                    // Then set the original value back to empty/0
                    board[i][j].setValue(0);
                }
            }
        }
    }

    // Checks the board for a win (getting a 2048 tile)
    // Returns true if there is a win
    public boolean checkWin(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j].getValue() == 2048){
                    return true;
                }
            }
        }
        return false;
    }

    // Returns true if there is a lose
    // Returns false if there is not a lose
    public boolean checkLose(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++)
                if(board[i][j].getValue() == 0){
                    return false;
                }
        }
        return true;
    }

    // Prints the board
    public void printBoard() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }
    public void run(){
        System.out.println("Welcome to 2048!");
        this.printBoard();
        while(!this.checkLose()){
            // Draw board
            this.printBoard();
            this.newTile();
            // Collect user input for move
            Scanner s = new Scanner(System.in);
            System.out.println("Move: ");
            String move = s.nextLine();
            // Check for which shift
            // Shift right
            if(move.equals("a"))
            {
                shift_right();
                newTile();
                this.printBoard();
            }
            // Shift left
            else if(move.equals("d"))
            {
                shift_left();
                newTile();
                this.printBoard();
            }
            // Shift down
            else if(move.equals("s"))
            {
                shift_down();
                newTile();
                this.printBoard();
            }
            // Shift up
            else if(move.equals("w"))
            {
                shiftUp();
                newTile();
                this.printBoard();
            }

    }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
