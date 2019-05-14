import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import java.io.InputStream;
import java.io.File;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Color;

public class MazePanel extends JPanel 
{

 private static final long serialVersionUID = 1L;
 Maze maze;
    boolean solAt = false, solFd = false;
    public Scanner m;
    
    public void readMaze(File inputFile)  {
     this.solAt = false;
        this.solFd = false;
     try{
            maze = new Maze();
            maze.readMaze(inputFile);
            repaint();
        } catch  (Exception ex){
            throw ex;
        }
    }
   
    public void clearMazePath() {
        
        this.solAt = false;
        this.solFd = false;
        maze.clearMazePath();
        repaint();
    }
   
    public void solveMaze() {
        
        this.solAt = true;
        this.solFd = maze.solveMaze();
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = this.getSize();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0, dimension.width, dimension.height);

        if (maze != null) {
            
            maze.drawMaze(g, MazeSquare.SquareDimen*7, MazeSquare.SquareDimen*7);
            if ((solAt == true) && (solFd == true)) 
            {
        
                g.drawString("Solved!", maze.getNumCols()*MazeSquare.SquareDimen, 
                  (maze.getNumRows()+11)*MazeSquare.SquareDimen);
                       
            }
            if ((solAt == true) && (solFd  == false))
                g.drawString("No Solution exists for this Maze", maze.getNumCols()*MazeSquare.SquareDimen, 
                  (maze.getNumRows()+11)*MazeSquare.SquareDimen);
        }
    }
}


