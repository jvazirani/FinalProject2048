public class Board {
    private GameViewer window;
    private tile[][] board;

    public Board(tile[][] board){
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

}
