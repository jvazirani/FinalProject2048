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

    private final int INSTRUCTIONS_STARTX = 20;

    private final int LINE_ONE = 56;
    private final int LINE_TWO = 114;
    private final int LINE_THREE = 171;
    private final int LINE_FOUR = 229;
    private final int INSTRUCTIONS_STARTY = 630;

    private final int INSTRUCTIONS_ADDY = 30;

    private final int SCORE_HEIGHT = 100;

    private final int FONT_HEIGHT = 100;

    private final int REDX = 100;

    private final int REDY = 400;

    private final int SCOREX = 50;
    private final int SCOREY = 150;

    private final int SCORE_NUMX = 450;

    private final int BSCORE_NUMX = 350;

    private final int NEW_Y = 500;



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
        // Draw the background image
        g.drawImage(background, 0 ,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Draw the board
        g.drawImage(boardImage, WINDOW_WIDTH / 4, WINDOW_HEIGHT / 4, WINDOW_WIDTH / 2,
                WINDOW_HEIGHT / 2, this);

        // Draw the squares and tiles
        Tile[][] board = game.getBoard();
        g.setColor(Color.black);
        int x = 0;
        int y = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].draw(g, this, x + (j * TILE_HEIGHT), y + (i * TILE_HEIGHT));
            }
        }

        // Write the instructions
        // Using substring to improve the following competency:
        // Can use and apply SLICE String methods.
        g.setFont(new Font("instructions", Font.PLAIN,  25));
        g.drawString(game.getInstructions().substring(0, LINE_ONE), INSTRUCTIONS_STARTX, INSTRUCTIONS_STARTY);
        g.drawString(game.getInstructions().substring(LINE_ONE + 1, LINE_TWO), INSTRUCTIONS_STARTX,
                INSTRUCTIONS_STARTY + INSTRUCTIONS_ADDY);
        g.drawString(game.getInstructions().substring(LINE_TWO + 1, LINE_THREE), INSTRUCTIONS_STARTX,
                INSTRUCTIONS_STARTY + (2 * INSTRUCTIONS_ADDY));
        g.drawString(game.getInstructions().substring(LINE_THREE, LINE_FOUR), INSTRUCTIONS_STARTX,
                INSTRUCTIONS_STARTY + (3 * INSTRUCTIONS_ADDY));

        // Update the score
        g.setFont(new Font("Serif", Font.BOLD, FONT_HEIGHT));
        g.drawString("SCORE: ", SCOREX, SCORE_HEIGHT);
        g.drawString(String.valueOf(game.getScore()), SCORE_NUMX, SCORE_HEIGHT);

        // Update the best score
        g.setFont(new Font("best", Font.BOLD, FONT_HEIGHT / 2));
        g.setColor(Color.darkGray);
        game.updateBestScore();
        g.drawString("Best Score: ", SCOREX, SCOREY);
        g.drawString(String.valueOf(game.getBestScore()), BSCORE_NUMX, SCOREY);
        g.setFont(new Font("Serif", Font.BOLD, FONT_HEIGHT));

        // Lose and win screens
        if(game.checkLose()){
            g.setColor(Color.red);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            g.drawString("YOU LOSE", REDX, REDY);
            g.setFont(new Font("new game", Font.PLAIN, INSTRUCTIONS_ADDY));
            g.drawString("Click the space bar for a new game", SCOREX, NEW_Y);
        }
        if(game.checkWin()){
            g.setColor(Color.red);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            g.drawString("YOU WIN", REDX, REDY);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }
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
        }
        if(keyCode == KeyEvent.VK_SPACE){
            game.reset();
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
