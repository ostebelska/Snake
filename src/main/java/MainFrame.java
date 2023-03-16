import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static JLabel score;             //nowa etykieta

    public MainFrame() throws HeadlessException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SNAKE GAME");

        score = new JLabel("SCORE: ", SwingConstants.CENTER);
        score.setFont(new Font(score.getFont().getName(), Font.BOLD, 15));

        add(new MainPanel());
        add(score, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);            //okno na środku
        setResizable(false);                    //nie moze uzytkownik zmienic rozmiaru
    }

}
