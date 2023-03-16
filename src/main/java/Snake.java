import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {

    private Image tail;
    private Image head;

    public Direction direction;        //okresla kierunek weza, obiekt klasy enum
    public Point lastPoint;               //zapisanie ostatniej wspolrzednej w ktorej byl waz
    public List<Point> body;           //lista punktów tworzących ciało snake

    public Snake() {

        lastPoint = new Point();
        direction = Direction.DOWN;        //waz zmierza w dol
        body = new ArrayList<>();

        body.add(new Point(2, 5));  //współrzedne głowy snake, skierowana w dol, os y skierowana w dol, y> -> wyswietla sie nizej
        body.add(new Point(2, 4));
        body.add(new Point(2, 3));

    }

    public void draw(Graphics g){
        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
        ImageIcon iit = new ImageIcon("src/resources/dot2.png");
        tail = iit.getImage();

        for (Point point : getTail()) {
            g.drawImage(tail, point.x* Board.SIZE, point.y* Board.SIZE, null);
        }

            g.drawImage(head, getHead().x* Board.SIZE, getHead().y* Board.SIZE, null);
    }

    public Point getHead() {           //zamisat body get bedziemy uzywac get head
        return body.get(0);
    }

    public List<Point> getTail() {
        return body.subList(1, body.size());    //od indeksu 1 do body size, wszystkie elementy oprocz glowy
    }

    public void move() {
        lastPoint.setLocation(body.get(body.size()-1));     //ostatni element ma współrzedne przedostatniego,
                                                            //setlocation ustawia wspolrzedne punktu

        for (int i = body.size()-1; i>0; i--) {         //i ustawione na ostatni element ogonu, i>0 bo głowa osobno
            body.get(i).setLocation(body.get(i-1));     //pobieramy element i, ustawiamy jego wspolrzedne na te wczesniejszego.
        }

        switch (direction) {                    //w jakim kierunku wedruje waz
            case DOWN -> getHead().y++;            //jak w dol to y wiekszy
            case UP -> getHead().y--;            //jak w gore to y mniejszy
            case RIGHT -> getHead().x++;
            case LEFT -> getHead().x--;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isCollision() {
        Point head = getHead();         //kolizje głowy, jest ona cały czas w nowym miejscu

        for (Point point : getTail()) {         //przeiterujemy przez cały ogon
            if (head.equals(point)) {           //jezeli glowa jest rowna z jednym z elementow ogona to kolizja jest prawdziwa
                return true;
            }
        }
        if (head.x < 0 || head.x >= Board.B_WIDTH || head.y < 0 || head.y >= Board.B_HEIGHT){ //uderzenie w krawedz planszy
            return true;
        }

        return false;
    }

    public int getSize() {
        return body.size();
    }

    public boolean eatApple(Apple apple) {

        if (getHead().equals(apple)) {                      //jezeli glowa jest rowna jablku = jedzenie
            body.add(new Point(lastPoint));                //nowy punkt do ogona, o wspolrzednych ostatniego punktu w ktorym byl waz
            MainFrame.score.setText("SCORE: " + (body.size()-3));

            return true;
        }

        return false;
    }
}

