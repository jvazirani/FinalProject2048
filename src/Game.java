import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game {
    private GameViewer window;
    private Tile[][] board;

    private Image[] numbers;

    public Game(){
        numbers = new Image[11];
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = new ImageIcon("Resources/" + (int) Math.pow(2, i + 1) + ".png").getImage();
        }
//        numbers[0] = new ImageIcon("Resources/2.png").getImage();
//        numbers[1] = new ImageIcon("Resources/4.png").getImage();
//        numbers[2] = new ImageIcon("Resources/8.png").getImage();
//        numbers[3] = new ImageIcon("Resources/16.png").getImage();
//        numbers[4] = new ImageIcon("Resources/32.png").getImage();
//        numbers[5] = new ImageIcon("Resources/64.png").getImage();
//        numbers[6] = new ImageIcon("Resources/128.png").getImage();
//        numbers[7] = new ImageIcon("Resources/256.png").getImage();

        this.board = new Tile[4][4];
        for(int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = new Tile(i, j);
            }
        }
        window = new GameViewer(this);

    }
    public Image[] getImages(){
        return numbers;
    }

    public Tile[][] getBoard(){
        return board;
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

    // Method to shift the board up
    public void shiftUp(){
        // Move all the zeros down
        shiftZerosDown();
        // Start indexes at 0, so prioritizing combos at top
        for(int j = 0; j < board.length; j++){
            for (int i = 0; i < board[0].length - 1; i++){
                // If a tiles value is equal to the one above it, combine them
                if (board[i][j].getValue() == board[i + 1][j].getValue()){
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    // Set other tile to zero
                    board[i + 1][j].setValue(0);
                }
                shiftZerosDown();
            }
        }
    }

    // Helper method that shifts all the zeros down
    public void shiftZerosDown(){
        for (int j = 0; j < board.length; j++){
            for (int i = 0; i < board.length - 1; i++) {
                // If there is a zero, move it one down
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i + 1][j].getValue());
                    board[i + 1][j].setValue(0);
                }
            }
        }
    }

    public void shiftDown() {
        // Move all the zeros up before making comparisons
        shiftZerosUp();
        for (int j = 0; j < board.length; j++){
            // Goes from index 3 to 0
            // Because prioritizes combinations down first
            for (int i = board[0].length - 1; i > 0; i--){
                // If a boards value is equal to the one above it, combine them
                if (board[i][j].getValue() == board[i - 1][j].getValue()){
                    board[i][j].setValue(board[i-1][j].getValue() *  2);
                    board[i - 1][j].setValue(0);
                }
                // Move any excess zeros back up
                shiftZerosUp();
            }
        }
    }

    public void shiftZerosUp(){
        for (int j = 0; j < board.length; j++){
            for (int i = board[0].length - 1; i > 0; i--) {
                // if you run into a zero, move it one space to the left by swapping it
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i-1][j].getValue());
                    board[i-1][j].setValue(0);
                }
            }
        }
    }


    public void shiftRight(){
        shiftZerosLeft();
        for (int i = 0; i < board.length; i++){
            // Starts at index 3 because prioritizing combinations to the right
            for (int j = board[0].length - 1; j > 0; j--){
                if(board[i][j].getValue() == board[i][j - 1].getValue()){
                    // Combine them
                    board[i][j].setValue(board[i][j - 1].getValue() * 2);
                    board[i][j - 1].setValue(0);
                }
                shiftZerosLeft();
            }
        }
    }

    void shiftZerosLeft(){
        for (int i = 0; i < board.length; i++) {
            for (int j = board[0].length - 1; j > 0; j--) {
                // If there is a zero, move it one to the left
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i][j - 1].getValue());
                    board[i][j-1].setValue(0);
                }
            }
        }
    }


    public void shiftLeft() {
        // Move all the zeros to the right
        shiftZerosRight();
        // Start everything at top left index, because prioritizing the left combos
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length - 1; j++) {
                // If it is equal to its neighbor
                if (board[i][j].getValue() == board[i][j+1].getValue()) {
                    board[i][j].setValue(board[i][j].getValue() * 2);
                    board[i][j+1].setValue(0);
                }
                //  Move all the zeros back
                shiftZerosRight();
            }
        }
    }

    public void shiftZerosRight(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length - 1; j++) {
                // If there's a zero, move it one to the right
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i][j + 1].getValue());
                    board[i][j + 1].setValue(0);
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
    // TODO fix method so cols together
    public boolean checkLose(){
        int value;
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[0].length - 1; j++) {
                value = board[i][j].getValue();
                if (value == 0) {
                    return false;
                }
                else if((value == board[i][j + 1].getValue()) || (value == board[i][j - 1].getValue())
                        || (value == board[i + 1][j].getValue()) || ( value == board[i - 1][j].getValue())){
                    return false;
                }
            }
        }
        return checkCol(0) && checkCol(board.length - 1) && checkRow(0)
                && checkRow(board.length - 1);
    }


    // Helper method for lose, returns false if there are two adjacent tiles in a col
    public boolean checkCol(int startCoor){
        for(int i = 0; i < board[0].length - 1; i++){
            if(board[i][startCoor].getValue() == board[i+1][startCoor].getValue()){
                return false;
            }
        }
        return true;
    }
    // Helper method for lose, returns false if there are two adjacent tiles in a row
    public boolean checkRow(int startCoor){
        for(int i = 0; i < board.length - 1; i++){
            if(board[startCoor][i].getValue() == board[startCoor][i + 1].getValue()){
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
        this.newTile();
        this.newTile();
        this.printBoard();
        while(!this.checkLose()){
            window.repaint();
            // Collect user input for move
            Scanner s = new Scanner(System.in);
            System.out.println("Move: ");
            String move = s.nextLine();
            // Check for which shift
            // Shift right
            if(move.equals("a"))
            {
                shiftLeft();
                newTile();
                this.printBoard();
            }
            // Shift left
            else if(move.equals("d"))
            {
                shiftRight();
                newTile();
                this.printBoard();
            }
            // Shift down
            else if(move.equals("s"))
            {
                shiftDown();
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
        System.out.println("YOU LOSE");

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
