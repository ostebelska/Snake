import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;

public class MainPanel extends JPanel {

    private Apple apple = new Apple();
    private boolean gameOver = false;
    private Snake snake = new Snake();
    public boolean inGame = true;

    String name;
    JTextField userText = new JTextField(20);
    JSONObject JsonObj = new JSONObject();

    public MainPanel(){

        setPreferredSize(new Dimension(Board.MAX_X, Board.MAX_Y));
        MainTimer timer = new MainTimer();
        timer.start();

        MainFrame.score.setText("SCORE: " +(snake.getSize()-3));

        setFocusable(true);
        addKeyListener((KeyListener) new MainKeyAdapter());
    }

    @Override
    public void paintComponent(Graphics g) {

        if (inGame) {
            Board.Draw(g);
            snake.draw(g);
            apple.draw(g);
        }
        else
            gameOver(g);

    }
    private void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont( new Font("Times New Roman",Font.BOLD, 45));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (Board.MAX_X - metrics2.stringWidth("GAME OVER"))/2, Board.MAX_Y /2);

    }

    public void saveToJSON() {

        name = userText.getText();
        JsonObj.put("SAVED SCORE: ", snake.getSize()-3);


        try {
            FileWriter file = new FileWriter("output.json", true);
            file.write(JsonObj.toString());
            file.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        System.out.println("JSON file created: " + JsonObj);
    }


    public class MainTimer extends Timer {

        public static final int DELAY = 130;        //okreslenie zegara, ruch weza, co jaki czas ma sie poruszac, w sekunde az 10 razy zmienia pozycje

        public MainTimer() {
            super(DELAY, e -> {                     //co sie dzieje podczas uruchomienia timera
                if (!gameOver) {
                    snake.move();
                    inGame=true;

                    if (snake.eatApple(apple)) {
                        apple = new Apple();
                        inGame=true;                    //po zjedzeniu, generowanie nowego jablka
                    }
                    if (snake.isCollision()) {
                        gameOver=true;
                        inGame=false;
                        MainFrame.score.setText("FINAL SCORE: " + (snake.getSize()-3));
                        saveToJSON();
                    }
                    repaint();
                }
            });
        }
    }

    private class MainKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {                                   //moze zmieniac kierunek o 90 stopni tylko, a nie o 180
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {          //jak jest rozny od prawego to moze isc w lewo (zasada 90 stopni)
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }

            }
        }
    }
}


