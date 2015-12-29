/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Tim
 */
public class Grid extends JPanel implements ActionListener
{
    Boolean falling = true;
    Timer timer;
    int[][] grid = new int[20][12];
    int x = 10;
    int y = 10;
    int blockSize = 20;
    Block block = new Block();
    
    //default constructor, creates a transparent grid (0)
    Grid()
    {
        initGrid();
        timer = new Timer(400, this);
        timer.start();
        for(int col = 0; col < 20; col++)
            for(int row = 0; row < 12; row++)
                grid[col][row] = 0;
    }
        
    private void initGrid()
    {
        addKeyListener(new KeyHandler());
        setFocusable(true);
        setDoubleBuffered(true);
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        insertBlock(block.getCoords(), block.getType());
        x = 10;
        y = 10;
        for(int col = 0; col < 20; col++)
        {
            y+=blockSize;
            x=blockSize;
            for(int row = 0; row < 12; row++)
            {
                switch(getColor(col,row))
                {
                    case 0:
                        g2d.setColor(new Color(1,1,1,0)); //transparent
                        break;                        
                    case 1:
                        g2d.setColor(Color.RED);
                        break;
                    case 2:
                        g2d.setColor(Color.BLUE);
                        break;
                    case 3:
                        g2d.setColor(Color.MAGENTA);
                        break;
                    case 4:
                        g2d.setColor(Color.YELLOW);
                        break;
                    case 5:
                        g2d.setColor(Color.ORANGE);
                        break;
                    case 6:
                        g2d.setColor(Color.GREEN);
                        break;
                    case 7:
                        g2d.setColor(Color.CYAN);
                        break;
                }
                g2d.fillRect(x, y, blockSize, blockSize);
                x+=blockSize;
            }
        }
    }    
    
        
    //returns color from a point in the grid
    public int getColor(int x, int y)
    {
        return grid[x][y];
    }
    
    //inserts block into grid
    public void insertBlock(int[] coords, int type)
    {
        for (int i = 0; i < 8; i++)
	{
            grid[coords[i]][coords[i + 1]] = type;
            i++;
	}
    }
   
    //removes block from grid
    public void removeBlock(int[] coords)
    {
        for (int i = 0; i < 8; i++)
	{
            grid[coords[i]][coords[i + 1]] = 0;
            i++;
	}
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        checkRows();
        if(!falling)
        {
            falling = true;
            block.reInitialize();
        }
        else
        {
            if (!outOfBoundsDown(block.moveDown()))
            {
                removeBlock(block.getCoords());
                insertBlock(block.moveDown(), block.getType());
                repaint();
            }
            else
            {
                falling = false;
            }
        }
    }
    
    public class KeyHandler implements KeyListener
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
        }
    
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
        
            if(key == KeyEvent.VK_LEFT)
            {
                if(!outOfBoundsLeft(block.moveLeft()) && !outOfBoundsDown(block.moveDown()))
                {           
                    removeBlock(block.getCoords());
                    insertBlock(block.moveLeft(), block.getType());
                    repaint();
                }
            }

            if(key == KeyEvent.VK_RIGHT)
            {
                if (!outOfBoundsRight(block.moveRight()) && !outOfBoundsDown(block.moveDown()))
                {
                    removeBlock(block.getCoords());
                    insertBlock(block.moveRight(), block.getType());
                    repaint();
                }
            }

            if(key == KeyEvent.VK_UP)
            {
                if(canRotate(block.rotate()))
                {
                    removeBlock(block.getCoords());
                    insertBlock(block.rotate(), block.getType());
                    repaint();
                }
            }

            if(key == KeyEvent.VK_DOWN)
            {
                if (!outOfBoundsDown(block.moveDown()))
                {
                    removeBlock(block.getCoords());
                    insertBlock(block.moveDown(), block.getType());
                    repaint();
                }
            }
            if(key == KeyEvent.VK_ENTER)
            {
                block.reInitialize();
                insertBlock(block.getCoords(), block.getType());
                repaint();
            }
            if(key == KeyEvent.VK_SPACE)
            {
                block.reInitialize();
                checkRows();
                repaint();
            }
            if(key == KeyEvent.VK_ESCAPE)
            {
                running = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
        }
    }
    
    Boolean outOfBoundsDown(int[] coords)
    {
	Boolean outOfBounds = false;
	for (int i = 0; i < 8; i++)
	{
		if (coords[i] > 19)
		{
			outOfBounds = true;
		}
		i++;
	}
        if(!outOfBounds)
        {
            switch(block.getType())
            {
                //red block
                case 1:
                    if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                    {
                        outOfBounds = true;
                    }
                    break;
                //blue L-shape
                case 2:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Purple J-shape
                case 3:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Yellow T-shape
                case 4:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Orange long one
                case 5:
                    switch(block.getRotation())
                    {
                        case 0:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                        case 1:
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                        case 3:
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //green z-shape
                case 6:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //light blue reverse z-shape
                case 7:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
            }
        }
	block.restore(1);
	return outOfBounds;
    }
    
    Boolean outOfBoundsLeft(int[] coords)
    {
	Boolean outOfBounds = false;
	for (int i = 0; i < 8; i++)
	{
		if (coords[i+1] < 0)
		{
			outOfBounds = true;
		}
		i++;
	}
        if(!outOfBounds)
        {
            switch(block.getType())
            {
                //red block
                case 1:
                    if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                    {
                        outOfBounds = true;
                    }
                    break;
                //Blue L-shape
                case 2:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            //1 and 2 or 1 and 4 CHECK
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[4]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Purple J-shape
                case 3:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Yellow T-shape
                case 4:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //orange long one
                case 5:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                    }
                    break;
                //green z-shape
                case 6:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //ligh blue reverse z-shape
                case 7:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
            }
        }
	block.restore(1);
	return outOfBounds;
    }
    
    Boolean outOfBoundsRight(int[] coords)
    {
	Boolean outOfBounds = false;
	for (int i = 0; i < 8; i++)
	{
		if (coords[i+1] > 11)
		{
			outOfBounds = true;
		}
		i++;
	}
        if(!outOfBounds)
        {
            switch(block.getType())
            {
                //red block
                case 1:
                    if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                    {
                        outOfBounds = true;
                    }
                    break;
                //Blue L-shape
                case 2:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            //1 and 4 or 1 and 2 CHECK
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Purple J-shape
                case 3:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //Yellow T-shape
                case 4:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //orange long one
                case 5:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                        case 2:
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            for (int i = 0; i < 8; i++)
                            {
                                if(grid[coords[i]][coords[i + 1]] != 0)
                                {
                                    outOfBounds = true;
                                }
                                i++;
                            }
                            break;
                    }
                    break;
                //green z-shape
                case 6:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
                //ligh blue reverse z-shape
                case 7:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                outOfBounds = true;
                            }
                            break;
                    }
                    break;
            }
        }
	block.restore(1);
	return outOfBounds;
    }
    
    Boolean canRotate(int[] coords)
    {
        Boolean canRotate = true;
        for (int i = 0; i < 8; i++)
	{
		if (coords[i] > 19 || coords[i+1] < 0 || coords[i+1] > 11)
		{
			canRotate = false;
		}
		i++;
	}
        if(canRotate)
        {
            switch(block.getType())
            {
                //Red block
                case 1:
                    break;
                //Blue L-shape
                case 2:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }                            
                            break;
                        case 1:                            
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }                            
                            break;
                    }
                    break;
                //purple J-shape
                case 3:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 1:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                    }
                    break;
                //yellow T-shape
                case 4:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 1:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:                            
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                    }
                    break;
                //orange long one
                case 5:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 1:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                    }                    
                    break;
                //green z-shape
                case 6:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 1:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[2]][coords[3]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[0]][coords[1]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                    }
                    break;
                //light blue reverse z-shape
                case 7:
                    switch(block.getRotation())
                    {
                        case 0:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 1:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 2:
                            if(grid[coords[2]][coords[3]] != 0 || grid[coords[4]][coords[5]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                        case 3:
                            if(grid[coords[4]][coords[5]] != 0 || grid[coords[6]][coords[7]] != 0)
                            {
                                canRotate = false;
                            }
                            break;
                    }
                    break;
            }
        }
        block.restore(2);
        return canRotate;
    }
    
    public void checkRows()
    {
        for (int row = 0; row < 20; row++)
	{
		int count = 0;
		for (int column = 0; column < 12; column++)
		{
			if (grid[row][column] != 0)
			{
				count += 1;
			}
		}
		if (count == 12)
		{
			//should increase score by one here
			for (int column = 0; column < 12; column++)
			{
				grid[row][column] = 0;
			}
			for (int row2 = row; row2 > 0; row2--)
			{
				for (int column = 0; column < 12; column++)
				{
					grid[row2][column] = grid[row2 - 1][column];
				}
			}
		}
	}
    }
}