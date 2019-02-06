package gamestate;

import org.newdawn.slick.Graphics;

//This class is used to keep track of the next incoming piece and draw it on the game (on top right side of the playfield).
//Also keeps track of the piece counters (how many of which shapes have been generated).
public class NextPiece{
	private PieceFactory pf = null;
	private Piece nextPiece = null, temp=null;
	private final int platformSize = 4;
	private int [] pieceCount = null;
	//	IDs:
	//	Z: Green, Color 3 ID 1
	//	S: Yellow, Color 7 ID 2
	//	J: Violet, Color 6 ID 3
	//	L: Orange, Color 4 ID 4
	//	T: Blue, Color 1 ID 5
	//	O: Red, Color 5 ID 6
	//	I: Gray, Color 2 ID 7 
	
	public NextPiece(){
		pf = new PieceFactory();
		pieceCount = new int [pf.getnumberOfBlocks()];
		nextPiece = pf.generate();
	}
	
	public int getPieceCount(int ID){
		return pieceCount[ID-1];
	}
	
	public void resetPieceCount(){
		for(int i=0;i<pf.getnumberOfBlocks();i++)
			pieceCount[i]=0;
	}
	
	public void reset(){
		pieceCount = new int[pf.getnumberOfBlocks()];
	}
	
	public void draw(Graphics g){
		//Draw the piece counters
		for(int t=0;t<pieceCount.length;t++){
			String count = String.format("%03d", pieceCount[t]);
			for(int i=1;i<3;i++)
				g.drawImage( Sprite.getNumber( Character.getNumericValue( count.charAt(i) )  ),i*18+GameState.PITOFFSETX+GameState.BLOCK_DIAMETER*GameState.PITWIDTH+24,
				GameState.PITOFFSETY+278+t*45);
		}
		//Draw the next upcoming piece
		for(int x=0;x<platformSize;x++){
			for(int y=0;y<platformSize;y++){	
				float xPos=GameState.PITOFFSETX+450+x*GameState.BLOCK_DIAMETER;
				float yPos=GameState.PITOFFSETY+120+y*GameState.BLOCK_DIAMETER;
				if(nextPiece.getID()!=7&&nextPiece.getID()!=6)
					xPos+=15;
				if(nextPiece.getID()==7)
					yPos+=15;
				switch(nextPiece.getPiece()[x][y]){
					case 1:
						g.drawImage(Sprite.getSprite(Sprite.blue),xPos,yPos);
						break;
					case 2:
						g.drawImage(Sprite.getSprite(Sprite.gray),xPos,yPos);
						break;
					case 3:
						g.drawImage(Sprite.getSprite(Sprite.green),xPos,yPos);
						break;
					case 4:
						g.drawImage(Sprite.getSprite(Sprite.orange),xPos,yPos);
						break;
					case 5:
						g.drawImage(Sprite.getSprite(Sprite.red),xPos,yPos);
						break;
					case 6:
						g.drawImage(Sprite.getSprite(Sprite.lightBlue),xPos,yPos);
						break;
					case 7:
						g.drawImage(Sprite.getSprite(Sprite.yellow),xPos,yPos);
					break;
					default:
					break;
				}
			}
		}
	}
	
	//Return the upcoming piece to be deployed on the playfield and generate new piece with piecefactory. 
	//Increase piece counter for the returned piece.
	public Piece getPiece(){
		temp=null;
		temp=new Piece(nextPiece);
		pieceCount[temp.getID()-1]++;
		nextPiece=pf.generate();
		return temp;
	}
}
