package com.bloodiey.game.states;

import com.bloodiey.GGL.GameLoop;
import com.bloodiey.GGL.GenericRender;
import com.bloodiey.GGL.SoundClip;
import com.bloodiey.GGL.TiledImage;
import com.bloodiey.game.State;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import com.bloodiey.GGL.Image;



public class OptionsState extends State {
	int maxselect = 6;
	public boolean musplayed = false;
	public Image bga;
	public float bgaX=0,bgaY=0;
	public Image bgb;
	public float bgbX=-160,bgbY=0;
	public SoundClip mus;
	public SoundClip mus1;
	public SoundClip choose;
	public SoundClip play;
	public SoundClip cantplay;
	public SoundClip err;
	MainMenuState ms; 
	public boolean musicEnabled = true;
	public boolean soundEnabled = true;
	public boolean vsyncEnabled = true;
	public double framerate = 30;
	public boolean WideScreen = true;
	public boolean isOnSettings = true;
	public int curPosition = 0;
	int refreshRate = 60;
	int curselect = 0;
	int resX = 480,resY = 270;
	float scale = 2f;
	boolean musicCheck = false;
	public boolean isOnMenu = true;
	public OptionsState() {
		mus = new SoundClip("/music/bgm/Settings.mid");
		mus1 = new SoundClip("/music/bgm/Settings.mid");
		choose = new SoundClip("/sounds/snd_beep.wav");
		play = new SoundClip("/sounds/snd_done.wav");
		cantplay = new SoundClip("/sounds/snd_error.wav");
		err = new SoundClip("/sounds/snd_cant.wav");
		bga = new Image("/sprites/bg/configbg.png");
		bgb = new Image("/sprites/bg/configbg.png");
		ms = new MainMenuState();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// Get the display mode of the screen
        DisplayMode displayMode = gd.getDisplayMode();
        
        // Retrieve the refresh rate
        refreshRate = displayMode.getRefreshRate();
	}
	
	
	
	public void update(GameLoop gc, float dt) {
		if(isOnSettings) {
			// MUSIC HANDLER
			/*
			if(musicCheck == false) {
				soundEnabled = gc.hasSound;
				musicEnabled = gc.hasMusic;
				musicCheck = true;
			}
			*/
			gc.getWindow().SetTitle("Initial Configuration");
			if(gc.hasMusic == false) 
			{
				mus1.stop();
				mus.stop();
			}
			// SOUND HANDLER		
			if(gc.hasSound == false) 
			{
				play.stop();
				cantplay.stop();
				err.stop();
				choose.stop();
			}
			
			// widescreen HANDLER
			if(WideScreen == true) {
				resX = 480;
				resY = 270;
			}
			else 
			{
				resX = 360;
				resY = 270;
			}
			
			// MENU HANDLER
			
			
			if(gc.getInp().isKeyDown(KeyEvent.VK_DOWN)) 
			{
				if(gc.hasSound) {
					choose.play();
				}
				if (curselect >= maxselect) 
				{
					curselect = 0;
				}
				else
				{
					curselect += 1;
				}
				
			}
			if(gc.getInp().isKeyDown(KeyEvent.VK_UP)) 
			{
				if(gc.hasSound) {
					choose.play();
				}	
				if (curselect <= 0) 
				{
					curselect = maxselect;
				}
				else
				{
					curselect -= 1;
				}
				
			}
			if(gc.getInp().isKeyDown(KeyEvent.VK_Z) || gc.getInp().isKeyDown(KeyEvent.VK_ENTER)) 
			{
				if(curselect == 0) 
				{
					if(musicEnabled == true) 
					{
						musicEnabled = false;
					}
					else 
					{
						musicEnabled = true;
					}
					if(gc.hasSound) {
						play.play();
					}
					
				}
				if(curselect == 1) 
				{
					if(soundEnabled == true) 
					{
						soundEnabled = false;
					}
					else 
					{
						soundEnabled = true;
					}
					if(gc.hasSound) {
						play.play();
					}
				}
				if(curselect == 2) 
				{
					if (vsyncEnabled == true) {
						vsyncEnabled = false;
						gc.setFRAMERATE(framerate);
					}
					else
					{
						gc.setFRAMERATE(refreshRate);
						vsyncEnabled = true;
					}
					if(gc.hasSound) {
						play.play();
					}
				}
				if(curselect == 3) 
				{
					if (vsyncEnabled == false) {
						framerate += 15;
						if(framerate > 120) {
							framerate = 15;
							
						}
						
						if(gc.hasSound) {
							play.play();
						}
						gc.setFRAMERATE(framerate);
					}
					else 
					{
						if(gc.hasSound) {
							err.play();
						}
					}
					
				}
				if(curselect == 4) 
				{
					scale += 0.5f;
					if(scale > 4f) 
					{
						scale = 4f;
					}
						
					if(gc.hasSound) {
						play.play();
					}
				}
				if(curselect == 5) 
				{
					if(WideScreen == true) 
					{
						WideScreen = false;
					}
					else 
					{
						WideScreen = true;
					}
					if(gc.hasSound) {
						play.play();
					}
				}
				if(curselect == maxselect) 
				{
					if(gc.hasSound) {
						cantplay.play();
					}
					
					GameLoop gc2 = new GameLoop(new MainMenuState());
					gc2.setWidth(resX);
					gc2.setHeight(resY);
					gc2.setScale(scale);
					if(vsyncEnabled == true) {
						gc2.setFRAMERATE(refreshRate);
					}
					else 
					{
						gc2.setFRAMERATE(framerate);
					}
					gc2.setWideScreen(WideScreen);
					gc2.setHasMusic(musicEnabled);
					gc2.setHasSound(soundEnabled);
					mus1.stop();
					mus.stop();
					play.stop();
					cantplay.stop();
					err.stop();
					choose.stop();
					isOnSettings = false;
					gc.getWindow().Close();
					//gc.stop();
					gc2.start();
					
				}
			}
			if(gc.getInp().isKeyDown(KeyEvent.VK_X) || gc.getInp().isKeyDown(KeyEvent.VK_BACK_SPACE )) 
			{
				if(curselect == 3) 
				{
					if (vsyncEnabled == false) {
						framerate -= 15;
						if(framerate < 15) {
							framerate = 120;
							
						}
		
						
						if(gc.hasSound) {
							cantplay.play();
						}
						gc.setFRAMERATE(framerate);
					}
					else 
					{
						if(gc.hasSound) {
							err.play();
						}
					}
				}
				if(curselect == 4) 
				{
					scale -= 0.5f;
					if(scale < 1f) 
					{
						scale = 1f;
					}
						
					if(gc.hasSound) {
						play.play();
					}
				}
			}
			
			int addX = 1;
			bgaX += addX;
			bgbX += addX;
			
			if(bgaX > 160) 
			{
				bgaX = -160;
			}
			if(bgbX > 160) 
			{
				bgbX = -160;
			}
			// MUSIC LOOP
			
			if(gc.hasMusic == true) 
			{
				if(mus.isRunning() == false && musplayed == false) 
				{
					
					
					if(!mus.isRunning()&& musplayed == false && mus.ended == false) 
					{
						mus.play();
					}
					else 
					{
						musplayed = true;
					}
				}
				else if(musplayed == true)
				{
					if(!mus1.isRunning()) 
					{
						mus1.play();
					}
				}
			}
			
		
		}
		super.update(gc, dt);
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		r.clear(0xff060270);
		//r.colorswitch();
		
		r.drawImage(bga, (int)bgaX, (int)bgaY);
		r.drawImage(bgb, (int)bgbX, (int)bgbY);
		r.drawText("OPTIONS",0,0,0xffBFD641);
		
		//r.drawText("X A: "+ bgaX,0,gc.getHeight()-12,0xffBFD641);
		//r.drawText("X B: "+ bgbX,0,gc.getHeight()-6,0xffBFD641);
		r.drawText("Version: "+ ms.version,0,gc.getHeight()-6,0xffFFDE59);
		r.drawText("X/BACKSPACE: Back/Minus",0,gc.getHeight()-12,0xffBFD641);
		r.drawText("Z/ENTER: Select/Plus",0,gc.getHeight()-18,0xffBFD641);
		if (curselect == 0) 
		{
			r.drawText("Music: "+musicEnabled,0+3,16,0xffFFDE59);
		}
		else
		{
			r.drawText("Music: "+musicEnabled,0,16,0xffffffff);
		}
		if (curselect == 1) 
		{
			r.drawText("Sound: "+soundEnabled,0+3,16+8,0xffFFDE59);
		}
		else
		{
			r.drawText("Sound: "+soundEnabled,0,16+8,0xffffffff);
		}
		if (curselect == 2) 
		{
			r.drawText("VSYNC: "+vsyncEnabled,0+3,16+8*2,0xffFFDE59);
		}
		else
		{
			r.drawText("VSYNC: "+vsyncEnabled,0,16+8*2,0xffffffff);
		}
		if (curselect == 3) 
		{
			r.drawText("FRAMERATE: "+framerate,0+3,16+8*3,0xffFFDE59);
		}
		else
		{
			r.drawText("FRAMERATE: "+framerate,0,16+8*3,0xffffffff);
		}
		if (curselect == 4) 
		{
			r.drawText("WINDOW SCALE: "+scale,0+3,16+8*4,0xffFFDE59);
		}
		else
		{
			r.drawText("WINDOW SCALE: "+scale,0,16+8*4,0xffffffff);
		}
		if (curselect == 5) 
		{
			r.drawText("WideScreen: "+WideScreen,0+3,16+8*5,0xffFFDE59);
		}
		else
		{
			r.drawText("WideScreen: "+WideScreen,0,16+8*5,0xffffffff);
		}
		if (curselect == maxselect) 
		{
			r.drawText("START GAME",0+3,16+8*9,0xffFFDE59);
		}
		else
		{
			r.drawText("START GAME",0,16+8*9,0xffffffff);
		}
		
		super.render(gc, r);
	}
	public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// Get the display mode of the screen
        DisplayMode displayMode = gd.getDisplayMode();
        
        //Initial Config
        if (args.length > 0) {
            System.out.println("Arguments passed:");
            for (String arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("No arguments provided.");
        }
        // Retrieve the refresh rate
        int refreshRate = displayMode.getRefreshRate();
		GameLoop gc = new GameLoop(new OptionsState());
		int resX = 160,resY = 144;
		float scale = 2f;
		gc.setWidth(resX);
		gc.setHeight(resY);
		gc.setScale(scale);
		gc.setFRAMERATE(refreshRate);
		System.out.println("Current Arguments lenght: " + args.length);
		if(args.length == 2) 
		{
			int arg1 = Integer.parseInt(args[0]);
			int arg2 = Integer.parseInt(args[1]);
			if(arg1 == 1) 
			{
				System.out.println("MUSIC ENABLED, ENJOY THE SECRET SONG.");
				gc.setHasMusic(true);
			}
			else 
			{
				System.out.println("MUSIC DISABLED BY DEFAULT PLEASE USE '1' IN ARGUMENT 1.");
				gc.setHasMusic(false);
			}
			if(arg2 == 1) 
			{
				System.out.println("SOUND ENABLED, YOU'LL HEAR EVERY SOUND.");
				gc.setHasSound(true);
			}
			else 
			{
				System.out.println("SOUND DISABLED BY DEFAULT PLEASE USE '1' IN ARGUMENT 2.");
				gc.setHasSound(false);
			}
		}
		else 
		{
			gc.setHasMusic(false);
			gc.setHasSound(false);
			System.out.println("2 arguments are needed Current lenght: " + args.length);
		}
		
		//System.out.println("SOUND: "+gc.isHasSound());
		//System.out.println("MUSIC: "+gc.isHasMusic());
		//gc.getWindow().SetTitle("Bloodiey's Night");
		gc.start();
	}

	public boolean isMusicEnabled() {
		return musicEnabled;
	}

	public void setMusicEnabled(boolean musicEnabled) {
		this.musicEnabled = musicEnabled;
	}

	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	public boolean isVsyncEnabled() {
		return vsyncEnabled;
	}

	public void setVsyncEnabled(boolean vsyncEnabled) {
		this.vsyncEnabled = vsyncEnabled;
	}

	public double getFramerate() {
		return framerate;
	}

	public void setFramerate(double framerate) {
		this.framerate = framerate;
	}
	
}
