//************************************************************************
// File: GuitarHeroAutoAltered.java       CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal			 	  Email: hanah.leventhal@yale.edu
//
// Class: GuitarHeroAutoAltered
// Dependencies: RingBuffer, GuitarString, StdAudio, StdDraw, ComposerMode,
//				 Menu, PlayFileLoading 
//
//   --------------------
//   
//      This program calls on the Guitar String and RingBuffer objects
//		in order to play guitar strings for the user's chosen song file 
//		containing times and notes. The program reads the .txt file and
//		loads the time and note values in the loadMusic method. Those 
//		values are then stored and the note's corresponding index in the 
//		keyboard string will be used to 'pluck' the appropriate string at 
//		the correct time. While the notes are played, the wave is animated 
//		in StdDraw.
//
//		Additionally, this program creates the GUI pages for the playFile
//		functions. The getFileName method passes the frame and replaces
//		the contents with a title and textField. The user's input is then
//		taken and passed to the PlayFIleLoading.loadingScreenGUI method.
//		That method then returns to this file's playFile method where the
//		ComposerMode GUI is called and the music is played/drawn in 
//		StdDraw and StdAudio
//
//************************************************************************

//*************************************************************
//	Altered slightly from pset 8 for project compatability
//*************************************************************

import java.util.Scanner;
import javax.swing.JFrame;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuitarHeroAutoAltered {
	private static double[] timeArr;          // times indicated by source file
	private static String[] notesArr;      // notes as strings from source file
	private static final Color YaleBlue = new Color(0, 53, 107);
	private static final Color YaleBlue50 = new Color(0, 53, 107, 50);
	public static final String frameIconImage = "composericon.PNG";

    public static JFrame playFile(JFrame frame, String musicFileName) {
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
        JLabel title = new JLabel("<html><ceneter>Playing:</center></html>", SwingConstants.CENTER){
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
		titlePanel.add(title);

		String songPaying = ("\"" + musicFileName + "\"");
		// Page title
        JLabel songTitleLabel = new JLabel(songPaying, SwingConstants.CENTER){
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
        songTitleLabel.setFont(font3);
        songTitleLabel.setForeground(Color.WHITE);
		titlePanel.add(songTitleLabel);

		frame.setVisible(true);

		// Get info from source file
		loadMusic(musicFileName);
		int n = notesArr.length;

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
		int x = 0;
		long startTime = System.currentTimeMillis(); // gets time at start of program
		boolean songPlaying = true;
        while (songPlaying) {
			for (int i = x; i < timeArr.length; i++) {
				if ((System.currentTimeMillis() - startTime) >= (timeArr[i] * 10)) { // if time since start is greater than corresponding time value
					for (int j = 0; j < notesArr[i].length(); j++) { // iterates through string (if more than one note)
						keyIndex = keyboard.indexOf(notesArr[i].charAt(j));

                		// pluck the corresponding string
						if (keyIndex < strings.length && keyIndex >= 0){
							strings[keyIndex].pluck(); // press keys of corresponding note array and string indeces
						}
					}
					x++;
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
			if (strings[keyIndex].time() % AUDIO_PER_DRAW == 0) {
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
			if ((System.currentTimeMillis() - startTime) >= ((timeArr[n-1] * 10) + 5000)){
				songPlaying = false;
			}
		} // end of while
		StdDraw.setCanvasSize(1,1);
		
		// Repaint new content
		frame.revalidate();
		frame.repaint();
		
		frame.requestFocus();

		return frame;
    } // end of main

	// pull necessary information from music source file
	public static void loadMusic(String musicFileName) {
		Scanner scanner = null;
		// File IO with exception handling
		try {
			scanner = new Scanner(new File(musicFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Could not open " + musicFileName);
			System.exit(1);
		}
      
		// reading number of lines in file, N
		int N = 0;
		while (scanner.hasNextLine()) {
			N++;
			scanner.nextLine();
		}

		// create arrays with length N for time and notes
		timeArr = new double[N];
		notesArr = new String[N];
		scanner.close();

      	// Re-declare scanner to start at beginning of file
		Scanner input = null;
		// File IO with exception handling
		try {
			input = new Scanner(new File(musicFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Could not open " + musicFileName);
			System.exit(1);
		}

		// collect values from .txt file into arrays
		for (int i = 0; i < N; i++) {
			timeArr[i] = input.nextDouble();
			notesArr[i] = input.next();
		}

		// Close scanner
		input.close();
    }

	public static JFrame playFileGUI(JFrame frame) {

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

		// Create submit button
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
				PlayFileLoading.loadingScreenGUI(frame, fileNameInput, musicFileName);
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
