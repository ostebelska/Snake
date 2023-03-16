import java.awt.*;

public class Game {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        MainFrame mainframe = new MainFrame(); //tworzenie obiektu main frame i wyswietlanie ekranu
        mainframe.setVisible(true);
        });
    }
}

