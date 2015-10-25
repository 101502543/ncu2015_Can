// CharacterWalking Class
// CharacterWalking.java

// Imports
import java.awt.*;
import javax.swing.ImageIcon;

public class CharacterWalking extends Sprite
{
	public static Image[][] 	image;
	
	public CharacterWalking(Component comp, Point pos) 
	{
		super(comp, image[0], 0, 1, 2, pos);
	}

	public static void initResources(MediaTracker tracker, int id) 
	{
		image = new Image[4][4];
		for (int i = 0; i < 4; i++) 
		{
			for (int j = 0; j < 4; j++) 
			{
				ImageIcon img = new ImageIcon( "res/Walking" + (i+1) + (j+1) + ".gif" );
				image[i][j] = img.getImage();
				tracker.addImage(image[i][j], id);
			}
		}
  	}

  	public void setDirection(int dir) 
  	{
  		// Set the image
  		
  		if(dir > 3)
  		{
  			//stop
  			setImage(image[ (dir-4) ]);
  			setFrameInc(0);
  			setFrame(0);
  		}
  		else if( dir>=0 && dir<=3 )
  		{
  			//walk
  			setImage(image[dir]);
  			setFrameInc(1);
  		}
  		else
  		{
  			//idle
  			setImage(image[0]);
  			setFrameInc(0);
  		}
  	}
}
