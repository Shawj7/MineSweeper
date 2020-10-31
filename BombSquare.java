public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private boolean hasFlag;
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
            setImage("blank.png");
            bombCounter=0;
            for(int i=-1;i<2;i++)
            {
                for(int j=-1;j<2;j++) //counting all the bombs surrounding the square
                {
                    if((getXLocation()+i)>-1 && (getXLocation()+i)<21 && (getYLocation()+j)>-1 && (getYLocation()+j)<21)
                    {
                        if(((BombSquare)board.getSquareAt((getXLocation()+i),(getYLocation()+j))).hasBomb)
                        {
                            bombCounter++;
                            System.out.println(bombCounter);
                        }
                    }
                }
                setImage(bombCounter + ".png");
                if(bombCounter==0) //if there are no surrounding bombs
                {
                    fill((BombSquare)board.getSquareAt((getXLocation()-1),(getYLocation()-1)));
                }
            }
        }
    }
    public void fill(BombSquare square)
    {
        bombCounter=0;
       for(int i=-1;i<2;i++)
            {
                for(int j=-1;j<2;j++) //counting all the bombs surrounding the square
                {
                    if((getXLocation()+i)>-1 && (getXLocation()+i)<21 && (getYLocation()+j)>-1 && (getYLocation()+j)<21)
                    {
                        if((((BombSquare)board.getSquareAt((square.getXLocation()+i),(square.getYLocation()+j))).hasBomb))
                        {
                           bombCounter++;
                        }
                    }
                
                    if(bombCounter==0)
                    {
                        fill((BombSquare)board.getSquareAt((getXLocation()+i),(getYLocation()+j))); //call fill method to find next square
                    }
                }
                setImage(bombCounter + ".png");
                
            }
    }


}
