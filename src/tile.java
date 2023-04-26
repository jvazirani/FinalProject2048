import java.awt.*;
public class tile {
    private String marker;
    private int row;
    private int col;
    private boolean isWinningSquare;
    private final int TILE_HEIGHT = 100;
    private GameViewer t;
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



    public void draw(Graphics g, GameViewer game){
        int x = 400;
        int y = 400;
        g.drawImage(t.getImages()[0], x, y, TILE_HEIGHT, TILE_HEIGHT, t);
    }

//    public String toString(){
//        return this.marker;
//    }


}
