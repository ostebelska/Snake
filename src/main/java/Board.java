import java.awt.*;

public class Board {

    private final int DELAY = 100;
    public static final int B_WIDTH = 20;       //pola po których może się ruszać wąż
    public static final int B_HEIGHT = 20;
    public static final int SIZE = 15;          //wielkość jednego pola
    public static final int MAX_X = B_WIDTH *SIZE;   //wysokość i szerokość panelu
    public static final int MAX_Y = B_HEIGHT *SIZE;

    public static void Draw(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0,0,MAX_X,MAX_Y);
    }

}
