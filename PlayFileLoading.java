//************************************************************************
// File: PlayFileLoading.java         CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal		     	  Email: hanah.leventhal@yale.edu
//
// Class: PlayFileLoading
// Dependencies: GuitarHeroAutoAltered
//
//   --------------------
//   
//      This program sets up the record button loading page GUI. There is a
//    frame containing the menu, title, extra text, and progress bar. The
//    progress bar progresses incrementally via a timer and calls the 
//    GuitarHeroAutoAltered playFile method when it reaches 100.
//
//************************************************************************

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class PlayFileLoading {

  private static final Color YaleBlue = new Color(0, 53, 107);
  public static final String frameIconImage = "songgeneratoricon.PNG";
  private static int count = 0;

  public static JFrame loadingScreenGUI(JFrame frame, JTextField fileNameInput, String musicFileName) {
    // Clear frame
    frame.getContentPane().removeAll();
    frame.setBackground(YaleBlue);
    frame.setLayout(new BorderLayout());

    //Set the frame icon to an image loaded from a file.
    frame.setIconImage(new ImageIcon(frameIconImage).getImage());

    // Add MenuBar to frame
    JMenuBar menuBar = Menu.menuBarGUI(frame);
    frame.add(menuBar, BorderLayout.NORTH);

    // Create contentPanel
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new GridLayout(2,1));
    contentPanel.setBackground(YaleBlue);
    frame.add(contentPanel, BorderLayout.CENTER);

    // Create titlePanel
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new GridLayout(2,1));
    titlePanel.setBackground(YaleBlue);
    contentPanel.add(titlePanel);

    // Page title
    JLabel title = new JLabel("<html><ceneter>Preparing Song...</center></html>", SwingConstants.CENTER){
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
    // Create font size
    Font font3 = new Font("SansSerif", Font.BOLD, 100);
    title.setFont(font3);
    title.setForeground(Color.WHITE);
    JLabel extraText = new JLabel("<html><ceneter>Please wait.</center></html>", SwingConstants.CENTER){
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
    // Create font size
    Font font4 = new Font("SansSerif", Font.BOLD, 50);
    extraText.setFont(font4);
    extraText.setForeground(Color.WHITE);
    titlePanel.add(title);
    titlePanel.add(extraText);

    // Create loadingBarPanel
    JPanel loadingBarPanel = new JPanel();
    loadingBarPanel.setLayout(new GridLayout(3,3));
    loadingBarPanel.setBackground(YaleBlue);
    contentPanel.add(loadingBarPanel);

    // Create progress bar
    JProgressBar progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
      
    // Timed increase of progress bar
    int delay = 250; // delay for 1 sec.
    int period = 175; // repeat every sec.
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run(){
                if (progressBar.getValue() == 100) {
                    progressBar.setValue(100);

                    JFrame frame2;
                    frame2 = GuitarHeroAutoAltered.playFile(frame, musicFileName);
                    // set the text of field to blank
                    fileNameInput.setText("FileName.txt");
                    GuitarHeroAutoAltered.playFileGUI(frame2);
                    count = 0;
                    progressBar.setValue(0);
                    timer.cancel();
                }
                else {
                    progressBar.setValue(count * 5);
                    count++;
                }
            }
        }, delay, period);

    // Add to loadingBarPanel
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(progressBar);
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));
    loadingBarPanel.add(new JLabel(""));

    // Repaint new content
        frame.revalidate();
        frame.repaint();

    return frame;
	}
}
