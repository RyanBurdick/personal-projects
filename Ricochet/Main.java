package assignment06;

import javax.swing.*;

public class Main implements Runnable {
	/**
	 * This is the main method of the program, that calls on the GUI to execute and run the 
	 * method.
	 * 
	 * Instructions for this game are located in the Gameplay method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Main());
	}
	public void run() 
	{
		JFrame content = new JFrame("Ricochet!");
		Gameplay gameplay = new Gameplay();

		content.add(gameplay);
		content.setSize(700, 600);
		content.setLocationRelativeTo(null);
		content.setResizable(false);
		content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content.setVisible(true);
	}

}
