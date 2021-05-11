//************************************************************************
// File: ComposerMode.java           CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal			     Email: hanah.leventhal@yale.edu
//
// Class: ComposerMode
// Dependencies: Menu, GuitarHeroAltered, GuitarHeroAutoAltered, PianoKey,
//               BlackKey
//
//   --------------------
//   
//      This program sets up the ComposerMode GUI. There is a frame
//    containing the title, two buttons and their labels, and a series of
//    buttons which make up a piano. The Piano buttons have action listeners
//    which change their colors on click, and simulate the corresponding key
//    press when clicked. The two buttons with labels are for the record
//    function and the playFile function. the button for recording calls the
//    GuitarHeroAltered getFileName method, and the playFile button calls
//    the GuitarHeroAutoAltered getFileName method.
//
//    Note: A commented T-O-D-O has been added before the start of the
//          major sections of code for ease of navigation. In VSCode, this
//          this should appear as a blue dot on the side navigation panel.
//
//************************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComposerMode {
    private static final Color YaleBlue = new Color(0, 53, 107);
    private static final Color YaleBlue50 = new Color(0, 53, 107, 50);
    private static final Color TRANSPARENT = new Color(0,0,0,0);
    public static final String frameIconImage = "composericon.PNG";

    public static JFrame compModeGUI(JFrame frame) {
        // Clear frame
        frame.getContentPane().removeAll();
        frame.setBackground(YaleBlue);
        frame.setLayout(new BorderLayout());

        //Set the frame icon to an image loaded from a file.
        frame.setIconImage(new ImageIcon(frameIconImage).getImage());

        // Add MenuBar to frame
        JMenuBar menuBar = Menu.menuBarGUI(frame);
        frame.add(menuBar, BorderLayout.NORTH);

        // Create Base Panel
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(2,1));
        basePanel.setBackground(YaleBlue);
        frame.add(basePanel, BorderLayout.CENTER);

        // Create Base Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.setBackground(YaleBlue);
        basePanel.add(topPanel);

        JLabel title = new JLabel("<html><ceneter>Composer Mode</center></html>", SwingConstants.CENTER){
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

        // Create font size 50
        Font BrushScriptMT = new Font("Brush Script MT", Font.ITALIC, 135);
        title.setFont(BrushScriptMT);
        title.setForeground(Color.WHITE);

        // Add title
        topPanel.add(title, Component.CENTER_ALIGNMENT);

        // Create panel to hold button panels
        JPanel buttonsArea = new JPanel();
        buttonsArea.setLayout(new GridLayout(2,1));
        buttonsArea.setBackground(YaleBlue);
        topPanel.add(buttonsArea, Component.CENTER_ALIGNMENT);

        // Create record button panel
        JPanel recBtnArea = new JPanel();
        recBtnArea.setLayout(new GridLayout(1,2));
        recBtnArea.setBackground(YaleBlue);
        buttonsArea.add(recBtnArea, Component.CENTER_ALIGNMENT);

        // Create play file button panel
        JPanel pFileBtnArea = new JPanel();
        pFileBtnArea.setLayout(new GridLayout(1,2));
        pFileBtnArea.setBackground(YaleBlue);
        buttonsArea.add(pFileBtnArea, Component.CENTER_ALIGNMENT);

        // Create button descriptions
        JLabel recText = new JLabel("Record a song: ", SwingConstants.CENTER);
        // Create font
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        recText.setFont(font1);
        recText.setForeground(Color.WHITE);
        JLabel pFileText = new JLabel("Play .txt File: ", SwingConstants.CENTER);
		    pFileText.setFont(font1);
      	pFileText.setForeground(Color.WHITE);

        // Add button descriptions
        recBtnArea.add(recText, Component.CENTER_ALIGNMENT);
        pFileBtnArea.add(pFileText, Component.CENTER_ALIGNMENT);

        // Create font
        Font font2 = new Font("SansSerif", Font.BOLD, 15);
        int borderThickness = 5;
        // Create and add content to recBtnArea Panel
        JButton recButton = new JButton("Rec"){
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
        recButton.setFont(font2);
        recButton.setBackground(Color.WHITE);
        recButton.setForeground(YaleBlue);
        recButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
        recButton.setToolTipText("<html>Click to start recording.<br>Play on the piano or your keyboard<br>and make your own song!</html>");
        recButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            GuitarHeroAltered.getFileName(frame);
          }
        });
        recButton.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseEntered(java.awt.event.MouseEvent evt) {
            recButton.setBackground(YaleBlue);
            recButton.setForeground(Color.WHITE);
            recButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
          }
          @Override
          public void mouseExited(java.awt.event.MouseEvent evt) {
            recButton.setBackground(Color.WHITE);
            recButton.setForeground(YaleBlue);
            recButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            recButton.setBackground(Color.WHITE);
            recButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            recButton.setBackground(YaleBlue50);
            recButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            recButton.setBackground(YaleBlue50);
            recButton.setForeground(Color.WHITE);
          }
        });
        recBtnArea.add(recButton, Component.CENTER_ALIGNMENT);

        // Create and add content to recBtnArea Panel
        JButton pFileButton = new JButton("Play"){
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
        pFileButton.setFont(font2);
        pFileButton.setBackground(Color.WHITE);
        pFileButton.setForeground(YaleBlue);
        pFileButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
        pFileButton.setToolTipText("<html>Click to prompt for a .txt file.<br>Input the file name<br>and play the song!</html>");
        pFileButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            GuitarHeroAutoAltered.playFileGUI(frame);
          }
        });
        pFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseEntered(java.awt.event.MouseEvent evt) {
            pFileButton.setBackground(YaleBlue);
            pFileButton.setForeground(Color.WHITE);
            pFileButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
          }
          @Override
          public void mouseExited(java.awt.event.MouseEvent evt) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
            pFileButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pFileBtnArea.add(pFileButton, Component.CENTER_ALIGNMENT);

        //TODO -> this is just a tag to help locate the start of the Piano buttons

        // Create bottomPanel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2,1));
        bottomPanel.setBackground(YaleBlue);
        bottomPanel.setBorder(BorderFactory.createLineBorder(YaleBlue, 10));
        basePanel.add(bottomPanel);

        // Create Piano black keys
        JPanel pianoBlackKeys = new JPanel();
        pianoBlackKeys.setLayout(new GridLayout(1,37));
        pianoBlackKeys.setPreferredSize(basePanel.getPreferredSize());
        pianoBlackKeys.setBackground(TRANSPARENT);
        pianoBlackKeys.setBorder(BorderFactory.createLineBorder(YaleBlue, 10));
        bottomPanel.add(pianoBlackKeys);

        // Create Piano white keys
        JPanel pianoWhiteKeys = new JPanel();
        pianoWhiteKeys.setLayout(new GridLayout(1,22));
        pianoWhiteKeys.setPreferredSize(basePanel.getPreferredSize());
        pianoWhiteKeys.setBackground(YaleBlue);
        pianoWhiteKeys.setBorder(BorderFactory.createLineBorder(YaleBlue, 10));
        bottomPanel.add(pianoWhiteKeys);

        // Create and add white keys for piano
        PianoKey q = new PianoKey();
        q.setText("<html><center><bold>q<bold><br>A<center></html>");
        q.setVerticalAlignment(SwingConstants.BOTTOM);
        q.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_Q);
              robot.keyRelease(KeyEvent.VK_Q);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        q.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(q);
        PianoKey w = new PianoKey();
        w.setText("<html><center><bold>w<bold><br>B<center></html>");
        w.setVerticalAlignment(SwingConstants.BOTTOM);
        w.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_W);
              robot.keyRelease(KeyEvent.VK_W);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        w.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(w);
        PianoKey e = new PianoKey();
        e.setText("<html><center><bold>e<bold><br>C<center></html>");
        e.setVerticalAlignment(SwingConstants.BOTTOM);
        e.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_E);
              robot.keyRelease(KeyEvent.VK_E);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        e.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(e);
        PianoKey r = new PianoKey();
        r.setText("<html><center><bold>r<bold><br>D<center></html>");
        r.setVerticalAlignment(SwingConstants.BOTTOM);
        r.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_R);
              robot.keyRelease(KeyEvent.VK_R);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        r.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(r);
        PianoKey t = new PianoKey();
        t.setText("<html><center><bold>t<bold><br>E<center></html>");
        t.setVerticalAlignment(SwingConstants.BOTTOM);
        t.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_T);
              robot.keyRelease(KeyEvent.VK_T);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        t.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(t);
        PianoKey y = new PianoKey();
        y.setText("<html><center><bold>y<bold><br>F<center></html>");
        y.setVerticalAlignment(SwingConstants.BOTTOM);
        y.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_Y);
              robot.keyRelease(KeyEvent.VK_Y);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        y.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(y);
        PianoKey u = new PianoKey();
        u.setText("<html><center><bold>u<bold><br>G<center></html>");
        u.setVerticalAlignment(SwingConstants.BOTTOM);
        u.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_U);
              robot.keyRelease(KeyEvent.VK_U);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        u.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(u);
        PianoKey i = new PianoKey();
        i.setText("<html><center><bold>i<bold><br>A<center></html>");
        i.setVerticalAlignment(SwingConstants.BOTTOM);
        i.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_I);
              robot.keyRelease(KeyEvent.VK_I);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        i.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(i);
        PianoKey o = new PianoKey();
        o.setText("<html><center><bold>o<bold><br>B<center></html>");
        o.setVerticalAlignment(SwingConstants.BOTTOM);
        o.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_O);
              robot.keyRelease(KeyEvent.VK_O);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        o.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(o);
        PianoKey p = new PianoKey();
        p.setText("<html><center><bold>p<bold><br>C<center></html>");
        p.setVerticalAlignment(SwingConstants.BOTTOM);
        p.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_P);
              robot.keyRelease(KeyEvent.VK_P);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        p.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(p);
        PianoKey leftBracket = new PianoKey();
        leftBracket.setText("<html><center><bold>[<bold><br>D<center></html>");
        leftBracket.setVerticalAlignment(SwingConstants.BOTTOM);
        leftBracket.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
              robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        leftBracket.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(leftBracket);
        PianoKey z = new PianoKey();
        z.setText("<html><center><bold>z<bold><br>E<center></html>");
        z.setVerticalAlignment(SwingConstants.BOTTOM);
        z.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_Z);
              robot.keyRelease(KeyEvent.VK_Z);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        z.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(z);
        PianoKey x = new PianoKey();
        x.setText("<html><center><bold>x<bold><br>F<center></html>");
        x.setVerticalAlignment(SwingConstants.BOTTOM);
        x.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_X);
              robot.keyRelease(KeyEvent.VK_X);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        x.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(x);
        PianoKey c = new PianoKey();
        c.setText("<html><center><bold>c<bold><br>G<center></html>");
        c.setVerticalAlignment(SwingConstants.BOTTOM);
        c.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_C);
              robot.keyRelease(KeyEvent.VK_C);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        c.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(c);
        PianoKey v = new PianoKey();
        v.setText("<html><center><bold>v<bold><br>A<center></html>");
        v.setVerticalAlignment(SwingConstants.BOTTOM);
        v.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_V);
              robot.keyRelease(KeyEvent.VK_V);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        v.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(v);
        PianoKey b = new PianoKey();
        b.setText("<html><center><bold>b<bold><br>B<center></html>");
        b.setVerticalAlignment(SwingConstants.BOTTOM);
        b.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_B);
              robot.keyRelease(KeyEvent.VK_B);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        b.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(b);
        PianoKey n = new PianoKey();
        n.setText("<html><center><bold>n<bold><br>C<center></html>");
        n.setVerticalAlignment(SwingConstants.BOTTOM);
        n.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_N);
              robot.keyRelease(KeyEvent.VK_N);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        n.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(n);
        PianoKey m = new PianoKey();
        m.setText("<html><center><bold>m<bold><br>D<center></html>");
        m.setVerticalAlignment(SwingConstants.BOTTOM);
        m.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_M);
              robot.keyRelease(KeyEvent.VK_M);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        m.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(m);
        PianoKey comma = new PianoKey();
        comma.setText("<html><center><bold>,<bold><br>E<center></html>");
        comma.setVerticalAlignment(SwingConstants.BOTTOM);
        comma.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_COMMA);
              robot.keyRelease(KeyEvent.VK_COMMA);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        comma.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(comma);
        PianoKey period = new PianoKey();
        period.setText("<html><center><bold>.<bold><br>F<center></html>");
        period.setVerticalAlignment(SwingConstants.BOTTOM);
        period.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_PERIOD);
              robot.keyRelease(KeyEvent.VK_PERIOD);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        period.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(period);
        PianoKey forwardSlash = new PianoKey();
        forwardSlash.setText("<html><center><bold>/<bold><br>G<center></html>");
        forwardSlash.setVerticalAlignment(SwingConstants.BOTTOM);
        forwardSlash.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_SLASH);
              robot.keyRelease(KeyEvent.VK_SLASH);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        forwardSlash.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(forwardSlash);
        PianoKey space = new PianoKey();
        space.setText("<html><center><bold>space<bold><br>A<center></html>");
        space.setVerticalAlignment(SwingConstants.BOTTOM);
        space.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_SPACE);
              robot.keyRelease(KeyEvent.VK_SPACE);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        space.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoWhiteKeys.add(space);

        //TODO -> this is just a tag to help locate the start of the black keys

        // Create and add Black keys for piano
        pianoBlackKeys.add(new JLabel(""));
        BlackKey two = new BlackKey();
        two.setText("<html><bold>2<bold><br>A♯/B♭<center></html>");
        two.setVerticalAlignment(SwingConstants.BOTTOM);
        two.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_2);
              robot.keyRelease(KeyEvent.VK_2);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        two.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(two);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey four = new BlackKey();
        four.setText("<html><bold>4<bold><br>C♯/D♭<center></html>");
        four.setVerticalAlignment(SwingConstants.BOTTOM);
        four.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_4);
              robot.keyRelease(KeyEvent.VK_4);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        four.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(four);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey five = new BlackKey();
        five.setText("<html><bold>5<bold><br>D♯/E♭<center></html>");
        five.setVerticalAlignment(SwingConstants.BOTTOM);
        five.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_5);
              robot.keyRelease(KeyEvent.VK_5);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        five.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(five);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey seven = new BlackKey();
        seven.setText("<html><bold>7<bold><br>F♯/G♭<center></html>");
        seven.setVerticalAlignment(SwingConstants.BOTTOM);
        seven.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_7);
              robot.keyRelease(KeyEvent.VK_7);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        seven.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(seven);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey eight = new BlackKey();
        eight.setText("<html><bold>8<bold><br>G♯/A♭<center></html>");
        eight.setVerticalAlignment(SwingConstants.BOTTOM);
        eight.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_8);
              robot.keyRelease(KeyEvent.VK_8);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        eight.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(eight);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey nine = new BlackKey();
        nine.setText("<html><center><bold>9<bold><br>A♯/B♭<center></html>");
        nine.setVerticalAlignment(SwingConstants.BOTTOM);
        nine.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_9);
              robot.keyRelease(KeyEvent.VK_9);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        nine.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(nine);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey minus = new BlackKey();
        minus.setText("<html><center><bold>-<bold><br>C♯/D♭<center></html>");
        minus.setVerticalAlignment(SwingConstants.BOTTOM);
        minus.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_MINUS);
              robot.keyRelease(KeyEvent.VK_MINUS);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(minus);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey equal = new BlackKey();
        equal.setText("<html><center><bold>=<bold><br>D♯/E♭<center></html>");
        equal.setVerticalAlignment(SwingConstants.BOTTOM);
        equal.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_EQUALS);
              robot.keyRelease(KeyEvent.VK_EQUALS);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        equal.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(equal);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey d = new BlackKey();
        d.setText("<html><center><bold>d<bold><br>F♯/G♭<center></html>");
        d.setVerticalAlignment(SwingConstants.BOTTOM);
        d.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_D);
              robot.keyRelease(KeyEvent.VK_D);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        d.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(d);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey f = new BlackKey();
        f.setText("<html><center><bold>f<bold><br>G♯/A♭<center></html>");
        f.setVerticalAlignment(SwingConstants.BOTTOM);
        f.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_F);
              robot.keyRelease(KeyEvent.VK_F);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        f.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(f);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey g = new BlackKey();
        g.setText("<html><center><bold>g<bold><br>A♯/B♭<center></html>");
        g.setVerticalAlignment(SwingConstants.BOTTOM);
        g.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_G);
              robot.keyRelease(KeyEvent.VK_G);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        g.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(g);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey j = new BlackKey();
        j.setText("<html><center><bold>j<bold><br>C♯/D♭<center></html>");
        j.setVerticalAlignment(SwingConstants.BOTTOM);
        j.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_J);
              robot.keyRelease(KeyEvent.VK_J);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        j.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(j);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey k = new BlackKey();
        k.setText("<html><center><bold>k<bold><br>D♯/E♭<center></html>");
        k.setVerticalAlignment(SwingConstants.BOTTOM);
        k.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_K);
              robot.keyRelease(KeyEvent.VK_K);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        k.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(k);
        pianoBlackKeys.add(new JLabel(""));
        pianoBlackKeys.add(new JLabel(""));
        BlackKey semicolon = new BlackKey();
        semicolon.setText("<html><center><bold>;<bold><br>F♯/G♭<center></html>");
        semicolon.setVerticalAlignment(SwingConstants.BOTTOM);
        semicolon.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_SEMICOLON);
              robot.keyRelease(KeyEvent.VK_SEMICOLON);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        semicolon.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(semicolon);
        pianoBlackKeys.add(new JLabel(""));
        BlackKey apostrophe = new BlackKey();
        apostrophe.setText("<html><center><bold>'<bold><br>G♯/A♭<center></html>");
        apostrophe.setVerticalAlignment(SwingConstants.BOTTOM);
        apostrophe.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              Robot robot = new Robot();
      
              // Simulate a key press
              robot.keyPress(KeyEvent.VK_QUOTE);
              robot.keyRelease(KeyEvent.VK_QUOTE);
      
            } catch (AWTException a) {
                    a.printStackTrace();
            }
          }
        });
        apostrophe.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            pFileButton.setBackground(Color.WHITE);
            pFileButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            pFileButton.setBackground(YaleBlue50);
            pFileButton.setForeground(Color.WHITE);
          }
        });
        pianoBlackKeys.add(apostrophe);
        pianoBlackKeys.add(new JLabel(""));

        // Repaint new content
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

      return frame;
    }
}