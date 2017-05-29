package usageExample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 21:54.
 */
public class GUI extends JFrame{

  private JTextArea textArea;
  private JTextField textField;

  public GUI(){
    super("Testing GUI");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(10, 10));
    setResizable(false);

    textArea = new JTextArea(10, 45);
    textArea.setWrapStyleWord(true);
    textArea.setLineWrap(true);
    textArea.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GREEN));
    textField = new JTextField(45);
    textField.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.GREEN));

    add(textArea, "Center");
    add(textField, "South");

    setSize(600, 400);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public JTextField getTextField() {
    return textField;
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public void append(String text){
    if(this.textArea.getText().equals("")){
      this.textArea.setText(text);
    } else {
      this.textArea.setText(this.textArea.getText() + "\n" + text);
    }
  }

  /**
   * main method to get everything going. starts the gui within the designated thread.
   *
   * @param args .
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException |
        UnsupportedLookAndFeelException | IllegalAccessException e) {
      e.printStackTrace();
    }
    JComponent.setDefaultLocale(Locale.ENGLISH);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new GUI();
      }
    });
  }

}
