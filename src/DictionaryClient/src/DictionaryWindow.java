import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by es on 2017/9/4.
 */
public class DictionaryWindow {

    private JTabbedPane container;

    public DictionaryWindow() {

        JFrame frame = new JFrame("DictionaryWindow");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        query_bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String command;
                String word = inputWord.getText();
                command = "search,"+word;
                try{
                    if (Client.checkCommand(command)){
                        Client.dos.writeUTF(command);
                    }else {
                        outputWord.setText("Invalid input");
//                    gui.outputWord.append(str);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        add_bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String command;
                String word = inputWord.getText();
                String explain = outputWord.getText();
                command = "add,"+word+","+explain;

                try{
                    if (Client.checkCommand(command)){
                        Client.dos.writeUTF(command);
                    }else {
                        outputWord.setText("Invalid input");
//                    gui.outputWord.append(str);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        delete_bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String command;
                String word = inputWord.getText();
                command = "delete,"+word;
                try{
                    if (Client.checkCommand(command)){
                        Client.dos.writeUTF(command);
                    }else {
                        outputWord.setText("Invalid input");
//                    gui.outputWord.append(str);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

//    public void start() {
//        JFrame frame = new JFrame("DictionaryWindow");
//        frame.setContentPane(this.mainPanel);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
    private JTextField inputWord;
    public JTextArea outputWord;
    private JButton query_bt;
    private JButton add_bt;
    private JButton delete_bt;
    private JScrollPane scroll;
    private JPanel mainPanel;
}
