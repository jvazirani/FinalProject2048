import java.awt.*;
public class Tile {
    private String marker;
    private int row;
    private int col;
    private boolean isWinningSquare;
    private final int TILE_HEIGHT = 100;
    private GameViewer game;
    private int value;
    public Tile(int row, int col){
        this.row = row;
        this.col = col;
//        this.marker = Board.BLANK;
        this.isWinningSquare = false;
        value = 0;
    }


    public int getValue(){
        return value;
    }

    public void setValue(int newVal){
        value = newVal;
    }

    // Returns true if the tile is empty
    public boolean isEmpty(){
        if (value == 0){
            return true;
        }
        return false;
    }



    public void draw(Graphics g, GameViewer game){
        int x = 40;
        int y = 40;
        g.drawImage(game.getImages()[0], x, y, TILE_HEIGHT, TILE_HEIGHT, game);
        g.drawImage(game.getImages()[1], x + 200, y + 200, TILE_HEIGHT, TILE_HEIGHT, game);
        g.drawImage(game.getImages()[2], x + 400, y + 400, TILE_HEIGHT, TILE_HEIGHT, game);
        g.drawImage(game.getImages()[3], x + 600, y + 600, TILE_HEIGHT, TILE_HEIGHT, game);
    }

//    public String toString(){
//        return this.marker;
//    }


}
