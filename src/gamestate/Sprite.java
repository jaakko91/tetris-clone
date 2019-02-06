package gamestate;

import org.newdawn.slick.Image;

//Class for managing all the graphics used in-game. When the class is initialized all the sprites are loaded.
//Sprites can be called for use by using getSprite() and passing the desired sprite number.
//Some of the numbers are also drawn using sprites and they can be fetched using getNumber() and passing the number (0-9).
public class Sprite {
	public static int border=0,blue=1,gray=2,green=3,orange=4,red=5,lightBlue=6,yellow=7,bgleft=8,bgright=9,pitbg=10,top=11,
			slider1=12,slider2=13,slider3=14,slider4=15,slider5=16,slider6=17,slider7=18,slider8=19,slider9=20,slider10=21;
	private static Image b0,b1,b2,b3,b4,b5,b6,b7,ibgleft,ibgright,ipitbg,itop,
			islider1,islider2,islider3,islider4,islider5,islider6,islider7;
	private static Image [] numbers;
	
	public static void init(){
		try{
			b0 = new Image("res/sprite/blocks/border.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b1 = new Image("res/sprite/blocks/blue.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b2 = new Image("res/sprite/blocks/gray.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b3 = new Image("res/sprite/blocks/green.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b4 = new Image("res/sprite/blocks/orange.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b5 = new Image("res/sprite/blocks/red.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			b6 = new Image("res/sprite/blocks/lightblue.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);	
			b7 = new Image("res/sprite/blocks/yellow.png").getScaledCopy(GameState.BLOCK_DIAMETER,GameState.BLOCK_DIAMETER);
			ibgleft = new Image("res/sprite/bgleft.png");ibgright = new Image("res/sprite/bgright.png");ipitbg= new Image("res/sprite/pitbg.png");
			itop = new Image("res/sprite/top.png");
			islider1 = new Image("res/sprite/sliders/slider1.png");islider2 = new Image("res/sprite/sliders/slider2.png");
			islider3 = new Image("res/sprite/sliders/slider3.png");islider4 = new Image("res/sprite/sliders/slider4.png");
			islider5 = new Image("res/sprite/sliders/slider5.png");islider6 = new Image("res/sprite/sliders/slider6.png");
			islider7 = new Image("res/sprite/sliders/slider7.png");
			numbers = new Image[10];
			for(int i=0;i<numbers.length;i++){
				String filename="res/sprite/numbers/"+i+".png";
				numbers[i]=new Image(filename).getScaledCopy(0.8f);
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static Image getSprite (int number){
		switch (number){
			case 0:return b0;	
			case 1:return b1;	
			case 2:return b2;	
			case 3:return b3;	
			case 4:return b4;	
			case 5:return b5;	
			case 6:return b6;	
			case 7:return b7;	
			case 8:return ibgleft;	
			case 9:return ibgright;	
			case 10:return ipitbg;	
			case 11:return itop;
			case 12:return islider1;	
			case 13:return islider2;
			case 14:return islider3;
			case 15:return islider4;
			case 16:return islider5;
			case 17:return islider6;	
			case 18:return islider7;
			default:break;
		}
		return null;
	}
	
	public static Image getNumber(int i){
		return numbers[i];
	}
}
