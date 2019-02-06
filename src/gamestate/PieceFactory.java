package gamestate;

//Class which is used to generate new pieces randomly. They are created on a small 4x4 "spawning platform" which is a two-dimensional array.
public class PieceFactory {
	java.util.Random gen = null;
	private int [] [] spawnPlatform = null;
	private final int platformSize=4,numberOfBlocks=7;
	
	public PieceFactory(){
		gen = new java.util.Random(System.currentTimeMillis()/1000L);
		spawnPlatform = new int [platformSize][platformSize];
		clearPlatform();
	}
	
	private void clearPlatform(){
		for(int x=0;x<platformSize;x++)
			for(int y=0;y<platformSize;y++)
				spawnPlatform[x][y]=-1;
	}
	
	public int getnumberOfBlocks(){
		return numberOfBlocks;
	}
	
	//Generates a random piece to the spawning platform and returns it as a Piece instance.
	//All the common "tetris blocks" are included. Each shape has its own color, that is not randomized.
	public Piece generate(){
		int random = gen.nextInt(numberOfBlocks)+1;
		int color=0;
		clearPlatform();
		switch(random){
			case 1://Z - Green
				spawnPlatform[0][1]=3; spawnPlatform[1][1]=3;
				spawnPlatform[1][2]=3; spawnPlatform[2][2]=3;
				color=3;
	//			[]  []  []  []
	//			[x] [x] []  []
	//			[]  [x] [x] []
	//			[]  []  []  []
				break;
			case 2://S - Yellow
				spawnPlatform[1][1]=7; spawnPlatform[2][1]=7;
				spawnPlatform[0][2]=7; spawnPlatform[1][2]=7;
				color=7;
	//			[]  []  []  []
	//			[]  [x] [x] []
	//			[x] [x] []  []
	//			[]  []  []  []
				break;
			case 3://J - Violet
				spawnPlatform[0][1]=6; spawnPlatform[1][1]=6; spawnPlatform[2][1]=6;
				spawnPlatform[2][2]=6;
				color=6;
	//			[]  []  []  []
	//			[x] [x] [x] []
	//			[]  []  [x] []
	//			[]  []  []  []
				break;
			case 4://L - Orange
				spawnPlatform[0][1]=4; spawnPlatform[1][1]=4; spawnPlatform[2][1]=4;
				spawnPlatform[0][2]=4;
				color=4;
	//			[]  []  []  []
	//			[x] [x] [x] []
	//			[x] []  []  []
	//			[]  []  []  []			
				break;
			case 5://T - Blue
				spawnPlatform[0][1]=1; spawnPlatform[1][1]=1; spawnPlatform[2][1]=1;
				spawnPlatform[1][2]=1;
				color=1;
	//			[]  []  []  []
	//			[x] [x] [x] []
	//			[]  [x] []  []
	//			[]  []  []  []	
				break;
			case 6://O - Red
				spawnPlatform[1][1]=5; spawnPlatform[2][1]=5;
				spawnPlatform[1][2]=5; spawnPlatform[2][2]=5;
				color=5;
	//			[]  []  []  []
	//			[]  [x] [x] []
	//			[]  [x] [x] []
	//			[]  []  []  []	
				break;
			case 7://I - Gray
				spawnPlatform[0][1]=2; spawnPlatform[1][1]=2; spawnPlatform[2][1]=2; spawnPlatform[3][1]=2;
				color=2;
	//			[]  []  []  []
	//			[x] [x] [x] [x]
	//			[]  []  []  []
	//			[]  []  []  []
				break;
			default:break;
		}
		return new Piece(spawnPlatform,random,color);
	}
}
