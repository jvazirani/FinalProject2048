import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameViewer extends JFrame  implements KeyListener {
    private Game game;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private Image boardImage;
    private Image background;

    private final int TILE_HEIGHT = 100;

    public GameViewer(Game game) {
        this.game = game;
        boardImage = new ImageIcon("Resources/board.png").getImage();
        background = new ImageIcon("Resources/background.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.addKeyListener(this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawImage(background, 0 ,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(boardImage, WINDOW_WIDTH / 4, WINDOW_HEIGHT / 4, WINDOW_WIDTH / 2,
                WINDOW_HEIGHT / 2, this);
        Tile[][] board = game.getBoard();
        g.setColor(Color.black);
        int x = 0;
        int y = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].draw(g, this, x + (j * TILE_HEIGHT), y + (i * TILE_HEIGHT));
            }
        }
        g.setFont(new Font("instructions", Font.PLAIN,  25));
        g.drawString("How to play: Combine alike numbers, to try to make higher", 20, 600);
        g.drawString("numbers. You can do this by using the arrow keys to shift ", 20, 630);
        g.drawString("the board up, right, left, or down. The goal of the game", 20, 660);
        g.drawString("is to get a 2048 tile. Click the space bar for a new game.", 20, 690);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        g.drawString("SCORE: ", 50, 100);
        g.drawString(String.valueOf(game.getScore()), 450, 100);
        g.setFont(new Font("best", Font.BOLD, 50));
        g.setColor(Color.darkGray);
        game.updateBestScore();
        g.drawString("Best Score: ", 50, 150);
        g.drawString(String.valueOf(game.getBestScore()), 350, 150);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        if(game.checkLose()){
            g.setColor(Color.red);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            g.drawString("YOU LOSE", 100, 400);
            g.setFont(new Font("new game", Font.PLAIN, 30));
            g.drawString("Click the space bar for a new game", 50, 500);
        }
        if(game.checkWin()){
            g.setColor(Color.red);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            g.drawString("YOU WIN", 100, 400);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e)                // #5 Required for KeyListener
    {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyReleased(KeyEvent e)             // #6 Required for KeyListener
    {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    // TODO issue: when input a not valid input, stops options
    @Override
    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int keyCode = e.getKeyCode();
        if(!game.checkLose()){
            if (keyCode == KeyEvent.VK_LEFT) {
                game.shiftLeft();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                game.shiftRight();
            } else if (keyCode == KeyEvent.VK_UP) {
                game.shiftUp();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                game.shiftDown();
            }
            System.out.println(keyCode);
        }
        if(keyCode == KeyEvent.VK_SPACE){
            game.reset();
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
