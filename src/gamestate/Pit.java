package gamestate;

import org.newdawn.slick.Graphics;

//This class contains the play area (or "pit") and functions related to it.
//The play area is a two-dimensional array of integers. The number decides the block type.
public class Pit {
	private int [][] pit = null;
	private LineRemover lr = null;
	private LevelManager lm = null;
	private CurrentPiece currentPiece = null;
	private int lineCount;
	
	public Pit(){
		pit = new int [GameState.PITWIDTH][GameState.PITHEIGHT];
		clean();
		lm = new LevelManager(this);
		lr = new LineRemover(lm);
		currentPiece = new CurrentPiece(this,lm);
		lineCount=0;
	}
	
	public void saveScore(){
		lm.saveScore();
	}
	
	public LevelManager getLevelManager(){
		return lm;
	}
	
	//Empties the play area and resets the borders to the area.
	public void clean(){
		for(int x=0;x<GameState.PITWIDTH;x++){
			for(int y=0;y<GameState.PITHEIGHT;y++){
				if ((x==2||x==GameState.PITWIDTH-3)&&y>=3)//Add borders
					pit[x][y]=0;
				else if (y==GameState.PITHEIGHT-1 && x>2 && x<GameState.PITWIDTH-3)//Add borders
					pit[x][y]=0;
				else
					pit[x][y]=-1;
			}
		}
	}
	
	public void reset(){
		clean();
		lm.reset();
		currentPiece.reset();
		lineCount=0;
	}
	
	public void setLevel(int level){
		currentPiece.setLevel(level);
	}
	
	public int[][] getPit(){
		return pit;
	}
	
	//Check for full lines. If found, remove them using LineRemover class.
	public void checkFullLines(Graphics g){
		boolean full;
		int [] remove = new int[5];
		int count=0;
		for(int i=0;i<remove.length;i++)
			remove[i]=-1;
		
		//Go through each row one by one.
		//If even one space on an individual row is empty, row is not full.
		for(int y=0;y<GameState.PITHEIGHT-1;y++){
			full=true;
			for(int x=2;x<GameState.PITWIDTH-3;x++)
				if(pit[x][y]==-1)
					full=false;
			if(full){
				remove[count]=y;
				count++;
			}
		}
		if(count!=0){//If there are lines to be removed, remove them and add number of removed lines to counter.
			lr.removeLines(remove,pit);
			lineCount+=count;
		}
	}
	
	//If game is running, check for full lines and update falling piece.
	//Also draw and update all other required classes and functions.
	public void draw(Graphics g){
		if(GameState.getGameState()==1){
			checkFullLines(g);
			currentPiece.timer();
		}
		currentPiece.tick(g);
		drawPit(g);
		lm.tick(g);
		lr.tick(g);
	}
	
	//Check if game is over.
	//If even one of the spaces on the top row is full, game is decided over and function returns true.
	public boolean checkGameOver(){
		if(GameState.getGameState()==1){
			boolean over=false;
			for(int x=3;x<GameState.PITWIDTH-3;x++)
				if(pit[x][3]!=-1)
					over=true;
			return over;
		}
		return false;
	}
	
	//Draw the line counter, graphics and play area.
	private void drawPit(Graphics g){
		String lines = String.format("%06d", lineCount);
		for(int i=1;i<6;i++)
			g.drawImage( Sprite.getNumber( Character.getNumericValue( lines.charAt(i) )  ),i*18+GameState.PITOFFSETX-96,GameState.PITOFFSETY+295);	
		
		for(int x=0;x<GameState.PITWIDTH;x++){
			for(int y=0;y<GameState.PITHEIGHT;y++){
				switch(pit[x][y]){
					case 0://Border
						g.drawImage(Sprite.getSprite(Sprite.border),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 1://Blue
						g.drawImage(Sprite.getSprite(Sprite.blue),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 2://Gray
						g.drawImage(Sprite.getSprite(Sprite.gray),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 3://Green
						g.drawImage(Sprite.getSprite(Sprite.green),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 4://Orange
						g.drawImage(Sprite.getSprite(Sprite.orange),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 5://Red
						g.drawImage(Sprite.getSprite(Sprite.red),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 6://Violet
						g.drawImage(Sprite.getSprite(Sprite.lightBlue),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					case 7://Yellow
						g.drawImage(Sprite.getSprite(Sprite.yellow),
						GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
						GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER);
						break;
					default:break;
				}
			}
		}
	}
	
	
}
