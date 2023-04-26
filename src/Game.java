import java.util.Scanner;

public class Game {
    private GameViewer window;
    private Tile[][] board;

    public Game(Tile[][] board){
        this.board = board;
    }

    // Method to shift the squares to the right depending on the tile
    public void shiftUp(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; i++){
                // If a tiles value is equal to the one above it, combine them
                while (board[i][j].getValue() == board[i + 1][j].getValue()){
                    board[i + 1][j].setValue(board[i + 1][j].getValue() * 2);;
                    board[i][j].setValue(0);
                }
            }
        }
    }

    // Checks the board for a win (getting a 2048 tile)
    public boolean checkWin(){
        for(int i = 0; i < board[0].length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j].getValue() == 2048){
                    return true;
                }
            }
        }
        return false;
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

        while(!this.checkWin()){
            // Draw board
            this.printBoard();
            // Collect user input for move
            Scanner s = new Scanner(System.in);
            System.out.println("Move: ");
            String move = s.nextLine();
            // Check for which shift
            // Shift right
            if(move.equals("a"))
            {
                shift_right();
                new_tile();
                draw_board();
            }
            // Shift left
            else if(move.equals("d"))
            {
                shift_left();
                new_tile();
                draw_board();
            }
            // Shift down
            else if(move.equals("s"))
            {
                shift_down();
                new_tile();
                draw_board();
            }
            // Shift up
            else if(move.equals("w"))
            {
                shift_up();
                new_tile();
                draw_board();
            }

    }

    }

}
