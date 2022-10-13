package assignment06;

import java.awt.*;

public class Level 
{
	public int[][] level;
	public int blockWidth;
	public int blockHeight;
	/**
	 * This constructor method builds the level using two parameters,
	 * which are given as number of rows and columns.
	 * 
	 * @param rows - an int, the number of rows
	 * @param columns - an int, the number of columns
	 */
	public Level (int rows, int columns) 
	{
		level = new int[rows][columns];
		for (int i = 0; i < level.length; i++)
		{
			for (int j = 0; j < level[0].length; j++)
			{
				level[i][j] = 1;
			}
		}
		blockWidth = 540/columns;
		blockHeight = 150/rows;
	}
	/**
	 * This method actually draws the built level.
	 * @param g - a 2D graphics object
	 */
	public void draw(Graphics2D g)
	{
		for (int i = 0; i < level.length; i++)
		{
			for (int j = 0; j < level[0].length; j++)
			{
				if (level[i][j] > 0)
				{
					g.setColor(getRandomColor());
					g.drawRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);
				}
			}
		}
	}
	/**
	 * This method assigns each block a value that can be used to 'delete' a block, once the ball hits it
	 * @param value - either a 0 (delete) or 1 (show)
	 * @param row - The row that the block is in
	 * @param column - The column that the block is in
	 */
	public void setBlockValue(int value, int row, int column)
	{
		level[row][column] = value;
	}
	
	/**
	 * This method generates and returns a random color.
	 * 
	 * @return a random color
	 */
	public Color getRandomColor()
	{
		int red = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		return new Color(red, green, blue);
	}
}

