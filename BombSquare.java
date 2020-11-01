public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private boolean hasFlag;
    private boolean checked;
    private int bombCounter;

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }    

    public void rightClicked()
    {
        if(hasFlag==true)
        {
            setImage("0.png");
        }
        else
        {
            setImage("flag.png");
            hasFlag=true;
        }
    }

    public void leftClicked()
    {
        if(hasBomb)
        {
            setImage("bomb.png");
        }
        else
        {
            search((BombSquare)board.getSquareAt(getXLocation(), getYLocation()));
            System.out.println("Returned");
        }
    }
    /*public void fill(BombSquare square)
    {
        bombCounter=0;
       for(int i=-1;i<2;i++)
            {
                for(int j=-1;j<2;j++) //counting all the bombs surrounding the square
                {
                    if((getXLocation()+i)>-1 && (getXLocation()+i)<30 && (getYLocation()+j)>-1 && (getYLocation()+j)<30)
                    {
                        if((((BombSquare)board.getSquareAt((square.getXLocation()+i),(square.getYLocation()+j))).hasBomb))
                        {	
                           bombCounter++;
                           System.out.println(bombCounter + " now");
                           System.out.println("X is: " + square.getXLocation() + "Y is: " + square.getYLocation());
                        }
                    }
                }
            }
        System.out.println(bombCounter);
        if(bombCounter==0)
        {
            setImage("0.png");
            for(int i=-1;i<2;i++)
                {
                    for(int j=-1;j<2;j++)
                    {   System.out.println("Just here now");
                        if((square.getXLocation()+i)>-1 && (square.getXLocation()+i)<30 && (square.getYLocation()+j)>-1 && (square.getYLocation()+j)<30)
                        {
                            if((BombSquare)board.getSquareAt((square.getXLocation()+i),(square.getYLocation()+j)).checked==false)
                            {
                                System.out.println("We here");
                                fill((BombSquare)board.getSquareAt((getXLocation()+i),(getYLocation()+j))); //call fill method to find next square
                            }
                        }  
                    }
                }  
        }  
        else
        {
            setImage(bombCounter + ".png");
            return;
        }  
            
    }*/

    public void search(BombSquare square)
    {
        //System.out.println("a");
        if(count(square)>0)
        {
            square.setImage(count(square)+".png");
            return;
        }
        if(square.checked==true)
        {
            return;
        }
        square.setChecked(square);
        square.setImage("0.png");
        System.out.println("Square is Zero");
        for(int i=-1;i<2;i++)
        {
            for(int j=-1;j<2;j++)
            {
                search((BombSquare)board.getSquareAt((getXLocation()+i),(getYLocation()+j))); //call fill method to find next square
            }
        }

   
    }

    public int count(BombSquare selected) //counts the number of adjacent bombs
    {
        int bombCounter=0;
            for(int i=-1;i<2;i++)
                {
                    for(int j=-1;j<2;j++)
                    {
                        if((selected.getXLocation()+i)>-1 && (selected.getXLocation()+i)<30 && (selected.getYLocation()+j)>-1 && (selected.getYLocation()+j)<30)
                            {
                                if((((BombSquare)board.getSquareAt((selected.getXLocation()+i),(selected.getYLocation()+j))).hasBomb))
                                    {	
                                    bombCounter++;
                                    }
                            }
                    }
                }
        System.out.println(bombCounter);
        return bombCounter;
    }

    public void setChecked(BombSquare set)
    {
       set.checked=true;
    }


}
