package main;

//Class which receives and forwards input to all "listeners", 
//the other classes requiring input from keyboard or mouse.
public class InputHandler {
	private static Input [] listeners = null;
	private static int amountOfListeners=0;
	
	public static void addListener (Input add){
		InputHandler.listeners[amountOfListeners]=add;
		amountOfListeners++;
	}
	
	public static void resetListeners(){
		InputHandler.listeners = new Input [10];
		amountOfListeners=0;
	}
	
	public InputHandler(){
		listeners = new Input [10];
		amountOfListeners=0;
	}
	
	public void mousePressed(int button, int x, int y){
		for (int i=0;i<amountOfListeners;i++){
			listeners[i].mousePressed(button,x,y);
		}
	}
	
	public void mouseReleased(int button, int x, int y){
		for (int i=0;i<amountOfListeners;i++){
			listeners[i].mouseReleased(button,x,y);
		}
	}
	
	public void keyPressed(int key,char c){
		for (int i=0;i<amountOfListeners;i++){
			listeners[i].keyPressed(key,c);
		}
	}
	
	public void keyReleased(int key,char c){
		for (int i=0;i<amountOfListeners;i++){
			listeners[i].keyReleased(key,c);
		}
	}
}
