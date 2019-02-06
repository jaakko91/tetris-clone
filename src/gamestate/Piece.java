package gamestate;

//Class which has information and functions about a single piece (instances can be used as the current falling piece, next piece, as the background animation etc.)
//The pieces are created on PieceFactory class to a small "spawning platform" which is a small 4x4 two-dimensional array.
public class Piece {
	private int [][] piece;
	private int currentRotation;
	private int ID;
	private int color;

	//Pieces can be created either by specifying the shape (when pieces are created at PieceFactory), ID and color or alternatively by passing another instance of the class as a parameter.
	public Piece(int [][] piece,int pieceID, int color){
		this.piece=piece;
		this.ID=pieceID;
		this.color=color;
		this.currentRotation=1;
	}
	
	public Piece(Piece another){
		this.piece=new int [4][4];
		for(int x=0;x<4;x++)
			for(int y=0;y<4;y++)
				this.piece[x][y]=another.getPiece()[x][y];
		this.ID=another.getID();
		this.color=another.getColor();
		this.currentRotation=another.getCurrentRotation();
	}
	
	public int[][] getPiece() {
		return piece;
	}
	
	public void setPiece(int[][] piece) {
		this.piece = piece;
	}
	
	public int getCurrentRotation() {
		return currentRotation;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int pieceID) {
		this.ID = pieceID;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setcolor(int color) {
		this.color = color;
	}
	
	private void clearPiece(){
		for(int x=0;x<piece.length;x++)
			for(int y=0;y<piece[x].length;y++)
				piece[x][y]=-1;
	}
	
	//Rotates the piece. The tables include all used piece shapes and every possible rotation of them.
	public void rotate(){
		if(ID!=6)
			clearPiece();
		switch(ID){
			case 1: //Z
				if(currentRotation==1){
					piece[2][0]=color;
					piece[2][1]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					piece[2][2]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[2][0]=color;
					piece[2][1]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==4){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					piece[2][2]=color;
					currentRotation=1;
				}
				break;
			case 2: //S
				if(currentRotation==1){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[2][2]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[0][2]=color;
					piece[1][2]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[2][2]=color;
					currentRotation++;
					
				}
				else if (currentRotation==4){
					piece[0][2]=color;
					piece[1][2]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					currentRotation=1;
				}
				break;
			case 3: //J
				if(currentRotation==1){
					piece[1][0]=color;
					piece[2][0]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[0][0]=color;
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[1][0]=color;
					piece[0][2]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==4){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[2][2]=color;
					currentRotation=1;
				}
				break;
			case 4: //L
				if(currentRotation==1){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					piece[2][2]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[2][0]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[0][0]=color;
					piece[1][0]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==4){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[0][2]=color;
					currentRotation=1;
				}
				break;
			case 5: //T
				if(currentRotation==1){
					piece[1][0]=color;
					piece[0][1]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[1][0]=color;
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[1][2]=color;
					currentRotation++;
				}
				else if (currentRotation==4){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[1][2]=color;
					currentRotation=1;
				}
				break;
			case 6: //O
				if(currentRotation==1){
					currentRotation++;	
				}
				else if (currentRotation==2){
					currentRotation++;	
				}
				else if (currentRotation==3){
					currentRotation++;	
				}
				else if (currentRotation==4){
					currentRotation=1;
				}
				break;
			case 7: //I
				if(currentRotation==1){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					piece[1][3]=color;
					currentRotation++;
				}
				else if (currentRotation==2){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[3][1]=color;
					currentRotation++;
				}
				else if (currentRotation==3){
					piece[1][0]=color;
					piece[1][1]=color;
					piece[1][2]=color;
					piece[1][3]=color;
					currentRotation++;
					
				}
				else if (currentRotation==4){
					piece[0][1]=color;
					piece[1][1]=color;
					piece[2][1]=color;
					piece[3][1]=color;
					currentRotation=1;
				}
				break;
			default:break;
		}
	}
	
}
