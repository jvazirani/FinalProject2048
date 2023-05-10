import javax.swing.*;
import java.awt.*;
public class Tile {
    private String marker;
    private int row;
    private int col;
    private boolean isWinningSquare;
    private final int TILE_HEIGHT = 100;
    private GameViewer game;

    private Image tileImage;
    private int value;

    private final int BOARD_WIDTH = 4;
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


    // TODO change to make the 2 a value
    public Image findImage(){
         return new ImageIcon("Resources/" + value + ".png").getImage();
    }

    // Returns true if the tile is empty
    public boolean isEmpty(){
        return value == 0;
    }

    public void setTileImage(Image image){
        this.tileImage = image;
    }

    public void draw(Graphics g, GameViewer game, int x, int y){
//        int x = 200;
//        int y = 200;
//        // Draw board of empty squares
        g.drawRect(x + (100), y + (100), TILE_HEIGHT, TILE_HEIGHT);
//
//        g.drawImage(game.getImages()[0], x, y, TILE_HEIGHT, TILE_HEIGHT, game);
//        g.drawImage(game.getImages()[1], x + 200, y + 200, TILE_HEIGHT, TILE_HEIGHT, game);
//        g.drawImage(game.getImages()[2], x + 400, y + 400, TILE_HEIGHT, TILE_HEIGHT, game);
//        g.drawImage(game.getImages()[3], x + 600, y + 600, TILE_HEIGHT, TILE_HEIGHT, game);
        g.drawImage(this.findImage(), x + 100, y + 100, TILE_HEIGHT, TILE_HEIGHT, game);
    }

//    public String toString(){
//        return this.marker;
//    }


}
