//************************************************************************
// File: Menu.java           		 CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal			 Email: hanah.leventhal@yale.edu
//
// Class: Menu
// Dependencies: MainPage, AutoSongGenerator, ComposerMode
//
//   --------------------
//   
//      This class creates a menu bar which is added to all of the
//		subsequent pages of the program. The menu items call the MainPage,
//		AutoSongGenerator, and ComposerMode methods to navigate to that
//		respective page.
//
//************************************************************************

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.*;
import javax.swing.*;

public class Menu {
    public static final String homeImage = "music@3x.png";
	public static final String compModeImage = "composericon.png";
	public static final String genModeImage = "songgeneratoricon.png";

    public static JMenuBar menuBarGUI(JFrame frame) {
        // Create Menu to get back to home
		JMenuBar menuBar;
		JMenu menu;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menu.getAccessibleContext().setAccessibleDescription("Menu for returning to specific pages");
		menuBar.add(menu);

		// Home menuItem
		JMenuItem homeMenuItem = new JMenuItem(new AbstractAction("Home", new ImageIcon(homeImage)) {
			public void actionPerformed(ActionEvent e) {
                MainPage.mainPageGUI(frame);
			}
		});
		homeMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(homeMenuItem);

		// CompMode menuItem
		JMenuItem compModeMenuItem = new JMenuItem(new AbstractAction("Composer Mode", new ImageIcon(compModeImage)) {
			public void actionPerformed(ActionEvent e) {
				ComposerMode.compModeGUI(frame);
			}
		});
		compModeMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(compModeMenuItem);

		// GenMode menuItem
		JMenuItem genModeMenuItem = new JMenuItem(new AbstractAction("Song Generator", new ImageIcon(genModeImage)) {
			public void actionPerformed(ActionEvent e) {
				AutoSongGenerator.autoModeGUI(frame);
			}
		});
		genModeMenuItem.setMnemonic(KeyEvent.VK_S);
		menu.add(genModeMenuItem);

        return menuBar;
    }
}
