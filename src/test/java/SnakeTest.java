import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;


public class SnakeTest {

    Snake snake = new Snake();
    Apple apple = new Apple();

    @Test
    public void testGetDirection() {

        snake.getDirection();

        Assertions.assertNotNull(snake.direction);

    }

    @Test
    public void testSetDirection() {

        snake.setDirection(Direction.RIGHT);
        Assertions.assertNotNull(snake.direction);
    }

    @Test
    public void testIsCollisionWithTail() {

        snake.body.add(new Point(2, 5));

        Assertions.assertTrue(snake.isCollision());

    }

    @Test
    public void testIsCollisionWithBoard() {

        Board board = new Board();

        snake.body.get(0).x=Board.B_WIDTH;
        snake.body.get(0).y=Board.B_HEIGHT;

        Assertions.assertTrue(snake.isCollision());

    }

    @Test
    public void testSnakeGrows() {          //bez score w mainframe

        apple.x = snake.body.get(0).x;
        apple.y = snake.body.get(0).y;

        snake.eatApple(apple);

        Assertions.assertEquals(4, snake.getSize());

    }

    @Test
    public void testEatApple() {        //w main frame bez score

        Snake snake = new Snake();
        Apple apple = new Apple();

        apple.x = snake.body.get(0).x;
        apple.y = snake.body.get(0).y;

       Assertions.assertTrue(snake.eatApple(apple));
    }
}