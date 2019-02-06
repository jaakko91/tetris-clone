package gamestate;

import main.SoundPlayer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

//Class which includes the game over animation. It is run when run() is called from GameState when it is detected that game is over.
//Also the high scores are recorded.
public class EndGame {
	private boolean running=false,isHighScore=false;
	private int frameCount=0,frameCount2=0,count=0;
	private Pit pit;
	private UnicodeFont fFont;
	
	@SuppressWarnings("unchecked")
	public EndGame(){	
		fFont = new UnicodeFont( new java.awt.Font("Impact",java.awt.Font.PLAIN,40) );
		fFont.addAsciiGlyphs(); 
		fFont.getEffects().add(new org.newdawn.slick.font.effects.ColorEffect( java.awt.Color.black ) );
		try{
			fFont.loadGlyphs();
		}
		catch(Exception e){e.printStackTrace();System.out.println("loadGlyphs() failed");} 
	}
	
	//Run the animation.
	public void run(Pit pit){
		this.pit=pit;
		running=true;
	}
	
	public void reset(){
		running=false;isHighScore=false;
		frameCount2=0;count=0;frameCount=0;
	}
	
	//Play the animation over the pit and play sounds. Timing is based on a framecounter.
	//If there is a high score, save it to a file.
	public void draw(Graphics g){
		if(running){
			frameCount++;
			//Start the animation, play game over sound and save possible high score.
			if(frameCount==1){
				SoundPlayer.playSound(SoundPlayer.gameover);
				if(pit.getLevelManager().getTopScore()<pit.getLevelManager().getTotalScore()){
					isHighScore=true;
					pit.saveScore();
				}
			}
			//Every 100 frames, draw one more row over the pit as in animation.
			if(frameCount>100){
				frameCount2++;
				if(frameCount2>15 && count<20){
					frameCount2=0;
					count++;
				}
			}
		}
		
		//Draw the rows over the pit, increasing slowly (as described above).
		for(int y=0;y<count;y++)
			for(int x=3;x<GameState.PITWIDTH-2;x++)
				g.drawImage(Sprite.getSprite(Sprite.border),
					GameState.PITOFFSETX+x*GameState.BLOCK_DIAMETER,
					(GameState.PITOFFSETY+y+3)*GameState.BLOCK_DIAMETER);
		
		//Draw the end score and possible high score notification.
		if(frameCount>500){
			for(int c=7;c<7+3;c++)
				g.drawImage(Sprite.getSprite(pit.getLevelManager().getCurrentLevel()+11),
					GameState.PITOFFSETX+3*GameState.BLOCK_DIAMETER,
					(GameState.PITOFFSETY+c)*GameState.BLOCK_DIAMETER);
			
			fFont.drawString(GameState.PITOFFSETX+3*GameState.BLOCK_DIAMETER+98, (GameState.PITOFFSETY+7)*GameState.BLOCK_DIAMETER-1,
					"SCORE");
			fFont.drawString(GameState.PITOFFSETX+3*GameState.BLOCK_DIAMETER+123, (GameState.PITOFFSETY+8)*GameState.BLOCK_DIAMETER+10,
					Integer.toString((int)pit.getLevelManager().getTotalScore()));
			
			if(isHighScore){
				for(int c=11;c<11+2;c++)
					g.drawImage(Sprite.getSprite(pit.getLevelManager().getCurrentLevel()+11),
						GameState.PITOFFSETX+3*GameState.BLOCK_DIAMETER,
						(GameState.PITOFFSETY+c)*GameState.BLOCK_DIAMETER);	
					
				fFont.drawString(GameState.PITOFFSETX+3*GameState.BLOCK_DIAMETER+20, (GameState.PITOFFSETY+11)*GameState.BLOCK_DIAMETER+4,
						"NEW HIGHSCORE");
			}		
		}
		
		//Stop the animation.
		if(frameCount>1000){
			frameCount=1000;
			running=false;
		}
	}
}
