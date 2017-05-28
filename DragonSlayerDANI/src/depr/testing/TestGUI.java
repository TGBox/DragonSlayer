package depr.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 01.05.2017, 02:19.
 */
public class TestGUI extends JFrame implements ActionListener{

    public JTextField textField;
    public JTextArea textArea;
    public JButton button1, button2;

    private TestGUI(){
        super("TestGUI");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        textField = new JTextField(35);
        textField.addActionListener(this);
        textField.setActionCommand("in");
        textArea = new JTextArea(5, 35);
        button1 = makeButton("button1");
        button2 = makeButton("button2");
        add(new JScrollPane(textArea), "Center");
        add(textField, "North");
        add(button1, "West");
        add(button2, "East");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * method to create a new button that implements the action listener and uses it's caption as it's action command.
     * the action commands need to be caught in the action performed method with corresponding behaviours.
     * @param caption String that will be used as the action command and for the actual caption of the button.
     * @return JButton that was created as planned.
     */
    private JButton makeButton(String caption) {
        JButton button = new JButton(caption);
        button.setActionCommand(caption);
        button.addActionListener(this);
        return button;
    }

    /**
     * method that overrides the actionPerformed method of the ActionListener class.
     * the action commands will be the same as the captions used to create the buttons
     * @param e ActionEvent that lead to the execution of this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "button1":
                textArea.append("\n" + button1.getText());
                break;
            case "button2":
                textArea.append("\n" + button2.getText());
                break;
            case "in":
                textArea.append("\n" + textField.getText());
                textField.setText("");
                break;
            default:
                break;
        }
    }

    /**
     * main method to get everything going. starts the gui within the designated thread.
     * @param args .
     */
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                UnsupportedLookAndFeelException | IllegalAccessException e){
            e.printStackTrace();
        }
        JComponent.setDefaultLocale(Locale.ENGLISH);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new TestGUI();
            }
        });
    }

}
