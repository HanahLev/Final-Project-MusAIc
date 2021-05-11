//************************************************************************
// File: AutoSongGenerator.java           CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal		     	  Email: hanah.leventhal@yale.edu
//
// Class: AutoSongGenerator
// Dependencies: RingBuffer, GuitarString, StdAudio, StdDraw, ComposerMode,
//				 Menu, LoadingScreen
//
//   --------------------
//   
//      This program sets up the Auto Song Generator page GUI. There is a
//    	frame containing the menu, title, 3 drop downs and their labels, and 
//		the generate button. The chosen values are eventually passed through
//		to be used in the generator. An action listener for the generate button 
//		calls the getFileName method which consists of the menu, title, textField, 
//		and submit button. The submit button action listener calls the 
//		LoadingScreen loadingScreenGUI method, which in turn returns the program 
//		to this class's generatedSongGUI method. This method plays/draws the 
//		generated music (passed through as a tree map and converted to arrays), 
//		and draws the generated song page GUI which consists of the menu, title,
//		and note that the song has been saved to a .txt file.
//
//************************************************************************

// Based some code on pset8

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComboBox;
import java.util.*;
import java.util.Map;

public class AutoSongGenerator {
	private static Double[] timeArr;          // times indicated by generator
	private static String[] notesArr;      // notes as strings from generator
	private static final Color YaleBlue = new Color(0, 53, 107);
    private static final Color YaleBlue50 = new Color(0, 53, 107, 50);
    public static final String frameIconImage = "songgeneratoricon.PNG";
	private static final int borderThickness = 25;
	private static int selectedTempo = 1;
	private static int selectedKeySig = 0;
	private static int selectedTimeSig = 0;
	private static PrintStream o;

	public static void autoModeGUI(JFrame frame) {
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
        basePanel.setLayout(new GridLayout(3,1,0,25));
        basePanel.setBackground(YaleBlue);
        frame.add(basePanel, BorderLayout.CENTER);

		// Create middlePanel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1,4));
        middlePanel.setBackground(YaleBlue);
		
		// Create dropdownPanel
        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new GridLayout(3,1,0,25));
        dropdownPanel.setBackground(YaleBlue);

		// Create bottomPanel
		JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,3));
        bottomPanel.setBackground(YaleBlue);

		// Create bottomPanel
		JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBackground(YaleBlue);

		// Create empty panel for spacing
		JPanel empty = new JPanel();
		empty.setBackground(YaleBlue);
		empty.setForeground(YaleBlue);

		// Page title
        JLabel title = new JLabel("<html><ceneter>Auto Song Generator</center></html>", SwingConstants.CENTER){
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
        Font BrushScriptMT = new Font("Brush Script MT", Font.ITALIC, 150);
        title.setFont(BrushScriptMT);
        title.setForeground(Color.WHITE);

		// Create Tempo dropdown
		String[] tempoStrings = {"Adagio", "Moderato", "Allegro"};
		JComboBox tempoList = new JComboBox(tempoStrings);
		tempoList.setSelectedIndex(1);
		tempoList.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectedTempo = tempoList.getSelectedIndex();
			}
		});

		// Create Key Signature dropdown
		String[] keySigStrings = {"C major", "G major"};
		JComboBox keySigList = new JComboBox(keySigStrings);
		keySigList.setSelectedIndex(0);
		keySigList.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectedKeySig = keySigList.getSelectedIndex();
			}
		});

		// Create Time Signature dropdown
		String[] timeSigStrings = {"4/4", "3/4"};
		JComboBox timeSigList = new JComboBox(timeSigStrings);
		timeSigList.setSelectedIndex(0);
		timeSigList.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				selectedTimeSig = timeSigList.getSelectedIndex();
			}
		});

		// Text for dropdowns
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(3,1));
		textPanel.setBackground(YaleBlue);
		JLabel tempoText = new JLabel("Tempo: ", SwingConstants.CENTER);
		Font font2 = new Font("SansSerif", Font.BOLD, 25);
		tempoText.setFont(font2);
      	tempoText.setForeground(Color.WHITE);
		JLabel keySigText = new JLabel("Key Signature: ", SwingConstants.CENTER);
		keySigText.setFont(font2);
      	keySigText.setForeground(Color.WHITE);
		JLabel timeSigText = new JLabel("Time Signature: ", SwingConstants.CENTER);
		timeSigText.setFont(font2);
      	timeSigText.setForeground(Color.WHITE);
      	
		textPanel.add(tempoText);
		textPanel.add(keySigText);
		textPanel.add(timeSigText);

		// Create Generate button
		// Create and add content to compModeButton Panel
		JButton generateButton = new JButton("Generate"){
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
		Font font3 = new Font("SansSerif", Font.BOLD, 50);
		generateButton.setFont(font3);
		generateButton.setBackground(Color.WHITE);
		generateButton.setForeground(YaleBlue);
		generateButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
		generateButton.setToolTipText("<html>Click to generate a song.</html>");
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getFileName(frame);
			}
		});
		generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				generateButton.setBackground(YaleBlue);
				generateButton.setForeground(Color.WHITE);
				generateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				generateButton.setBackground(Color.WHITE);
				generateButton.setForeground(YaleBlue);
				generateButton.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				generateButton.setBackground(Color.WHITE);
				generateButton.setForeground(YaleBlue);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				generateButton.setBackground(YaleBlue50);
				generateButton.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				generateButton.setBackground(YaleBlue50);
				generateButton.setForeground(Color.WHITE);
			}
		});
		buttonPanel.add(generateButton);

        // add to basePanel   
        basePanel.add(title, Component.CENTER_ALIGNMENT);
		basePanel.add(middlePanel);

		// Add text and dropdowns to middlePanel
		middlePanel.add(empty);
		middlePanel.add(textPanel);
		middlePanel.add(dropdownPanel);
		middlePanel.add(empty);

		dropdownPanel.add(tempoList);
		dropdownPanel.add(keySigList);
		dropdownPanel.add(timeSigList);

		basePanel.add(generateButton);

        // Repaint new content
        frame.revalidate();
        frame.repaint();
	}
	
	// GUI for generated song
	public static JFrame generatedSongGUI(JFrame frame, Map<Double, String> generatedSongMap, JTextField fileNameInput, String musicFileName) {
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
        JLabel title = new JLabel("<html><ceneter>Playing Song</center></html>", SwingConstants.CENTER){
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
        JLabel extraText = new JLabel("<html><ceneter>The song has been saved to the chosen file name.</center></html>", SwingConstants.CENTER){
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

		// Set array length
		int n = generatedSongMap.size();
		// Convert map values (notes) to string array
		Collection<String> notes = generatedSongMap.values();
    	notesArr = notes.toArray(new String[n]);
		// Convert map keys (times) to double arrays
		Collection<Double> times = generatedSongMap.keySet();
    	timeArr = times.toArray(new Double[n]);

		try {
			// Creating a File object that represents the disk file.
			o = new PrintStream(new File(musicFileName));
		} catch (IOException e) {
			e.printStackTrace(); 
		}
  
        // Assign o to output stream
		for (int i = 0; i < n; i++){
			System.setOut(o);
			System.out.print(timeArr[i] + " ");
			System.out.println(notesArr[i]);
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
            // check if the user has typed a key, and, if so, process it
            keyIndex = 0;
			if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
				keyIndex = keyboard.indexOf(key);

                // pluck the corresponding string
				if (keyIndex < strings.length && keyIndex >= 0){
					strings[keyIndex].pluck();
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
	}

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
        
		// Add Name of file
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
				LoadingScreen.loadingScreenGUI(frame, selectedTempo, selectedKeySig, selectedTimeSig, fileNameInput, musicFileName);
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