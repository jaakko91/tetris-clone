package main;

import org.newdawn.slick.Sound;

//This class is used to play game sound effects.
public class SoundPlayer {
	private static boolean soundEnabled=true;
	public static int gameover=1, linebreak=2, lines4=3, spawn=4;
	private static Sound sgameover, slinebreak, s4lines, sspawn;
	
	public SoundPlayer(){
	}
	
	public static void init(){
		try{//Load sound files.
			sgameover = new Sound("res/sound/gameover.wav");
			slinebreak = new Sound("res/sound/linebreak.wav");
			s4lines = new Sound("res/sound/4lines.wav");
			sspawn = new Sound("res/sound/spawn.wav");
		}catch(Exception e){e.printStackTrace();System.out.println("SoundPlayer.init() failed!");}
	}
	
	public static void setEnabled(boolean enabled){
		soundEnabled=enabled;
	}
	
	public static boolean isEnabled(){
		return soundEnabled;
	}
	
	//Play a sound if sound is enabled. The ID of the sound effect is passed as a parameter.
	public static void playSound(int sound){ 
		if(soundEnabled)	
			switch(sound){
				case 1:sgameover.play(1,0.5f);break;
				case 2:slinebreak.play(1,0.5f);break;
				case 3:s4lines.play(1,0.5f);break;
				case 4:sspawn.play(1,0.5f);break;
				default:break;
			}
		}
	
}
