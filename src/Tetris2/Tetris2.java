package Tetris2;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Tetris2 extends JFrame
{
    
    Tetris2()
    {
        initUI();
    }
    
    private void initUI()
    {
        add(new Grid());
        setResizable(false);
        setTitle("Tetris");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                Tetris2 tetris = new Tetris2();
                tetris.setVisible(true);
            }
        });
    }    
}