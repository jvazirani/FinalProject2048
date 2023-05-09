import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameViewer extends JFrame  implements KeyListener {
    private Game game;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public GameViewer(Game game) {
        this.game = game;
//        for(int i = 0; i < numbers.length; i++){
//            numbers[i] = new ImageIcon("Resources/" + Math.pow(2, i + 1) + ".png").getImage();
//        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("2048");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.addKeyListener(this);
    }


    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        int x = 0;
        int y = 0;
        Tile[][] board = game.getBoard();
        g.setColor(Color.black);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].draw(g, this, x + (j * 100), y + (i * 100));
            }
        }
        g.setFont(new Font("Serif", Font.BOLD, 100));
        if(game.checkLose()){
            g.setColor(Color.red);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            g.drawString("YOU LOSE", 100, 400);
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

        if (keyCode == KeyEvent.VK_LEFT) {
            game.shiftLeft();
            game.newTile();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            game.shiftRight();
            game.newTile();
        } else if (keyCode == KeyEvent.VK_UP) {
            game.shiftUp();
            game.newTile();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            game.shiftDown();
            game.newTile();
        }
        repaint();

    }
}
