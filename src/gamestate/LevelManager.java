package gamestate;

import main.SettingsParser;

import org.newdawn.slick.Graphics;

//This class contains the playfield ("pit") and information and functions related to it such as the current score and level.
public class LevelManager {
	private SettingsParser sp = null;
	private float totalScore,topScore;
	private float levelScore;
	private float levelMaxScore;
	private int currentLevel;
	private Pit pit;
	private float sliderLength,sliderMaxLength,sliderScore,sliderDelta;
	
	public LevelManager(Pit pit){
		this.pit=pit;
		sp = new SettingsParser();
		//Load previous highscore from file, if possible.
		if (sp.load("top")==null)
			topScore=0;
		else 
			topScore=Integer.parseInt(sp.load("top"));
		levelMaxScore=1000;
		sliderLength=0;sliderMaxLength=150;
		totalScore=0;levelScore=0;sliderScore=0;sliderDelta=0;
		currentLevel=1;
	}
	
	public void addScore(float amount){
		totalScore+=amount;
		levelScore+=amount;
	}
	
	public void reset(){
		sliderLength=0;
		totalScore=0;levelScore=0;sliderScore=0;sliderDelta=0;
		currentLevel=1;
	}
	
	//If score is higher then the previous highscore, save it to a file.
	public void saveScore(){
		if(totalScore>topScore){
			String [][] top = new String [1][2];
			top[0][0]="top";top[0][1]=Integer.toString((int)totalScore);
			sp.write(top);
			topScore=totalScore;
		}
	}
	
	public int getCurrentLevel(){
		return currentLevel;
	}
	
	public float getTotalScore(){
		return totalScore;
	}
	
	public float getTopScore(){
		return topScore;
	}
	
	public void tick(Graphics g){
		//Increase level (speed of game), if levelMaxScore is reached.
		if(levelScore>levelMaxScore){
			if(currentLevel<7){
				currentLevel++;levelScore=0;
				pit.setLevel(currentLevel);
				sliderScore=0;sliderDelta=0;
			}
		}
		//Update the slider which indicates level progress.
		if(levelScore>sliderScore)
			sliderDelta=1;
		else if(levelScore<sliderScore)
			sliderDelta=-1;
		else
			sliderDelta=0;
		sliderScore=sliderScore+sliderDelta;
		sliderLength=sliderMaxLength*(sliderScore/levelMaxScore);
		if(sliderLength>sliderMaxLength)
			sliderLength=sliderMaxLength;
		
		//Draw sliders.
		g.drawImage(Sprite.getSprite(currentLevel+11),GameState.PITOFFSETX+(GameState.PITWIDTH*GameState.BLOCK_DIAMETER)/2,GameState.BLOCK_DIAMETER,
				GameState.PITOFFSETX+(GameState.PITWIDTH*GameState.BLOCK_DIAMETER)/2+sliderLength,GameState.BLOCK_DIAMETER+Sprite.getSprite(currentLevel+11).getHeight(),
				0,0,
				sliderLength,Sprite.getSprite(currentLevel+11).getHeight());
		g.drawImage(Sprite.getSprite(currentLevel+11),GameState.PITOFFSETX+(GameState.PITWIDTH*GameState.BLOCK_DIAMETER)/2,GameState.BLOCK_DIAMETER,
				GameState.PITOFFSETX+(GameState.PITWIDTH*GameState.BLOCK_DIAMETER)/2-sliderLength,GameState.BLOCK_DIAMETER+Sprite.getSprite(currentLevel+11).getHeight(),
				0,0,
				sliderLength,Sprite.getSprite(currentLevel+11).getHeight());
		
		//Draw scores.
		String score = String.format("%06d", (int)totalScore);
		for(int i=1;i<6;i++)
			g.drawImage( Sprite.getNumber( Character.getNumericValue( score.charAt(i) )  ),i*18+GameState.PITOFFSETX-96,GameState.PITOFFSETY+147);
		String top = String.format("%06d", (int)topScore);
		for(int i=1;i<6;i++)
			g.drawImage( Sprite.getNumber( Character.getNumericValue( top.charAt(i) )  ),i*18+GameState.PITOFFSETX-96,GameState.PITOFFSETY+205);	
	}
}