package main;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;

//This class is used to play and manage the background music.
//Music is disabled/removed for the published version since there might be copyright problems.
public class MusicPlayer implements MusicListener{
	private final int NUMBEROFSONGS=6;
	private boolean musicEnabled=true,playing=false;
	private java.util.Random generator = new java.util.Random(System.currentTimeMillis()/1000L);
	private Music music,music1,music2,music3,music4,music5,music6;
	
	public MusicPlayer(){
	}
	
	public void init(){
		try{
			//Music disabled for the published version.
			music1 = null;	
			music2 = null;		
			music3 = null;		
			music4 = null;
			music5 = null;
			music6 = null;
			/*music1 = new Music("res/sound/music1.ogg");	
			music2 = new Music("res/sound/music2.ogg");		
			music3 = new Music("res/sound/music3.ogg");		
			music4 = new Music("res/sound/music4.ogg");
			music5 = new Music("res/sound/music5.ogg");
			music6 = new Music("res/sound/music6.ogg");*/
		}catch(Exception e){e.printStackTrace();System.out.println("MusicPlayer.init() failed!");}
	}
	
	public void setEnabled(boolean enabled){
		musicEnabled=enabled;
	}
	
	public boolean isEnabled(){
		return musicEnabled;
	}
	
	//When this function is called, it starts playing a random song from the library if musicEnabled is true. 
	public void play(){
		if(musicEnabled){
			int random=generator.nextInt(NUMBEROFSONGS - 1 + 1) + 1;
			switch(random){
				case 1:
					music=music1;
					break;
				case 2:
					music=music2;
					break;
				case 3:
					music=music3;
					break;
				case 4:
					music=music4;
					break;
				case 5:
					music=music5;
					break;
				case 6:
					music=music6;
					break;
			}	
			music.addListener(this);
			music.play(1,0);
			playing=true;
			music.fade(5000, 0.4f, false); //Bring up the volume slowly
		}
	}

	//If music was playing, stop it.
	public void stop(){
		if(playing){
			music.fade(5000,0,true);//Fade out the volume slowly
			playing=false;
		}
	}
	
	//Inherited abstract method, just play a new song.
	public void musicEnded(Music arg0) {
		play();
	}
	
	//Inherited abstract method.
	public void musicSwapped(Music arg0, Music arg1) {	
	}
	
}
