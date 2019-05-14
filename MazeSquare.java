import java.awt.Graphics;
import java.awt.Color;

public class MazeSquare
{
    public enum SquareType{WALL,SPACE,PATH} 

    static final int SquareDimen = 15;
    int row,col;
    SquareType type;
    boolean visited = false;

    public MazeSquare(int row, int col, SquareType type) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public void clearSquare() 
    {   

        visited = false;
        if (this.type == SquareType.PATH) 
        {
            this.type = SquareType.SPACE;
        }

    }

    public void markVisited(){
        visited = true;
    } 

    public boolean getVisited(){
        return visited;
    }

    public boolean isWall()
    {   

        if(this.type == SquareType.WALL)
        {
            return true;
        }
        else
            return false;

    }

    public  void setToPath(){
        this.type = SquareType.PATH;
    } 

    public void drawSquare(Graphics g,int startX,int startY){ 

        if(this.type == SquareType.WALL){

            g.setColor(Color.DARK_GRAY);
            g.fillRect(startX + (row*SquareDimen),startY + (col*SquareDimen),SquareDimen,SquareDimen);
            g.setColor(Color.BLACK);
            g.drawRect(startX + (row*SquareDimen),startY + (col*SquareDimen),SquareDimen,SquareDimen);
        }

        if(this.type == SquareType.SPACE){

            g.setColor(Color.WHITE);
            g.fillRect(startX + (row*SquareDimen),startY + (col*SquareDimen),SquareDimen,SquareDimen);
            g.setColor(Color.BLACK);
            g.drawRect(startX + (row*SquareDimen),startY + (col*SquareDimen),SquareDimen,SquareDimen);

        }

        if(this.type == SquareType.PATH){

            g.setColor(Color.RED);
            g.fillRect(startX + (row*SquareDimen), startY + (col*SquareDimen),SquareDimen,SquareDimen);
            g.setColor(Color.BLACK);
            g.drawRect(startX + (row*SquareDimen), startY + (col*SquareDimen),SquareDimen,SquareDimen);
        }
    }
}
