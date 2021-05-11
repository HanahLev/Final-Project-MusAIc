//************************************************************************
// File: GuitarHeroAltered.java           CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal			 	  Email: hanah.leventhal@yale.edu
//
// Class: GuitarHeroAltered
// Dependencies: RingBuffer, GuitarString, StdAudio, StdDraw, ComposerMode,
//				 Menu, RecordLoading 
//
//   --------------------
//   
//      This program calls on the Guitar String and RingBuffer objects
//		in order to play guitar strings when the user types the 
//		corresponding lowercase character. An if statement checks
//		if a key has been pressed. If so, the character will be stored
//		and its corresponding index in the keyboard string will be used
//		to 'pluck' the appropriate string. While the notes are played, 
//		the wave is animated in StdDraw.
//
//		Additionally, this program creates the GUI pages for the record
//		functions. The getFileName method passes the frame and replaces
//		the contents with a title and textField. The user's input is then
//		taken and passed to the RecordLoading.loadingScreenGUI method.
//		That method then returns to this file's record method where the
//		ComposerMode GUI is called and the music is played/drawn in 
//		StdDraw and StdAudio
//
//************************************************************************

//*************************************************************
//	Altered slightly from pset 8 for project compatability
//*************************************************************

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GuitarHeroAltered {
	private static final Color YaleBlue = new Color(0, 53, 107);
	private static final Color YaleBlue50 = new Color(0, 53, 107, 50);
	public static final String frameIconImage = "composericon.PNG";
	private static PrintStream o;

    public static JFrame record(JFrame frame, JTextField fileNameInput, String musicFileName) {
		frame = ComposerMode.compModeGUI(frame);
		
		JLabel message = new JLabel("Recording in Progress...");
		message.setForeground(YaleBlue);
		message.setBackground(Color.WHITE);
		frame.add(message, BorderLayout.SOUTH);

		int    DRAW_SAMPLE_RATE = 20;    // draw at a rate of 20/sec
		int    AUDIO_PER_DRAW   = StdAudio.SAMPLE_RATE / DRAW_SAMPLE_RATE;

		int    PLAY_TIME        = 10;    // target 60 seconds display window
		int    XWIDTH           = DRAW_SAMPLE_RATE * PLAY_TIME;
		int    keyIndex = 0;

        // Create guitar strings for each key
        double CONCERT_A = 440.0;
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/\' "; // creates string with corresponding keys to the appropriate index
		int NUM_NOTES = 37;
		double freqArr[] = new double[NUM_NOTES];
		GuitarString strings[] = new GuitarString[NUM_NOTES];
		for (int i = 0; i < NUM_NOTES; i++){
	        freqArr[i] = CONCERT_A * Math.pow(2, (i - 24)/12.0);
			strings[i] = new GuitarString(freqArr[i]);
		}

		try {
			// Creating a File object that represents the disk file.
			o = new PrintStream(new File(musicFileName));
		} catch (IOException e) {
			e.printStackTrace(); 
		}

		// Set up parameters for visualization
		int canvasWidth = 768;
		int canvasHeight = 256;
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setXscale(0, XWIDTH);
		StdDraw.setYscale(-1, 1);

		// fence post
		double xprev = 0, yprev = 0;

        // the main input loop
		long startTime = System.currentTimeMillis(); // gets time at start of program
		boolean songPlaying = true;
        while (songPlaying) {
			keyIndex = 0;
			if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
				keyIndex = keyboard.indexOf(key);

                // pluck the corresponding string
				if (keyIndex < strings.length && keyIndex >= 0){
					strings[keyIndex].pluck();
					// Assign o to output stream
					System.setOut(o);
					System.out.println((System.currentTimeMillis() - startTime) + " " + key);
				}
            }

			// compute the superposition of the samples for duration
			double sample = 0;
			for (int i = 0; i < NUM_NOTES; i++){
				sample += strings[i].sample();
			}


			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for (int i = 0; i < NUM_NOTES; i++){
				strings[i].tic();
			}

			// Decide if we need to draw. 
			//   Audio sample rate is StdAudio.SAMPLE_RATE per second
			//   Draw sample rate is DRAW_SAMPLE_RATE
			//   Hence, we draw every StdAudio.SAMPLE_RATE / DRAW_SAMPLE_RATE
			if (keyIndex < strings.length && keyIndex >= 0 && strings[keyIndex].time() % AUDIO_PER_DRAW == 0) {
				StdDraw.line(xprev, yprev, xprev+1, sample);
				if (xprev != XWIDTH) { // check if wrapped around
					xprev ++;
				}
				else {
					StdDraw.clear();
					xprev = 0;
				}
				yprev = sample;
			} // end of if
			if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)){
				songPlaying = false;
			}
		} // end of while
		StdDraw.setCanvasSize(1,1);
		
		// Repaint new content
		frame.revalidate();
		frame.repaint();
		
		frame.requestFocus();

		ComposerMode.compModeGUI(frame);

		return frame;
    } // end of main

	public static JFrame getFileName(JFrame frame) {
		
		// Clear frame
        frame.getContentPane().removeAll();
        frame.setBackground(YaleBlue);
		frame.setLayout(new BorderLayout());

        //Set the frame icon to an image loaded from a file.
        frame.setIconImage(new ImageIcon(frameIconImage).getImage());

		// Add MenuBar to frame
		JMenuBar menuBar = Menu.menuBarGUI(frame);
		frame.add(menuBar, BorderLayout.NORTH);

		// Create titlePanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2,1));
		titlePanel.setBackground(YaleBlue);
		frame.add(titlePanel, BorderLayout.CENTER);

        // Page title
        JLabel title = new JLabel("<html><ceneter>Enter File Name:</center></html>", SwingConstants.CENTER){
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
        
		JLabel songFileName = new JLabel("");

		JPanel textInput = new JPanel();
		textInput.setLayout(new FlowLayout());
		textInput.setBackground(YaleBlue);

		// Create font
		Font font2 = new Font("SansSerif", Font.BOLD, 15);
		// create a object of JTextField with a given initial text
        JTextField fileNameInput = new JTextField("FileName.txt");
		fileNameInput.setFont(font2);
		fileNameInput.setPreferredSize(new Dimension(500, 50));
        fileNameInput.setMinimumSize(new Dimension(100, 25));

		// Create Submit Button
        int borderThickness = 5;
		JButton submitButton = new JButton("Submit"){
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
        submitButton.setFont(font2);
        submitButton.setBackground(Color.WHITE);
        submitButton.setForeground(YaleBlue);
        submitButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
        submitButton.setToolTipText("<html>Submit song file name.</html>");
        submitButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            	// set the text of the label to the text of the field
				songFileName.setText(fileNameInput.getText());

				String musicFileName = fileNameInput.getText();
				RecordLoading.loadingScreenGUI(frame, fileNameInput, musicFileName);
          }
        });
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseEntered(java.awt.event.MouseEvent evt) {
            submitButton.setBackground(YaleBlue);
            submitButton.setForeground(Color.WHITE);
            submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
          }
          @Override
          public void mouseExited(java.awt.event.MouseEvent evt) {
            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(YaleBlue);
            submitButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
          }
          @Override
          public void mouseReleased(MouseEvent e) {
            submitButton.setBackground(Color.WHITE);
            submitButton.setForeground(YaleBlue);
          }

          @Override
          public void mousePressed(MouseEvent e) {
            submitButton.setBackground(YaleBlue50);
            submitButton.setForeground(Color.WHITE);
          }

          @Override
          public void mouseClicked(MouseEvent e) {
            submitButton.setBackground(YaleBlue50);
            submitButton.setForeground(Color.WHITE);
          }
        });

        textInput.add(fileNameInput);
		textInput.add(submitButton);
		titlePanel.add(title);
        titlePanel.add(textInput);
		
		// Repaint new content
		frame.revalidate();
		frame.repaint();
		
		frame.requestFocus();

		return frame;
	}
} // end of class