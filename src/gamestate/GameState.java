package gamestate;

import main.InputHandler;
//import main.MusicPlayer;
import main.SoundPlayer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

//General state used when the game is running, the other one being MenuState while in menus. The state is decided on StateManager class.
public class GameState {
	//Y: 20 blocks is the actual pit, 1 block for bottom border, 3 blocks for top buffer (not visible)
	//X: 10 blocks is the actual pit, 2 (1 each side) blocks for borders, 4 (2 each side) buffers (not visible)
	public static int PITOFFSETX=400,PITOFFSETY=0,PITWIDTH=10+2+4,PITHEIGHT=20+1+3,BLOCK_DIAMETER=30;
	private Pit pit = null;																			 
	//private MusicPlayer mp = null;
	private EndGame eg = null;
	private static int gameState;//1 - In game, 2 - Pause, 3 - Game over, 4 - Paused (for animations)
	
	public void init(GameContainer arg0) throws SlickException {
		InputHandler.resetListeners();
		Sprite.init();
		SoundPlayer.init();
		eg = new EndGame();
		pit = new Pit();
		gameState=1;
		/*mp = new MusicPlayer();
		mp.init();
		mp.play();*/ //Music disabled for copyright reasons.
		resetGame();
	}
	
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
	}
	
	public void resetGame(){
		pit.reset();
		setGameState(1);
		eg.reset();
	}
	
	//Draw the background and the actual game.
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.drawImage(Sprite.getSprite(Sprite.bgleft),0,0);
		g.drawImage(Sprite.getSprite(Sprite.bgright),820,0);
		g.drawImage(Sprite.getSprite(Sprite.top),PITOFFSETX+2*BLOCK_DIAMETER,0);
		g.drawImage(Sprite.getSprite(Sprite.pitbg),PITOFFSETX+BLOCK_DIAMETER*3,PITOFFSETY+BLOCK_DIAMETER*3);
		pit.draw(g);
		eg.draw(g);
		//If detected the game is over, set gamestate to game over, stop music and run end animation.
		if(pit.checkGameOver()){
			setGameState(3);
			//mp.stop(); //Music disabled for copyright reasons.
			eg.run(pit);	
		}
		//arg0.setMouseGrabbed(true);
	}
	
	public static void setGameState(int state){
		gameState=state;
	}
	
	public static int getGameState(){
		return gameState;
	}
}
