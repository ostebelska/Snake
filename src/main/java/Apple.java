import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Apple extends Point {

    public Image apple;

    private static Random random = new Random();            //statyczne, nie potrzeba obiektu

    public Apple() {

        super(random.nextInt(Board.B_WIDTH), random.nextInt(Board.B_HEIGHT));      //losujemy jablka, dla x max = ilosc pol po x, to samo dla y
    }

    public void draw(Graphics g) {

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();
        g.drawImage(apple, x*Board.SIZE, y*Board.SIZE, null);

    }
}