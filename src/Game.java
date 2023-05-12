import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game {
    private GameViewer window;
    private Tile[][] board;
    private Image[] numbers;
    private int score;
    private int bestScore;

    // Variables that help determine if a new tile should be generated at the end of a move
    private boolean canShiftLeft;
    private boolean canShiftRight;
    private boolean canShiftUp;
    private boolean canShiftDown;

    private final int NUMBERS_SIZE = 11;

    private final int BOARD_WIDTH = 4;
    private final int BOARD_HEIGHT = 4;

    public Game(){
        numbers = new Image[NUMBERS_SIZE];
        for(int i = 0; i < numbers.length; i++){
            // Assign each element of numbers to its image, they are powers of two
            numbers[i] = new ImageIcon("Resources/" + (int) Math.pow(2, i + 1) + ".png").getImage();
        }

        this.board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
        for(int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = new Tile();
            }
        }
        score = 0;
        bestScore = 0;
        window = new GameViewer(this);

    }

    public Tile[][] getBoard(){
        return board;
    }

    public String getInstructions(){
        return "How to play: Combine alike numbers to try to make higher numbers. You can do this " +
                "by using the arrow keys to shift the board up, right, left, or down. The goal of the game" +
                "is to get a 2048 tile. Click the space bar for a new game. ";
    }

    // Resets the board back to its original state
    public void reset(){
        for (Tile[] tiles : board) {
            for (int j = 0; j < board[0].length; j++) {
                tiles[j].setValue(0);
                tiles[j].setTileImage(null);
                score = 0;
            }
        }
        this.newTile();
        this.newTile();
    }

    // Generates a new tile on a random spot on the board
    public void newTile(){
        int randXcoordinate = (int) (Math.random() * 4);
        int randYcoordinate = (int) (Math.random() * 4);
        // While that spot is taken, keep generating values
        while (!(this.board[randXcoordinate][randYcoordinate].isEmpty())){
            randXcoordinate = (int) (Math.random() * 4);
            randYcoordinate = (int) (Math.random() * 4);
            if(this.checkLose()){
                return;
            }
        }
        board[randXcoordinate][randYcoordinate].setValue(generateNewVal());
    }


    // Method that generates a 2 90% of the time and a 4 10% of the time and returns the number generated
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

    // Method to shift the board up
    public void shiftUp(){
        // A variable that helps determine if a new tile should be generated at the end of a move
        canShiftUp = false;
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
                    canShiftUp = true;
                    updateScore(board[i][j].getValue());
                }
                shiftZerosDown();
            }
        }
        // If tiles actually combined and moved, then generate a new tile
        if(canShiftUp){
            newTile();
        }
    }

    // Helper method for shiftUp() that shifts all the zeros down
    public void shiftZerosDown(){
        for (int j = 0; j < board.length; j++){
            for (int i = 0; i < board.length - 1; i++) {
                // If there is a zero, move it one down
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i + 1][j].getValue());
                    board[i + 1][j].setValue(0);
                    if(board[i + 1][j].getValue() != 0){
                        canShiftUp = true;
                    }
                }
            }
        }
    }

    public void shiftDown() {
        canShiftDown = false;
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
                    canShiftDown = true;
                    updateScore(board[i][j].getValue());
                }
                // Move any excess zeros back up
                shiftZerosUp();
            }
        }
        if(canShiftDown){
            newTile();
        }
    }

    public void shiftZerosUp(){
        for (int j = 0; j < board.length; j++){
            for (int i = board[0].length - 1; i > 0; i--) {
                // if you run into a zero, move it one space to the left by swapping it
                if(board[i][j].getValue() == 0) {
                    board[i][j].setValue(board[i-1][j].getValue());
                    board[i-1][j].setValue(0);
                    if(board[i - 1][j].getValue() != 0){
                        canShiftDown = true;
                    }
                }
            }
        }
    }


    public void shiftRight(){
        canShiftRight = false;
        shiftZerosLeft();
        for (Tile[] tiles : board) {
            // Starts at index 3 because prioritizing combinations to the right
            for (int j = board[0].length - 1; j > 0; j--) {
                if (tiles[j].getValue() == tiles[j - 1].getValue()) {
                    // Combine them
                    tiles[j].setValue(tiles[j - 1].getValue() * 2);
                    tiles[j - 1].setValue(0);
                    canShiftRight = true;
                    updateScore(tiles[j].getValue());
                }
                shiftZerosLeft();
            }
        }
        if(canShiftRight){
            newTile();
        }
    }

    void shiftZerosLeft(){
        for (Tile[] tiles : board) {
            for (int j = board[0].length - 1; j > 0; j--) {
                // If there is a zero, move it one to the left
                if (tiles[j].getValue() == 0) {
                    tiles[j].setValue(tiles[j - 1].getValue());
                    tiles[j - 1].setValue(0);
                    if (tiles[j - 1].getValue() != 0) {
                        canShiftRight = true;
                    }
                }
            }
        }
    }

    public void shiftLeft() {
        canShiftLeft = false;
        // Move all the zeros to the right
        shiftZerosRight();
        // Start everything at top left index, because prioritizing the left combos
        for (Tile[] tiles : board) {
            for (int j = 0; j < board[0].length - 1; j++) {
                // If it is equal to its neighbor
                if (tiles[j].getValue() == tiles[j + 1].getValue()) {
                    tiles[j].setValue(tiles[j].getValue() * 2);
                    tiles[j + 1].setValue(0);
                    canShiftLeft = true;
                    updateScore(tiles[j].getValue());
                }
                // Move all the zeros back
                shiftZerosRight();
            }
        }
        if(canShiftLeft){
            newTile();
        }
    }

    public void shiftZerosRight(){
        for (Tile[] tiles : board) {
            for (int j = 0; j < board[0].length - 1; j++) {
                // If there's a zero, move it one to the right
                if (tiles[j].getValue() == 0) {
                    tiles[j].setValue(tiles[j + 1].getValue());
                    tiles[j + 1].setValue(0);
                    if (tiles[j + 1].getValue() != 0) {
                        canShiftLeft = true;
                    }
                }
            }
        }
    }

    // Checks the board for a win (getting a 2048 tile)
    // Returns true if there is a win
    public boolean checkWin(){
        for (Tile[] tiles : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (tiles[j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns true if there is a loss
    // Returns false if there is not a loss
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
            if(board[i][startCoor].getValue() == 0){
                return false;
            }
            if(board[i][startCoor].getValue() == board[i+1][startCoor].getValue()){
                return false;
            }
        }
        return true;
    }
    // Helper method for lose, returns false if there are two adjacent tiles in a row
    public boolean checkRow(int startCoor){
        for(int i = 0; i < board.length - 1; i++){
            if(board[startCoor][i].getValue() == 0){
                return false;
            }
            if(board[startCoor][i].getValue() == board[startCoor][i + 1].getValue()){
                return false;
            }
        }
        return true;
    }

    public void updateScore(int val){
        score += (val);
    }

    public int getScore(){
        return score;
    }

    public void updateBestScore(){
        if(score > bestScore){
            bestScore = score;
        }
    }

    public int getBestScore(){
        return bestScore;
    }
    public void run(){
        this.newTile();
        this.newTile();
        while(!this.checkLose()){
            window.repaint();
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        game.run();
    }

}
