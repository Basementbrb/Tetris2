package Tetris2;

import java.util.Random;

public class Block 
{
    //determines the type of block
    int type;
    
    //the rotation of the block, default 0 out of max 3
    int rotation = 0;
    int oRotation;
    
    //OKAY SO COORDINATES FOR THE BLOCKS ARE REVERSED
    //MEANING X IS UP AND DOWN AND Y LEFT AND RIGHT
    //YEAH..
    
    
    //coordinates of the block
    int firstX;
    int firstY;
    int secondX;
    int secondY;
    int thirdX;
    int thirdY;
    int fourthX;
    int fourthY;
    
    //old coordinates of the block
    int oFirstX;
    int oFirstY;
    int oSecondX;
    int oSecondY;
    int oThirdX;
    int oThirdY;
    int oFourthX;
    int oFourthY;
    
    //backup for the old coordinates
    int oFirstXb;
    int oFirstYb;
    int oSecondXb;
    int oSecondYb;
    int oThirdXb;
    int oThirdYb;
    int oFourthXb;
    int oFourthYb;
    
    //Constructor for random block
    Block()
    {
        Random rn = new Random();
        type = rn.nextInt(7)+1;
        switch (type)
	{
            //red box-shape
            case 1:
                firstX = 0;
                firstY = 4;
                secondX = 0;
                secondY = 5;
                thirdX = 1;
                thirdY = 4;
                fourthX = 1;
                fourthY = 5;
                break;
            //blue L-shape
            case 2:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 3;
                break;
            //purple J-Shape
            case 3:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 5;
                break;
            //yellow T-shape
            case 4:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 4;
                break;
            //orange long one
            case 5:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 0;
                fourthY = 6;
                break;
            //green z-shape
            case 6:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 1;
                thirdY = 4;
                fourthX = 1;
                fourthY = 5;
                break;
            //light blue reverse z-shape
            case 7:
                firstX = 0;
                firstY = 4;
                secondX = 0;
                secondY = 5;
                thirdX = 1;
                thirdY = 3;
                fourthX = 1;
                fourthY = 4;
                break;
	}
    }
    
    //Constructor for specific block
    Block(int type)
    {
        this.type = type;
        switch (type)
	{
            //red box-shape
            case 1:
                firstX = 0;
                firstY = 4;
                secondX = 0;
                secondY = 5;
                thirdX = 1;
                thirdY = 4;
                fourthX = 1;
                fourthY = 5;
                break;
            //blue L-shape
            case 2:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 3;
                break;
            //purple J-Shape
            case 3:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 5;
                break;
            //yellow T-shape
            case 4:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 1;
                fourthY = 4;
                break;
            //orange long one
            case 5:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 0;
                thirdY = 5;
                fourthX = 0;
                fourthY = 6;
                break;
            //green z-shape
            case 6:
                firstX = 0;
                firstY = 3;
                secondX = 0;
                secondY = 4;
                thirdX = 1;
                thirdY = 4;
                fourthX = 1;
                fourthY = 5;
                break;
            //light blue reverse z-shape
            case 7:
                firstX = 0;
                firstY = 4;
                secondX = 0;
                secondY = 5;
                thirdX = 1;
                thirdY = 3;
                fourthX = 1;
                fourthY = 4;
                break;
	}
    }
    
    //returns the coords of the block
    public int[] getCoords()
    {        
        int[] coords = {firstX, firstY, secondX, secondY, thirdX, thirdY, fourthX, fourthY};
        return coords;
    }
    
    //returns the old coords of the block
    public int[] getOldCoords()
    {        
        int[] coords = {oFirstX, oFirstY, oSecondX, oSecondY, oThirdX, oThirdY, oFourthX, oFourthY};
        return coords;
    }
    
    //returns the backup of the old coords
    public int[] getOldBCoords()
    {
        int[] coords = {oFirstXb, oFirstYb, oSecondXb, oSecondYb, oThirdXb, oThirdYb, oFourthXb, oFourthYb};
        return coords;
    }
    
    //returns the type of the block
    public int getType()
    {
        return type;
    }
    
    //returns the rotation of the block
    public int getRotation()
    {
        return rotation;
    }
    
    //moves block down
    public int[] moveDown()
    {
        oFirstXb = oFirstX;
	oFirstYb = oFirstY;
	oSecondXb = oSecondX;
	oSecondYb= oSecondY;
	oThirdXb = oThirdX;
	oThirdYb = oThirdY;
	oFourthXb = oFourthX;
	oFourthYb = oFourthY;
        
        oFirstX = firstX;
        oFirstY = firstY;
        oSecondX = secondX;
        oSecondY = secondY;
        oThirdX = thirdX;
        oThirdY = thirdY;
        oFourthX = fourthX;
        oFourthY = fourthY;
        switch (type)
	{
	case 1:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 2:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 3:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 4:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 5:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 6:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	case 7:
		firstX += 1;
		secondX += 1;
		thirdX += 1;
		fourthX += 1;
		break;
	}
        
        int[] coords = {firstX, firstY, secondX, secondY, thirdX, thirdY, fourthX, fourthY};
        return coords;
    }
    
    //moves block left
    public int[] moveLeft()
    {
        oFirstXb = oFirstX;
	oFirstYb = oFirstY;
	oSecondXb = oSecondX;
	oSecondYb= oSecondY;
	oThirdXb = oThirdX;
	oThirdYb = oThirdY;
	oFourthXb = oFourthX;
	oFourthYb = oFourthY;
        
        oFirstX = firstX;
        oFirstY = firstY;
        oSecondX = secondX;
        oSecondY = secondY;
        oThirdX = thirdX;
        oThirdY = thirdY;
        oFourthX = fourthX;
        oFourthY = fourthY;
        switch (type)
	{
	case 1:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 2:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 3:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 4:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 5:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 6:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	case 7:
		firstY -= 1;
		secondY -= 1;
		thirdY -= 1;
		fourthY -= 1;
		break;
	}
        
        int[] coords = {firstX, firstY, secondX, secondY, thirdX, thirdY, fourthX, fourthY};
        return coords;
    }
    
    public int[] moveRight()
    {
        oFirstXb = oFirstX;
	oFirstYb = oFirstY;
	oSecondXb = oSecondX;
	oSecondYb= oSecondY;
	oThirdXb = oThirdX;
	oThirdYb = oThirdY;
	oFourthXb = oFourthX;
	oFourthYb = oFourthY;
        
        oFirstX = firstX;
        oFirstY = firstY;
        oSecondX = secondX;
        oSecondY = secondY;
        oThirdX = thirdX;
        oThirdY = thirdY;
        oFourthX = fourthX;
        oFourthY = fourthY;
        switch (type)
	{
	case 1:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 2:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 3:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 4:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 5:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 6:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	case 7:
		firstY += 1;
		secondY += 1;
		thirdY += 1;
		fourthY += 1;
		break;
	}
        
        int[] coords = {firstX, firstY, secondX, secondY, thirdX, thirdY, fourthX, fourthY};
        return coords;
    }
    
    //rotates the block
    public int[] rotate()
    {
        oFirstXb = oFirstX;
	oFirstYb = oFirstY;
	oSecondXb = oSecondX;
	oSecondYb= oSecondY;
	oThirdXb = oThirdX;
	oThirdYb = oThirdY;
	oFourthXb = oFourthX;
	oFourthYb = oFourthY;
        
        oFirstX = firstX;
        oFirstY = firstY;
        oSecondX = secondX;
        oSecondY = secondY;
        oThirdX = thirdX;
        oThirdY = thirdY;
        oFourthX = fourthX;
        oFourthY = fourthY;
        
        oRotation = rotation;
        switch (type)
	{
	case 1:
		break;
	case 2:
		switch (rotation)
		{
		case 0:
			thirdX += 1;
			thirdY -= 1;
			fourthX += 1;
			fourthY += 1;
			rotation = 1;
			break;
		case 1:
			firstY += 2;
			secondX += 1;
			secondY -= 1;
			fourthX -= 1;
			fourthY += 1;
			rotation = 2;
			break;
		case 2:
			firstY -= 1;
			secondY += 1;
			thirdX += 1;
			fourthX += 1;
			rotation = 3;
			break;
		case 3:
			firstY -= 1;
			secondX -= 1;
			thirdX -= 2;
			thirdY += 1;
			fourthX -= 1;
			fourthY -= 2;
			rotation = 0;
			break;
		}
		break;
	case 3:
		switch (rotation)
		{
		case 0:
			firstY += 1;
			secondX += 1;
			thirdX += 2;
			thirdY -= 2;
			fourthX += 1;
			fourthY -= 1;
			rotation = 1;
			break;
		case 1:
			firstY -= 1;
			secondY -= 1;
			thirdX -= 1;
			thirdY += 1;
			fourthX -= 1;
			fourthY += 1;
			rotation = 2;
			break;
		case 2:
			secondX -= 1;
			secondY += 1;
			thirdY -= 1;
			fourthX += 1;
			fourthY -= 2;
			rotation = 3;
			break;
		case 3:
			thirdX -= 1;
			thirdY += 2;
			fourthX -= 1;
			fourthY += 2;
			rotation = 0;
			break;
		}
		break;
	case 4:
		switch (rotation)
		{
		case 0:
			firstY += 1;
			secondX += 1;
			secondY -= 1;
			thirdX += 1;
			thirdY -= 1;
			fourthX += 1;
			rotation = 1;
			break;
		case 1:
			fourthX -= 1;
			fourthY += 1;
			rotation = 2;
			break;
		case 2:
			secondY += 1;
			thirdY += 1;
			fourthX += 1;
			fourthY -= 1;
			rotation = 3;
			break;
		case 3:
			firstY -= 1;
			secondX -= 1;
			thirdX -= 1;
			fourthX -= 1;
			rotation = 0;
			break;
		}
		break;
	case 5:
		switch (rotation)
		{
		case 0:
			firstY += 1;
			secondX += 1;
			thirdX += 2;
			thirdY -= 1;
			fourthX += 3;
			fourthY -= 2;
			rotation = 1;
			break;
		case 1:
			firstY -= 1;
			secondX -= 1;
			thirdX -= 2;
			thirdY += 1;
			fourthX -= 3;
			fourthY += 2;
			rotation = 2;
			break;
		case 2:
			firstY += 1;
			secondX += 1;
			thirdX += 2;
			thirdY -= 1;
			fourthX += 3;
			fourthY -= 2;
			rotation = 3;
			break;
		case 3:
			firstY -= 1;
			secondX -= 1;
			thirdX -= 2;
			thirdY += 1;
			fourthX -= 3;
			fourthY += 2;
			rotation = 0;
			break;
		}
		break;
	case 6:
		switch (rotation)
		{
		case 0:
			firstY += 2;
			secondX += 1;
			thirdY += 1;
			fourthX += 1;
			fourthY -= 1;
			rotation = 1;
			break;
		case 1:
			firstY -= 2;
			secondX -= 1;
			thirdY -= 1;
			fourthX -= 1;
			fourthY += 1;
			rotation = 2;
			break;
		case 2:
			firstY += 2;
			secondX += 1;
			thirdY += 1;
			fourthX += 1;
			fourthY -= 1;
			rotation = 3;
			break;
		case 3:
			firstY -= 2;
			secondX -= 1;
			thirdY -= 1;
			fourthX -= 1;
			fourthY += 1;
			rotation = 0;
			break;
		}
		break;
	case 7:
		switch (rotation)
		{
		case 0:
			secondX += 1;
			secondY -= 1;
			thirdY += 2;
			fourthX += 1;
			fourthY += 1;
			rotation = 1;
			break;
		case 1:
			secondX -= 1;
			secondY += 1;
			thirdY -= 2;
			fourthX -= 1;
			fourthY -= 1;
			rotation = 2;
			break;
		case 2:
			secondX += 1;
			secondY -= 1;
			thirdY += 2;
			fourthX += 1;
			fourthY += 1;
			rotation = 3;
			break;
		case 3:
			secondX -= 1;
			secondY += 1;
			thirdY -= 2;
			fourthX -= 1;
			fourthY -= 1;
			rotation = 0;
			break;
		}
		break;
	}
        
        int[] coords = {firstX, firstY, secondX, secondY, thirdX, thirdY, fourthX, fourthY};
        return coords;
    }
    
    //undos a move, replaces coordinates with old stored ones
    public void restore(int choice)
    {
        if(choice == 2)
        {
            rotation = oRotation;
        }
	firstX = oFirstX;
	firstY = oFirstY;
	secondX = oSecondX;
	secondY = oSecondY;
	thirdX = oThirdX;
	thirdY = oThirdY;
	fourthX = oFourthX;
	fourthY = oFourthY;
        
        oFirstX = oFirstXb;
	oFirstY = oFirstYb;
	oSecondX = oSecondXb;
	oSecondY= oSecondYb;
	oThirdX = oThirdXb;
	oThirdY = oThirdYb;
	oFourthX = oFourthXb;
	oFourthY = oFourthYb;       
    }
    
    //re-initializes block with new values
    public void reInitialize()
    {
	Random rn = new Random();
        type = rn.nextInt(7)+1;
	switch (type)
	{
	case 1:
		firstX = 0;
		firstY = 4;
		secondX = 0;
		secondY = 5;
		thirdX = 1;
		thirdY = 4;
		fourthX = 1;
		fourthY = 5;
		break;
	case 2:
		firstX = 0;
		firstY = 3;
		secondX = 0;
		secondY = 4;
		thirdX = 0;
		thirdY = 5;
		fourthX = 1;
		fourthY = 3;
		break;
	case 3:
		firstX = 0;
		firstY = 3;
		secondX = 0;
		secondY = 4;
		thirdX = 0;
		thirdY = 5;
		fourthX = 1;
		fourthY = 5;
		break;
	case 4:
		firstX = 0;
		firstY = 3;
		secondX = 0;
		secondY = 4;
		thirdX = 0;
		thirdY = 5;
		fourthX = 1;
		fourthY = 4;
		break;
	case 5:
		firstX = 0;
		firstY = 3;
		secondX = 0;
		secondY = 4;
		thirdX = 0;
		thirdY = 5;
		fourthX = 0;
		fourthY = 6;
		break;
	case 6:
		firstX = 0;
		firstY = 3;
		secondX = 0;
		secondY = 4;
		thirdX = 1;
		thirdY = 4;
		fourthX = 1;
		fourthY = 5;
		break;
	case 7:
		firstX = 0;
		firstY = 4;
		secondX = 0;
		secondY = 5;
		thirdX = 1;
		thirdY = 3;
		fourthX = 1;
		fourthY = 4;
		break;
	}
	oFirstX = firstX;
	oFirstY = firstY;
	oSecondX = secondX;
	oSecondY = secondY;
	oThirdX = thirdX;
	oThirdY = thirdY;
	oFourthX = fourthX;
	oFourthY = fourthY;
        
        oFirstXb = firstX;
	oFirstYb = firstY;
	oSecondXb = secondX;
	oSecondYb= secondY;
	oThirdXb = thirdX;
	oThirdYb = thirdY;
	oFourthXb = fourthX;
	oFourthYb = fourthY;
        
	rotation = 0;
    }    
}
