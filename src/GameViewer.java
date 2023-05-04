import javax.swing.*;
import java.awt.*;
public class GameViewer extends JFrame {
    private Game game;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    private Image[] numbers;
    public GameViewer(Game game){
        this.game = game;
        numbers = new Image[11];
//        for(int i = 0; i < numbers.length; i++){
//            numbers[i] = new ImageIcon("Resources/" + Math.pow(2, i + 1) + ".png").getImage();
//        }
        numbers[0] = new ImageIcon("Resources/2.png").getImage();
        numbers[1] = new ImageIcon("Resources/4.png").getImage();
        numbers[2] = new ImageIcon("Resources/8.png").getImage();
        numbers[3] = new ImageIcon("Resources/16.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Image[] getImages(){
        return numbers;
    }

    public void paint(Graphics g){
        Tile[][] board = game.getBoard();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j].draw(g, this);
            }
        }
    }
}
