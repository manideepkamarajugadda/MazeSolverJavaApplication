import java.io.File;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Maze  {
     final int NumRows=30,NumCols=30;
     private int startr,startc,endr,endc;
     MazeSquare[][] sq; 
     private Scanner m;


     int numbOfRows;
     int numbOfCols;
     public int getNumRows(){
          return numbOfRows;
     }

     public int getNumCols(){
          return numbOfCols;
     }
     
     public void readMaze(File inputFile)  {
          try 
          {
               m= new Scanner(inputFile);
               this.numbOfRows = Integer.parseInt(m.nextLine());
               this.numbOfCols = Integer.parseInt(m.nextLine());
               sq = new MazeSquare[numbOfRows][numbOfCols];
               for (int i = 0; i < numbOfRows; i++) 
               {
                    String line = m.nextLine();
                    char [] array = line.toCharArray();
                    for (int j = 0; j < numbOfCols; j++) 
                    {
                         MazeSquare.SquareType type = null;
                         if (array[j] == '#')
                         {
                              type = MazeSquare.SquareType.WALL;
                         }
                         if (array[j] == '.') 
                         {
                              type = MazeSquare.SquareType.SPACE;
                         }
                         if (array[j] == 's') {
                              startr = i;
                              startc = j;
                              type = MazeSquare.SquareType.SPACE;
                         }
                         if (array[j] == 'e') 
                         {
                              endr = i;
                              endc = j;
                              type = MazeSquare.SquareType.SPACE;
                         }
                         sq[i][j] = new MazeSquare(i,j,type);
                    }
                    
               }
              
               m.close();
          } 
          catch ( FileNotFoundException e) 
          {
               System.out.println("Can't find the file " + inputFile + ".");
             
          } 
          catch (NumberFormatException e) 
          {
             JOptionPane.showMessageDialog(null,"Error!");
                 
               System.out.println("There was a problem  " +
                       inputFile + ". Exiting.");
              
          }
          
          
     }
     public void   clearMazePath(){  
          for(int i=0;i<sq.length;i++){
               for(int j=0;j<sq[i].length;j++){
                    sq[i][j].clearSquare();
               }
          }
     }  
     public void drawMaze(Graphics g,int startX,int startY){
       try
       {
          for(int i=0; i<sq.length; i++)
          {
               for(int j=0; j<sq[i].length; j++)
               {
                    sq[i][j].drawSquare(g, startX, startY);
               }
          }
       }
       catch(NullPointerException e)
       {
         System.out.println("The File is Not in the Correct Format");
     }
     }

     public boolean solveMaze(){
      boolean returned = solveMaze(startr,startc);
          if (returned){
               sq[startr][startc].setToPath();
          }
          return returned;
     }
     
     private boolean solveMaze(int row,int col){
          if(row == endr && col == endc){
               sq[row][col].setToPath();
               return true;
          }
          if(sq[row][col].type == MazeSquare.SquareType.WALL ||sq[row][col].getVisited()==true){
               return false;
          }
          sq[row][col].markVisited();
          if(row - 1 >= 0){
               if(this.solveMaze(row-1,col)){
                    sq[row-1][col].setToPath();
                    return true;
               }
          }
          if(row != getNumRows()-1){
               if(this.solveMaze(row+1,col)){
                    sq[row+1][col].setToPath();
                    return true;
               }

          }
          if(col-1 >= 0 ){
               if(this.solveMaze(row,col-1)){
                    sq[row][col-1].setToPath();
                    return true;
               }

          }
          if(col != getNumCols()-1){
               if(this.solveMaze(row,col+1)){
                    sq[row][col+1].setToPath();
                    return true;
               }
          }
          return false;
     }
}
