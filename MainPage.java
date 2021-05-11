//************************************************************************
// File: MainPage.java           CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal			 Email: hanah.leventhal@yale.edu
//
// Class: MainPage
// Dependencies: AutoSongGenerator, ComposerMode
//
//   --------------------
//   
//      This program sets up the main page GUI for MusAIc. There is a frame
//    containing the title, extra text, and the two sub-page buttons and
//    their labels. The buttons have action listeners to create hover/click
//    effects, as well as toolTips. When each button is clicked they call on 
//    the AutoSongGenerator and ComposerMode objects to launch those 
//    respective pages.
//
//************************************************************************
// Sourced help on GUIs from Oracle -> https://docs.oracle.com/javase/tutorial/uiswing/components/index.html

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage {
    private static final Color YaleBlue = new Color(0, 53, 107);
    private static final Color YaleBlue50 = new Color(0, 53, 107, 50);
    private static final int borderThickness = 10;
    private static final int FRAME_WIDTH = 1440;
    private static final int FRAME_HEIGHT = 810;
    public static final String frameIconImage = "music@3x.PNG";
    public static void main(String[] args) {
      // Creating the Frame
      JFrame frame = new JFrame();
      frame.setForeground(YaleBlue);
      frame.setBackground(YaleBlue);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
      frame.setMinimumSize(new Dimension(2750, 1500));
      // frame.setMinimumSize(new Dimension(1500, 1500));
      frame.setTitle("MusAIc");
      frame.setVisible(true);

      frame = mainPageGUI(frame);
    }

    public static JFrame mainPageGUI (JFrame frame){
      // Clear frame
      frame.getContentPane().removeAll();
      frame.setBackground(YaleBlue);

      // Setting frame layout
      frame.setLayout(new GridLayout(2,1));

      //Set the frame icon to an image loaded from a file.
      frame.setIconImage(new ImageIcon(frameIconImage).getImage());

      // Create new Panel for Text
      JPanel textPanel = new JPanel();
      textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
      textPanel.setBackground(YaleBlue);
      JLabel title = new JLabel("<html><ceneter>MusAIc</center></html>", SwingConstants.CENTER){
        public Dimension getPreferredSize() {
          return new Dimension(500, 125);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(500, 125);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(1920, 200);
        }
      };
      JLabel extraText = new JLabel("<html><center>Music Studio Application<br>Created by: Hanah Leventhal</center></html>", SwingConstants.CENTER){
        public Dimension getPreferredSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(1920, 75);
        }
      };
      textPanel.add(title);
      textPanel.add(extraText);
      frame.add(textPanel);

      // Create font size
      Font BrushScriptMT = new Font("Brush Script MT", Font.ITALIC, 150);
      title.setFont(BrushScriptMT);
      title.setForeground(Color.WHITE);
      // Create new font size
      Font font2 = new Font("SansSerif", Font.BOLD, 25);
      extraText.setFont(font2);
      extraText.setForeground(Color.WHITE);
      // Create new font size
      Font font3 = new Font("SansSerif", Font.BOLD, 50);

      // Create middle panel for button panels
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.setLayout(new GridLayout(1,2));
      buttonsPanel.setBackground(YaleBlue);

      // Create compModeButton Panel
      JPanel compModeButtonPanel = new JPanel();
      compModeButtonPanel.setLayout(new GridLayout(2,1));
      compModeButtonPanel.setBackground(YaleBlue);

      // Create and add content to compModeButton Panel
      JButton compModeBtn = new JButton("Enter"){
        public Dimension getPreferredSize() {
          return new Dimension(100, 50);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(75, 50);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(100, 50);
        }
      };
      compModeBtn.setFont(font3);
      compModeBtn.setBackground(Color.WHITE);
      compModeBtn.setForeground(YaleBlue);
      compModeBtn.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
      compModeBtn.setToolTipText("<html>Click to enter Composer Mode.<br>Make your own music and have fun!</html>");
      compModeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          ComposerMode.compModeGUI(frame);
        }
      });
      compModeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
          compModeBtn.setBackground(YaleBlue);
          compModeBtn.setForeground(Color.WHITE);
          compModeBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
          compModeBtn.setBackground(Color.WHITE);
          compModeBtn.setForeground(YaleBlue);
          compModeBtn.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
        }
        @Override
        public void mouseReleased(MouseEvent e) {
          compModeBtn.setBackground(Color.WHITE);
          compModeBtn.setForeground(YaleBlue);
        }

        @Override
        public void mousePressed(MouseEvent e) {
          compModeBtn.setBackground(YaleBlue50);
          compModeBtn.setForeground(Color.WHITE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
          compModeBtn.setBackground(YaleBlue50);
          compModeBtn.setForeground(Color.WHITE);
        }
      });
      compModeButtonPanel.add(compModeBtn);
      // Button Label
      JLabel buttonLabel1 = new JLabel("<html><center>Composer Mode:<br>Play and create your own music!</center></html>", SwingConstants.CENTER){
        public Dimension getPreferredSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(1920, 75);
        }
      };
      buttonLabel1.setFont(font2);
      buttonLabel1.setForeground(Color.WHITE);
      compModeButtonPanel.add(buttonLabel1);

      // Create genModeButton Panel
      JPanel genModeButtonPanel = new JPanel();
      genModeButtonPanel.setLayout(new GridLayout(2,1));
      genModeButtonPanel.setBackground(YaleBlue);

      // Create and add content to
      JButton genModeBtn = new JButton("Enter"){
        public Dimension getPreferredSize() {
          return new Dimension(100, 50);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(75, 50);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(100, 50);
        }
      };
      genModeBtn.setFont(font3);
      genModeBtn.setBackground(Color.WHITE);
      genModeBtn.setForeground(YaleBlue);
      genModeBtn.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
      genModeBtn.setToolTipText("<html>Click to enter Auto Song Generator Mode.<br>Select conditions and let the computer do its magic!</html>");
      genModeBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          AutoSongGenerator.autoModeGUI(frame);
        }
      });
      genModeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
          genModeBtn.setBackground(YaleBlue);
          genModeBtn.setForeground(Color.WHITE);
          genModeBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
          genModeBtn.setBackground(Color.WHITE);
          genModeBtn.setForeground(YaleBlue);
          genModeBtn.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
        }
        @Override
        public void mouseReleased(MouseEvent e) {
          genModeBtn.setBackground(Color.WHITE);
          genModeBtn.setForeground(YaleBlue);
        }

        @Override
        public void mousePressed(MouseEvent e) {
          genModeBtn.setBackground(YaleBlue50);
          genModeBtn.setForeground(Color.WHITE);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
          genModeBtn.setBackground(YaleBlue50);
          genModeBtn.setForeground(Color.WHITE);
        }
      });
      genModeButtonPanel.add(genModeBtn);
      // Button Label
      JLabel buttonLabel2 = new JLabel("<html><center>Auto Song Generator:<br>Let the computer create a song for you!</center></html>", SwingConstants.CENTER){
        public Dimension getPreferredSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMinimumSize() {
          return new Dimension(500, 75);
        }
  
        public Dimension getMaximumSize() {
          return new Dimension(1920, 75);
        }
      };
      buttonLabel2.setFont(font2);
      buttonLabel2.setForeground(Color.WHITE);
      genModeButtonPanel.add(buttonLabel2);

      // Add buttons to panel
      buttonsPanel.add(compModeButtonPanel);
      buttonsPanel.add(genModeButtonPanel);

      // Add buttonsPanel to frame
      frame.add(buttonsPanel);
      frame.setVisible(true);
      frame.requestFocus();
      return frame;
    }
}