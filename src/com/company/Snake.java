package com.company;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        createWindow();
    }

    private void createWindow() {

        add(new Board());

        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setTitle("SNAKE GAME");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void move() {
        // przesuń wszystkie segmenty poza głową
        for (int i = snakeSegments.size() - 1; i > 0; i--) {
            snakeSegments.get(i).set(snakeSegments.get(i - 1));
        }
        // przesuń głowę
        int segmentWidth = texture.getWidth();
        int segmentHeight = texture.getWidth();
        // pozycje X, Y ostatniego segmentu
        // przed górną i prawą krawędzią okna
        int lastWindowSegmentX = Gdx.graphics.getWidth() - segmentWidth;
        int lastWindowSegmentY = Gdx.graphics.getHeight() - segmentHeight;
        GridPoint2 head = snakeSegments.get(0);
        switch (direction) {
            case LEFT:
                head.x = (head.x == 0) ? lastWindowSegmentX : head.x - segmentWidth;
                break;
            case UP:
                head.y = (head.y == lastWindowSegmentY) ? 0 : head.y + segmentHeight;
                break;
            case RIGHT:
                head.x = (head.x == lastWindowSegmentX) ? 0 : head.x + segmentWidth;
                break;
            case DOWN:
                head.y = (head.y == 0) ? lastWindowSegmentY : head.y - segmentHeight;
                break;
        }
    }
}