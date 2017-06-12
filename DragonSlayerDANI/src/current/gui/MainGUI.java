package current.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 07.06.2017, 21:57.
 */
public class MainGUI extends JFrame implements ActionListener {

  private static final String IMAGE_PATH = "src/current/res/img/";

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 500;
  private static final int GAP = 10;

  // TODO create another use item button for the fight panel with specialized bag handling in fight

  private JPanel textPanel, rightPanel, bottomPanel, walkPanel, fightPanel, bagPanel, metaPanel;
  private JPanel walkTopPanel, walkBottomPanel;
  private JTextArea outputArea;
  private JScrollPane outPane;
  private JButton upButton, downButton, leftButton, rightButton, findButton, fightButton,
      runButton, openBagButton, useItemButton, deleteItemButton, mapButton, statusButton,
      helpButton, manualButton;
  private BufferedImage upImg, downImg, leftImg, rightImg, findImg, fightImg, runImg, openBagImg,
      useItemImg, deleteItemImg, mapImg, statusImg, helpImg, manualImg;
  private boolean imagesAvailable;

  /**
   * method to create a new main gui object.
   */
  public MainGUI() {
    super("Dragon Slayer");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(GAP, GAP));
    setResizable(true);

    this.imagesAvailable = loadImages();
    initComponents();
    addComponentsTogether();
    addActionListeners();

    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * method to load all necessary images for the gui.
   *
   * @return boolean true if all images were loaded correctly, false on IOException.
   */
  private boolean loadImages() {
    try {
      upImg = ImageIO.read(new File(IMAGE_PATH + "up.png"));
      downImg = ImageIO.read(new File(IMAGE_PATH + "down.png"));
      leftImg = ImageIO.read(new File(IMAGE_PATH + "left.png"));
      rightImg = ImageIO.read(new File(IMAGE_PATH + "right.png"));
      fightImg = ImageIO.read(new File(IMAGE_PATH + "fight.png"));
      findImg = ImageIO.read(new File(IMAGE_PATH + "find.png"));
      runImg = ImageIO.read(new File(IMAGE_PATH + "run.png"));
      openBagImg = ImageIO.read(new File(IMAGE_PATH + "bag.png"));
      useItemImg = ImageIO.read(new File(IMAGE_PATH + "use.png"));
      deleteItemImg = ImageIO.read(new File(IMAGE_PATH + "delete.png"));
      mapImg = ImageIO.read(new File(IMAGE_PATH + "map.png"));
      statusImg = ImageIO.read(new File(IMAGE_PATH + "status.png"));
      helpImg = ImageIO.read(new File(IMAGE_PATH + "help.png"));
      manualImg = ImageIO.read(new File(IMAGE_PATH + "manual.png"));
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * method to initialize all the components that are defined in the scope.
   */
  private void initComponents() {
    textPanel = new JPanel(new BorderLayout(GAP, GAP));
    rightPanel = new JPanel(new BorderLayout(GAP, GAP));
    bottomPanel = new JPanel(new BorderLayout(GAP, GAP));

    walkPanel = new JPanel(new GridLayout(2, 1, GAP, GAP));
    walkTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    walkBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    fightPanel = new JPanel(new GridLayout(3, 1, GAP, GAP));
    bagPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, GAP, GAP));
    metaPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, GAP, GAP));
    outputArea = new JTextArea(15, 45);
    outputArea.setLineWrap(true);
    outputArea.setWrapStyleWord(true);
    outPane = new JScrollPane(outputArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    // Image buttons.
    if (this.imagesAvailable) {
      upButton = new JButton(new ImageIcon(upImg));
      downButton = new JButton(new ImageIcon(downImg));
      leftButton = new JButton(new ImageIcon(leftImg));
      rightButton = new JButton(new ImageIcon(rightImg));
      findButton = new JButton(new ImageIcon(findImg));
      fightButton = new JButton(new ImageIcon(fightImg));
      runButton = new JButton(new ImageIcon(runImg));
      openBagButton = new JButton(new ImageIcon(openBagImg));
      useItemButton = new JButton(new ImageIcon(useItemImg));
      deleteItemButton = new JButton(new ImageIcon(deleteItemImg));
      mapButton = new JButton(new ImageIcon(mapImg));
      statusButton = new JButton(new ImageIcon(statusImg));
      helpButton = new JButton(new ImageIcon(helpImg));
      manualButton = new JButton(new ImageIcon(manualImg));
    } else {
      upButton = new JButton("North");
      downButton = new JButton("South");
      leftButton = new JButton("West");
      rightButton = new JButton("East");
      findButton = new JButton("Find");
      fightButton = new JButton("Fight");
      runButton = new JButton("Run");
      openBagButton = new JButton("Open Bag");
      useItemButton = new JButton("Use Item");
      deleteItemButton = new JButton("Delete Item");
      mapButton = new JButton("Show map");
      statusButton = new JButton("Show status");
      helpButton = new JButton("Show help");
      manualButton = new JButton("Show manual");
    }

  }

  /**
   * method to add all the components together.
   */
  private void addComponentsTogether() {
    textPanel.add(outPane, "Center");
    add(textPanel, "Center");
    add(bottomPanel, "South");
    add(rightPanel, "East");
    walkPanel.add(walkTopPanel, 0);
    walkPanel.add(walkBottomPanel, 1);
    walkTopPanel.add(upButton);
    walkBottomPanel.add(leftButton);
    walkBottomPanel.add(downButton);
    walkBottomPanel.add(rightButton);
    rightPanel.add(walkPanel, "South");
    rightPanel.add(bagPanel, "Center");
    bagPanel.add(openBagButton);
    bagPanel.add(useItemButton);
    bagPanel.add(deleteItemButton);
    fightPanel.add(fightButton);
    fightPanel.add(runButton);
    fightPanel.add(useItemButton);
    metaPanel.add(mapButton);
    metaPanel.add(statusButton);
    metaPanel.add(helpButton);
    metaPanel.add(manualButton);
  }

  /**
   * method to add all necessary action listeners.
   */
  private void addActionListeners() {

  }


  /**
   * method that overrides the actionPerformed method of the ActionListener class.
   * the action commands will be the same as the captions used to create the buttons
   *
   * @param e ActionEvent that lead to the execution of this method.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO implement actions
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
        new MainGUI();
      }
    });
  }
}
