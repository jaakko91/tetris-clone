package main;

import gamestate.GameState;
import menustate.MenuState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

//This is the main statemanager class which contains the main method.
//Depending on the currentState variable it will execute either GameState or MenuState render() and update() methods.
//When the state is changed, it will first execute init() method of the upcoming state.
public class StateManager extends BasicGame{
	public static int WINDOW_WIDTH=1280, WINDOW_HEIGHT=720;
	private static GameContainer gc;
	private static InputHandler input = null;
	private static GameState gs=null;
	private static MenuState ms=null;
	public static int menuState=1,gameState=2,pauseState=3;
	private static int currentState=0;
	
	public StateManager(String title) {
		super(title);
		input = new InputHandler();
		gs = new GameState();
		ms = new MenuState();
	}
	
	//Change state to a state passed as a parameter. 
	//The new state will be first initialized before changing it.
	public static void setState(int state){
		try{
			if(state>0 && state<=3){
				if(state==1)
					ms.init(gc);
				else if(state==2)
					gs.init(gc);
				currentState=state;
			}
		}catch(Exception e){}
	}
	
	public static int getState(){
		return currentState;
	}

	//Render the current state.
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		switch(currentState){
		case 1:
			ms.render(arg0,arg1);
			break;
		case 2:
			gs.render(arg0, arg1);
			break;
		case 3:
			break;
		}
	}
	
	//Initialize the game, starting with menu state.
	//Also receives the GameContainter which is used for Slick stuff.
	public void init(GameContainer arg0) throws SlickException {
		gc=arg0;
		StateManager.setState(StateManager.menuState);
	}
	
	//Update the current state.
	public void update(GameContainer arg0, int arg1) throws SlickException {
		switch(currentState){
		case 1:
			ms.update(arg0,arg1);
			break;
		case 2:
			gs.update(arg0,arg1);
			break;
		case 3:
			break;
		}
	}
	
	public void mousePressed(int button, int x, int y){
		input.mousePressed(button, x, y);
	}
	
	public void mouseReleased(int button, int x, int y){
		input.mouseReleased(button, x, y);
	}
	
	public void keyPressed(int key,char c) {
		input.keyPressed(key, c);
	}
	
	public void keyReleased(int key, char c){	
		input.keyReleased(key, c);
	}
	
	//Start the application by doing the required Slick2D related actions and then start running our own code.
	public static void main(String [] args){
		StateManager game=null;
		try{
			game = new StateManager("Tetris");
			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
			app.setShowFPS(false);
			app.setTargetFrameRate(100);
			app.setMaximumLogicUpdateInterval(30);
			app.setMinimumLogicUpdateInterval(30);
			app.setAlwaysRender(true);
			app.setForceExit(true);
			app.start();
		}catch(Exception e){e.printStackTrace();}
	}
	
}