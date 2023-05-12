import javax.swing.*;
import java.awt.*;
public class Tile {
    private final int TILE_HEIGHT = 100;
    private Image tileImage;
    private int value;

    public Tile(){
        value = 0;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int newVal){
        value = newVal;
    }

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
        // Draw board of empty squares
        g.drawRect(x + (200), y + (200), TILE_HEIGHT, TILE_HEIGHT);
        // Draw the image for the tile
        g.drawImage(this.findImage(), x + 200, y + 200, TILE_HEIGHT, TILE_HEIGHT, game);
    }
}
