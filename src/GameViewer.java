import javax.swing.*;
import java.awt.*;
public class GameViewer extends JFrame {
    private Board board;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    private Image[] numbers;
    public GameViewer(Board board){
        this.board = board;
        numbers = new Image[11];
        numbers[0] = new ImageIcon("Resources/2.png").getImage();
        numbers[1] = new ImageIcon("Resources/4.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Image[] getImages(){
        return numbers;
    }

    public void Paint(Graphics g){
        board[0][0].draw(g, this);
    }
}
