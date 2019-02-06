package menustate;

import main.InputHandler;
import main.StateManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import main.Input;


//Class which contains the menu system and all code related to it.
public class MenuState {
	//Instances of buttons
	ButtonDisplayer newGame;
	//ButtonDisplayer settings; Settings menu is not implemented yet, it can be added later.
	ButtonDisplayer quit;
	
	//Instances of background animations
	PieceFaller pfleft, pfright;
	
	public void init(GameContainer gc) throws SlickException {
		Sprite.init();
		newGame = new ButtonDisplayer(gc);
		pfleft = new PieceFaller(0,340);
		pfright = new PieceFaller(850,main.StateManager.WINDOW_WIDTH);
		//settings = new ButtonDisplayer(gc);
		quit = new ButtonDisplayer(gc);
	}
	
	public void update(GameContainer arg0, int arg1) throws SlickException {
	}
	
	//Draw menu, monitor buttons for presses and update animations.
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.drawImage(Sprite.getSprite(Sprite.menubgright), 0, 0);
		g.drawImage(Sprite.getSprite(Sprite.menubgleft), 850, 0);
		g.drawImage(Sprite.getSprite(Sprite.menubg), 430, 30);

		handleButtons(arg0,g);
		pfleft.draw(g);
		pfright.draw(g);
	}
	
	//Draw buttons and check if they are pressed and do action according to it.
	private void handleButtons(GameContainer gc, Graphics g){
		int butX=430+30;int butY=335;//New Game
		if(newGame.displayButton(g,Sprite.getSprite(Sprite.newgame1),Sprite.getSprite(Sprite.newgame2),Sprite.getSprite(Sprite.newgame3),butX,butY))
			StateManager.setState(StateManager.gameState);
		
		/*butX=430+50; butY=453;//Settings
		if(settings.displayButton(g,Sprite.getSprite(Sprite.settings1),Sprite.getSprite(Sprite.settings2),Sprite.getSprite(Sprite.settings3),butX,butY))
			System.out.println("Settings");*/
		
		butX=430+121;butY=515;//Quit
		if(quit.displayButton(g,Sprite.getSprite(Sprite.quit1),Sprite.getSprite(Sprite.quit2),Sprite.getSprite(Sprite.quit3),butX,butY))
			System.exit(0);				
	}	
	
	//Class for generating background animation of pieces falling randomly. One animation on left and one on right side.
	public class PieceFaller{
		java.util.Random gen = null;
		public FallingPiece [] pieces = null;
		
		//When an instance is created, generate random falling pieces and place them randomly on top of the window (so they are not visible).
		public PieceFaller(int minX,int maxX){
			gen = new java.util.Random(System.currentTimeMillis()/1000L);
			pieces=new FallingPiece[60];
			for (int i=0;i<pieces.length;i++){
				int ID = gen.nextInt(7)+1;
				int x = gen.nextInt(maxX-minX)+minX;
				int y = gen.nextInt(main.StateManager.WINDOW_HEIGHT+400)-main.StateManager.WINDOW_HEIGHT-450;
				pieces[i] = new FallingPiece(ID,x,y);
				pieces[i].setDy((gen.nextInt(3)+0.5f)/2);
			}
		}
		
		//Update and draw all pieces. Updating drops them down and makes them look like they are falling.
		public void draw(Graphics g){
			for (int i=0;i<pieces.length;i++){
				pieces[i].update();
				g.drawImage(Sprite.getSprite(12+pieces[i].getID()),pieces[i].getX(),pieces[i].getY());
			}
		}		
	}
	
	//This class is used by the piece faller class. Every piece is one instance.
	public class FallingPiece{
		private float x,y;
		private float dy;
		private int ID;
		public FallingPiece(int ID,int x,int y){
			this.ID=ID;
			this.y=y;
			this.x=x;
			this.dy=0;
		}
		
		public float getX() {
			return x;
		}
		
		public void setX(float x) {
			this.x = x;
		}
		
		public float getY() {
			return y;
		}
		
		public void setY(float y) {
			this.y = y;
		}
		
		public float getDy() {
			return dy;
		}
		
		public void setDy(float dy) {
			this.dy = dy;
		}
		
		public int getID(){
			return this.ID;
		}
		
		//Move piece down every time update is called.
		//If the piece goes off the screen, return it back to the top.
		public void update(){
			y+=dy;
			if(y>main.StateManager.WINDOW_HEIGHT+100)
				y=-100;
		}
	}
	
	//Class to create buttons in menu and monitor presses. Every button is one instance.
	public class ButtonDisplayer implements Input{
		private boolean mousePressed,buttonPressed;
		private GameContainer gc = null;
		
		public ButtonDisplayer(GameContainer gc){
			this.gc=gc;
			InputHandler.addListener(this);
			mousePressed=false;
			buttonPressed=false;
		}
		
		public boolean displayButton(Graphics g,org.newdawn.slick.Image i1,org.newdawn.slick.Image i2,org.newdawn.slick.Image i3,int x,int y){
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			//Detects if mouse is hovered over a button and changes graphics accordingly. Also detects if a button is pressed.
			if(mouseX>x&&mouseX<x+i1.getWidth()&&
			   mouseY>y&&mouseY<y+i1.getHeight()){
				if(mousePressed){
					g.drawImage(i3,x,y);buttonPressed=true;
				}
				else
					g.drawImage(i2,x,y);
			
				if(!mousePressed){
					if(buttonPressed){
						buttonPressed=false;
						return true;
					}
					else
						buttonPressed=false;
				}
			}
			else{
				g.drawImage(i1,x,y);buttonPressed=false;
			}
			return false;	
		}
		
		public void mousePressed(int button, int x, int y) {
			mousePressed=true;
		}
		
		public void mouseReleased(int button, int x, int y) {
			mousePressed=false;
		}
		
		public void keyPressed(int key, char c) {}
		public void keyReleased(int key, char c) {}	
	}	
}