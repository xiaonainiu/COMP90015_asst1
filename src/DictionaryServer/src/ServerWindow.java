import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by ES on 2017/9/5.
 */
public class ServerWindow {
    public JTextArea textArea1;
    private JButton close;
    private JPanel mainPanel;
    private JScrollPane scroll;

    public ServerWindow() {
        JFrame frame = new JFrame("ServerWindow");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Server.close();
            }
        });
    }
}
