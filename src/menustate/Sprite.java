package menustate;

import org.newdawn.slick.Image;

//Class for managing all the graphics used in the menu. When the class is initialized all the sprites are loaded.
//Sprites can be called for use by using getSprite() and passing the desired sprite number.
public class Sprite {
	public static int newgame1=1,newgame2=2,newgame3=3,settings1=4,settings2=5,settings3=6,quit1=7,quit2=8,quit3=9,menubg=10,menubgleft=11,menubgright=12,
		p1=13,p2=14,p3=15,p4=16,p5=17,p6=18,p7=19;
	private static Image inewgame1,inewgame2,inewgame3,isettings1,isettings2,isettings3,iquit1,iquit2,iquit3,imenubg,imenubgleft,imenubgright,
		ip1,ip2,ip3,ip4,ip5,ip6,ip7;
	
	public static void init(){
		try{
			inewgame1 = new Image("res/sprite/menu/newgame1.png");
			inewgame2 = new Image("res/sprite/menu/newgame2.png");
			inewgame3 = new Image("res/sprite/menu/newgame3.png");
			isettings1 = new Image("res/sprite/menu/settings1.png");
			isettings2 = new Image("res/sprite/menu/settings2.png");
			isettings3 = new Image("res/sprite/menu/settings3.png");
			iquit1 = new Image("res/sprite/menu/quit1.png");	
			iquit2 = new Image("res/sprite/menu/quit2.png");
			iquit3 = new Image("res/sprite/menu/quit3.png");
			imenubg = new Image("res/sprite/menu/menu.png");
			imenubgleft = new Image("res/sprite/menu/menubgleft.png");
			imenubgright = new Image("res/sprite/menu/menubgright.png");
			ip1 = new Image("res/sprite/pieces/I.png").getScaledCopy(0.5f);
			ip2 = new Image("res/sprite/pieces/J.png").getScaledCopy(0.5f);
			ip3 = new Image("res/sprite/pieces/L.png").getScaledCopy(0.5f);
			ip4 = new Image("res/sprite/pieces/O.png").getScaledCopy(0.5f);
			ip5 = new Image("res/sprite/pieces/S.png").getScaledCopy(0.5f);
			ip6 = new Image("res/sprite/pieces/T.png").getScaledCopy(0.5f);
			ip7 = new Image("res/sprite/pieces/Z.png").getScaledCopy(0.5f);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static Image getSprite (int number){
		switch (number){
			case 1:return inewgame1;	
			case 2:return inewgame2;	
			case 3:return inewgame3;	
			case 4:return isettings1;	
			case 5:return isettings2;	
			case 6:return isettings3;	
			case 7:return iquit1;	
			case 8:return iquit2;	
			case 9:return iquit3;
			case 10:return imenubg;
			case 11:return imenubgleft;
			case 12:return imenubgright;
			case 13:return ip1;
			case 14:return ip2;
			case 15:return ip3;
			case 16:return ip4;
			case 17:return ip5;
			case 18:return ip6;
			case 19:return ip7;
			default:break;
		}
		return null;
	}
}
