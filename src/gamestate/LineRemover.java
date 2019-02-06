package gamestate;

import main.SoundPlayer;

import org.newdawn.slick.Graphics;

//This class is used to remove full lines from the playfield and then drop the upper blocks down one line.
public class LineRemover {
	private int framecount;
	private int [] linesToRemove;
	private boolean remove;
	private int currentBlock, tickDelta, currentLine, amount, animLength;
	private int [][] pit;
	private LevelManager lm = null;
	
	public LineRemover(LevelManager lm){
		this.lm=lm;
		framecount=0;
		remove=false;
	}
	
	//This function gets the lines to be removed and the playfield as parameters.
	//It increases score based on amount of lines (if there are 4 or more to be removed, get more points).
	//It also calculates the animation parameters, plays sounds etc.
	//The actual line removing and animation is done on the tick() function when the "remove" flag is set.
	public void removeLines(int [] y,int [][] pit){
		this.pit=pit;
		linesToRemove=y;
		amount=0;
		for(int i=0;i<linesToRemove.length;i++)
			if(linesToRemove[i]!=-1)
				amount++;
		if(amount<4)
			lm.addScore(amount*100);
		else if(amount==4)
			lm.addScore(800);
		remove=true;
		if(amount<4)
			animLength=amount*60;
		else if (amount>=4){
			animLength=80;
			SoundPlayer.playSound(SoundPlayer.lines4);
		}
		currentBlock=0;currentLine=1;tickDelta=0;
		GameState.setGameState(4);
		SoundPlayer.playSound(SoundPlayer.linebreak);
	}
	
	//If "remove" flag is set, remove the lines and play animation.
	//If there are less than 4 lines, remove them one by one in the animation.
	//If there are 4 or more lines to be removed, remove them all at once.
	public void tick(Graphics g){
		if(remove){
			if(amount<4){
				tickDelta++;
				//currentBlock is the block to be removed and it is increased when tickDelta reaches 10.
				//When currentBlock is bigger than 4, the line is fully removed and currentLine is increased which starts removing the next line.
				if(tickDelta>10){
					currentBlock++;
					tickDelta=0;
				}
				if(currentBlock>4){
					currentBlock=0;
					if(currentLine<amount){
						currentLine++;
						SoundPlayer.playSound(SoundPlayer.linebreak);
						}
					}
				for(int i=0;i<=currentBlock;i++){
					if(currentLine==0){
						pit[7-i][linesToRemove[amount-currentLine]]=-1;
						pit[8+i][linesToRemove[amount-currentLine]]=-1;
					}
					else if(currentLine==1){
						pit[7-i][linesToRemove[amount-currentLine]]=-1;
						pit[8+i][linesToRemove[amount-currentLine]]=-1;
					}
					else if(currentLine==2){
						pit[7-i][linesToRemove[amount-currentLine]]=-1;
						pit[8+i][linesToRemove[amount-currentLine]]=-1;
					}
					else if(currentLine==3){
						pit[7-i][linesToRemove[amount-currentLine]]=-1;
						pit[8+i][linesToRemove[amount-currentLine]]=-1;
					}
					else if(currentLine==4){
						pit[7-i][linesToRemove[amount-currentLine]]=-1;
						pit[8+i][linesToRemove[amount-currentLine]]=-1;
					}
				}
			}
			
			else if (amount>=4){
				tickDelta++;
				if(tickDelta>15 && currentBlock<4){
					currentBlock++;tickDelta=0;
				}
				//All the lines are removed at the same time.
				for(int i=0;i<=currentBlock;i++){
						pit[7-i][linesToRemove[0]]=-1;
						pit[8+i][linesToRemove[0]]=-1;
					
						pit[7-i][linesToRemove[1]]=-1;
						pit[8+i][linesToRemove[1]]=-1;
					
						pit[7-i][linesToRemove[2]]=-1;
						pit[8+i][linesToRemove[2]]=-1;
					
						pit[7-i][linesToRemove[3]]=-1;
						pit[8+i][linesToRemove[3]]=-1;
				}
			}
			
			framecount++;
			//When the animation is over, move down all the blocks above the removed line.
			if(framecount>animLength){	
				for(int y=linesToRemove[0]-1;y>0;y--){
					for(int x=2;x<GameState.PITWIDTH-3;x++){
						if(pit[x][y+amount]!=0)
							pit[x][y+amount]=pit[x][y];	
					}
				}
				
				remove=false;
				framecount=0;
				GameState.setGameState(1);
			}
		}
	}
	
}
