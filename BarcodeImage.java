
interface BarcodeIO{
	
	//accept some image and stores a copy. (clone or refined image)
	public boolean scan(BarcodeImage bc);
	//Accept text string to be eventually encoded in an image.
	public boolean readText(String text);
	//looks at internal text stored and produces a companion barcode image.
	public boolean generateImageFromText();
	//looks at the internal image stored and produces a companion text string
	public boolean translateImageToText();
	//prints out the text string to console
	public void displayTextToConsole();
	//prints out the image to the console.
	public void displayImageToConsole();
}

public class BarcodeImage implements Cloneable{
	//Constants
	//Dimensions
	public static final int MAX_HEIGHT = 30;
	public static final int MAX_WIDTH = 65; 
	//store image.  if smaller than max, instatiate memory but leave it white
	private boolean[][] imageData;
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	//Default Constructor
	BarcodeImage()
	{
		// instantiates a 2d array (heightxwidth and adds blanks (false)
		for(int i = 0; i < MAX_HEIGHT; i++)
		{
			for(int j = 0; j < MAX_WIDTH; j++)
			{
				imageData[j][i]= false;
			}
		}
	}
	
	//TODO NEED TO FIX TO USE SET PIXEL
	//Constructor with 1D array of strings.  converts to 2d array of booleans
	BarcodeImage(String[] strData)
	{
		//start adding from the bottom left
		this();
		// check size of strData to make sure it is smaller
		if (checkSize(strData))
		{
			// add data to an array  for max width
			// get MAX_WIDTH items and then add them to an array and put it into one row
			// once the end is reached, create a new array 
			int counter = 0;		// For width
			int extCounter = 1;		// For height
			boolean tempArray[] = new boolean[MAX_WIDTH];
			while ((counter*extCounter) < strData.length)
			{
				for(int i = 0; i < MAX_WIDTH; i++)
				{
					if (strData[i*extCounter] == "*")
					{
						tempArray[i] = true;
					}
					else
					{
						tempArray[i] = false;
					}
					counter ++;	// counts width
				}
				extCounter++;	// counts how height
				imageData[extCounter-1] = tempArray;	// Adds temp array to image data (Need to check if we need to copy each item over indiv
			}
		}
	}
	
	public boolean getPixel(int row, int col) 
	{
		return imageData[row][col];
	}
	
	public boolean setPixel(int row, int col, boolean value)
	{
		if( row <= MAX_WIDTH && col <= MAX_HEIGHT)
		{
			imageData[row][col] = value;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Check to see if it is smaller, bigger or null is not
	private boolean checkSize(String[] data)
	{
		if(data.length > (30*65))
			return false;
		else
			return true;
	}
	
	//debugging
	public void displayToConsole() 
	{
		
	}
}
