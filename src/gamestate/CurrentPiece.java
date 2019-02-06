package gamestate;

import main.Input;
import main.InputHandler;
import main.SoundPlayer;
import main.StateManager;

import org.newdawn.slick.Graphics;

//Class which has information and functions about the currently falling piece. Also contains the next incoming piece (which is known previously, since it's shown on the screen).
public class CurrentPiece implements Input{
	private Piece currentPiece = null;
	private NextPiece nextPiece = null;
	private int dropOffset, sideOffset;
	private final int platformSize=4;
	private Pit pit = null;
	private LevelManager lm = null;
	private int speed, tickCount,currentLevel;
	
	public CurrentPiece(Pit pit, LevelManager lm){
		this.pit=pit;
		this.lm=lm;
		speed=50;tickCount=0;currentLevel=1;
		InputHandler.addListener(this);
		dropOffset=2;sideOffset=6;
		nextPiece = new NextPiece();
		currentPiece=nextPiece.getPiece();
	}
	
	//Move piece down if it does not collide with anything. If it does, set piece down on the playfield and set current piece to the next piece.
	public void move(){
		if(checkCollision(0)){
			dropPiece();
			setCurrentPiece(nextPiece.getPiece());
			SoundPlayer.playSound(SoundPlayer.spawn);
		}
		else{
			dropOffset++;
		}	
	}
	
	public void timer(){
		tickCount++;
		if(tickCount>speed){
			tickCount=0;
			move();
		}
	}
	
	//Check collision. Checking if it's possible to move currentPiece down (direction = 0) or to the sides (left 1, right 2).
	public boolean checkCollision(int direction){
		switch(direction){
			case 0://When trying to move the piece down
				for(int x=0;x<platformSize;x++)
					for(int y=0;y<platformSize;y++)//Check every piece of the current block (taking into account where the piece would be on the next move, dropOffset+1 or sideOffset +-1) against the pit area. 
						//If the block and pit have a block at the same position, there is a collision.
						if(currentPiece.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset][y+dropOffset+1]!=-1)
							return true;
				return false;
			case 1://When trying to move the piece left
				for(int x=0;x<platformSize;x++)
					for(int y=0;y<platformSize;y++)
						if(currentPiece.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset-1][y+dropOffset]!=-1)
							return true;
				return false;
			case 2://When trying to move the piece right
				for(int x=0;x<platformSize;x++)
					for(int y=0;y<platformSize;y++)
						if(currentPiece.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset+1][y+dropOffset]!=-1)
							return true;
				return false;
			default:break;
		}
		return true;
	}
	
	//Set piece down on the playfield.
	//It is done by copying the currentPiece 2-dimensional array contents onto the pit 2-dimensional array to the current position.
	public void dropPiece(){
		for(int x=0;x<platformSize;x++)
			for(int y=0;y<platformSize;y++)
				if(currentPiece.getPiece()[x][y]!=-1)
					pit.getPit()[x+sideOffset][y+dropOffset]=currentPiece.getPiece()[x][y];
		lm.addScore(10+(dropOffset-1));
		dropOffset=2;
		sideOffset=6;
	}
	
	public void setCurrentPiece(Piece piece){
		this.currentPiece=piece;
	}
	
	public void setLevel(int level){
		currentLevel=level;
		switch(level){
			case 1:speed=50;break;
			case 2:speed=40;break;
			case 3:speed=30;break;
			case 4:speed=25;break;
			case 5:speed=20;break;
			case 6:speed=15;break;
			case 7:speed=10;break;
			default:break;
		}
	}
	
	public void reset(){
		speed=50;
		tickCount=0;currentLevel=1;
		dropOffset=2;sideOffset=6;
		nextPiece.reset();
		currentPiece=nextPiece.getPiece();
	}
	
	//Attempt to rotate the piece.
	//The function makes a copy of the current piece, rotates the copy and checks for collisions. If there are no collisions, it rotates the actual piece.
	public void rotatePiece(){
		Piece copy = new Piece(currentPiece);
		copy.rotate();
		boolean collision=false;
		for(int x=0;x<platformSize;x++){
			for(int y=0;y<platformSize;y++){	
				if(copy.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset][y+dropOffset]!=-1)
					collision=true;
				else if(copy.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset][y+dropOffset]!=-1)
					collision=true;
				else if(copy.getPiece()[x][y]!=-1 && pit.getPit()[x+sideOffset][y+dropOffset]!=-1)
					collision=true;
			}
		}
		if(!collision)
			currentPiece.rotate();
	}
	
	//If the game is running, draw the next and current piece.
	public void tick(Graphics g){
		nextPiece.draw(g);
		if(GameState.getGameState()==1){
			for(int x=0;x<platformSize;x++){
				for(int y=0;y<platformSize;y++){
					float posX=GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER+sideOffset*GameState.BLOCK_DIAMETER;
					float posY=GameState.PITOFFSETY+y*GameState.BLOCK_DIAMETER+dropOffset*GameState.BLOCK_DIAMETER;
					switch(currentPiece.getPiece()[x][y]){
						case 1:
							g.drawImage(Sprite.getSprite(Sprite.blue),posX,posY);
							break;
						case 2:
							g.drawImage(Sprite.getSprite(Sprite.gray),posX,posY);
							break;
						case 3:
							g.drawImage(Sprite.getSprite(Sprite.green),posX,posY);
							break;
						case 4:
							g.drawImage(Sprite.getSprite(Sprite.orange),posX,posY);
							break;
						case 5:
							g.drawImage(Sprite.getSprite(Sprite.red),posX,posY);
							break;
						case 6:
							g.drawImage(Sprite.getSprite(Sprite.lightBlue),posX,posY);
							break;
						case 7:
							g.drawImage(Sprite.getSprite(Sprite.yellow),posX,posY);
							break;
						default:break;
					}
				}
			}
		}
	}
	
	public Piece getPiece(){
		return currentPiece;
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key==org.newdawn.slick.Input.KEY_ESCAPE && GameState.getGameState()==3){
			StateManager.setState(1);
			GameState.setGameState(1);
		}
		if(key==org.newdawn.slick.Input.KEY_LEFT){
			if (!checkCollision(1))
				sideOffset--;
		}
		if(key==org.newdawn.slick.Input.KEY_RIGHT){
			if (!checkCollision(2))
				sideOffset++;
		}
		if(key==org.newdawn.slick.Input.KEY_UP){
			rotatePiece();
		}
		if(key==org.newdawn.slick.Input.KEY_DOWN){
			speed=5;
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		if(key==org.newdawn.slick.Input.KEY_DOWN){
			switch(currentLevel){
				case 1:speed=50;break;
				case 2:speed=40;break;
				case 3:speed=30;break;
				case 4:speed=25;break;
				case 5:speed=20;break;
				case 6:speed=15;break;
				case 7:speed=10;break;
				default:break;
			}
		}
		
	}

}
