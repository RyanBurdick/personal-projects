package game;

import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ResourceLoader 
{
	private Map<String, BufferedImage> backgrounds; 
	private Map<String, Path> pathWays; 
	private Map<String, Clip> sounds;
	static private ResourceLoader instance; 

	/**	This method creates a new ResourceLoader if
	 * 	one is not already present.
	 * 
	 * @return a resource loader
	 */
	static public ResourceLoader getLoader()
	{
		if (instance == null)
		{
			instance = new ResourceLoader(); 
		}
		return instance; 
	}

	//Constructor
	private ResourceLoader()
	{
		backgrounds = new HashMap<>(); 
		pathWays = new HashMap<>(); 

	}

	/** This method retrieves the appropriate clips.
	 * 
	 * 
	 * @param imageName The name of the image to retrieve.
	 * @return The appropriate BufferedImage.
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public Clip getClip (String s) throws UnsupportedAudioFileException, LineUnavailableException
	{ 
		if (sounds.containsKey(s))
		{
			return sounds.get(s); 
		}
		Clip result; 
		try
		{
			//			ClassLoader myLoader = this.getClass().getClassLoader();
			//			AudioInputStream inputStream = AudioSystem.getAudioInputStream(myLoader.getResourceAsStream("resources/" + s));
			//			result = (Clip) (inputStream);
			//			sounds.put(s, result); 
			//			return result; 
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("resources/" + s));
			result = AudioSystem.getClip();
			result.open(audioInputStream);
			sounds.put(s, result);
			return result;
		}
		catch (IOException e) 
		{
			System.err.println ("Could not load one of the files.");
			System.exit(0);  // Bail out.
			return null; 
		}
	}

	/** This method retrieves the appropriate images that the user is attempting
	 * 	to draw.
	 * 
	 * @param imageName The name of the image to retrieve.
	 * @return The appropriate BufferedImage.
	 */
	public BufferedImage getImage (String s)
	{ 
		if (backgrounds.containsKey(s))
		{
			return backgrounds.get(s); 
		}
		BufferedImage result; 
		try
		{
			ClassLoader myLoader = this.getClass().getClassLoader();
			InputStream imageStream = myLoader.getResourceAsStream("resources/" + s);
			result = javax.imageio.ImageIO.read(imageStream);
			backgrounds.put(s, result); 
			return result; 
		}
		catch (IOException e) 
		{
			System.err.println ("Could not load one of the files.");
			System.exit(0);  // Bail out.
			return null; 
		}
	}

	/** This method retrieves the appropriate Path to follow.
	 * 
	 * @param pathName The name of the path to retrieve.
	 * @return The appropriate Path.
	 */
	public Path getPath(String p)
	{
		if (pathWays.containsKey(p))
		{
			return pathWays.get(p); 
		}
		Path route; 

		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream pointStream = myLoader.getResourceAsStream("resources/" + p);

		Scanner in = new Scanner (pointStream);
		route =  new Path(in);
		pathWays.put (p, route); 
		return route; 
	}
}
