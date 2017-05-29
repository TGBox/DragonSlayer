package usageExample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 29.05.2017, 22:03.
 */
public class TestController implements ActionListener {

  private GUI gui;
  private boolean inFight = false;
  private boolean gameWon = false;

  public TestController() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException |
        UnsupportedLookAndFeelException | IllegalAccessException e) {
      e.printStackTrace();
    }
    JComponent.setDefaultLocale(Locale.ENGLISH);
    SwingUtilities.invokeLater(this::startTheGame);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    gui.append(getResponse(gui.getTextField().getText()));
    gui.getTextField().setText("");
    gameLoop();
  }


  public void startTheGame() {
    gui = new GUI();
    gui.getTextField().addActionListener(this);
    gui.getTextField().setActionCommand("enter");
    gameLoop();
  }

  private void gameLoop() {
    if (!gameWon) {
      gui.append(getPrompt());
    }
  }

  public String getPrompt() {
    if (inFight && !gameWon) {
      return "You are in a fight, choose:\n1: attack\n2: run";
    } else if (!inFight && !gameWon) {
      return "You are standing. where do you want to walk?";
    } else {
      return "You won the game";
    }
  }

  public String getResponse(String input) {
    if (!inFight) {
      switch (input) {
        case "up":
          inFight = true;
          return "You walk upwards";
        case "down":
          return "You walk downwards";
        default:
          return "unknown";
      }
    } else {
      switch (input) {
        case "1":
          inFight = false;
          return "You deal damage";
        case "2":
          return "You run away";
        default:
          return "unknown";
      }
    }
  }
}
