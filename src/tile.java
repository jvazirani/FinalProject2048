import java.awt.*;
public class tile {
    private String marker;
    private int row;
    private int col;
    private boolean isWinningSquare;
    private final int SQUARE_HEIGHT = 100;
    private GameViewer g;
    private int value;
    public tile(int row, int col){
        this.row = row;
        this.col = col;
//        this.marker = Board.BLANK;
        this.isWinningSquare = false;
        value = generateNewVal();
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

    public int getValue(){
        return value;
    }

    public void setValue(int newVal){
        value = newVal;
    }



//    public void draw(Graphics g, GameViewer game){
//        //
//    }

//    public String toString(){
//        return this.marker;
//    }


}
