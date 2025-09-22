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

public class MainMenuState extends State {
	
	public TiledImage sigma;
	public SoundClip pax;
	public SoundClip choose;
	public SoundClip play;
	public SoundClip cantplay;
	public Image bga;
	public OptionsState options;
	public String version = "indev-v0.3";
	float bgaX=-181,bgaY=0;
	public Image bgb; 
	float bgbX=-181+(181+115),bgbY=270;
	public Image bgc; 
	float bgcX=-181-(181+256),bgcY=270;
	public float bloodieyX=480/2-64,bloodieyY=270/2-64;
	float i= 0;
	int curselect = 0;
	int dir = 4;
	int frame = 0;
	float offsetifnotwide = 160/2;
	public MainMenuState() {
		sigma = new TiledImage("/sprites/bloodiey_isometric.png",128,128);
		pax = new SoundClip("/music/bgm/SillyCat.mid");
		choose = new SoundClip("/sounds/snd_beep.wav");
		play = new SoundClip("/sounds/snd_done.wav");
		cantplay = new SoundClip("/sounds/snd_error.wav");
		bga = new Image("/sprites/bg/Knocker.png");
		bgb = new Image("/sprites/bg/Knocker.png");
		bgc = new Image("/sprites/bg/Knocker.png");
		
	}
	
	public void update(GameLoop gc, float dt) {
		// need 4 direction
		/*
		if(gc.getInp().isKeyDown(KeyEvent.VK_J)) 
		{
			if (dir < 7) 
			{
				dir += 1;
			}
			else 
			{
				dir = 0;
			}
				
				
		}
		if(gc.getInp().isKey(KeyEvent.VK_K)) 
		{
			if (frame < 15) 
			{
				frame += 1;
			}
			else 
			{
				frame = 0;
			}
				
				
		}
		*/
		/*
		if(gc.getInp().isKey(KeyEvent.VK_UP)) 
		{
			bgaY -= 1;
			
		}
		if(gc.getInp().isKey(KeyEvent.VK_DOWN)) 
		{
			bgaY += 1;
			
		}
		if(gc.getInp().isKey(KeyEvent.VK_LEFT)) 
		{
			bgaX -= 1;
			
		}
		if(gc.getInp().isKey(KeyEvent.VK_RIGHT)) 
		{
			bgaX += 1;
		}
		*/
		float mult = 1f;
		float addconY = 0.91f*mult;
		float addconX = 1f*mult;
		bgaX -= addconX;
		bgaY -= addconY;
		bgbX -= addconX;
		bgbY -= addconY;
		bgcX -= addconX;
		bgcY -= addconY;
		
		int maxselect = 2;
		
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
				if(gc.hasSound) {
					play.play();
				}
				JOptionPane.showMessageDialog(null,"Coming Soon");
			}
			if(curselect == 1) 
			{
				if(gc.hasSound) {
					play.play();
				}
			}
			if(curselect == 2) 
			{
				gc.getWindow().dispatchEvent(new WindowEvent(gc.getWindow(), WindowEvent.WINDOW_CLOSING));
				gc.exit();
				gc.stop();
				//cantplay.play();
				
			}
		}
		
		if(bgaY<=-270) 
		{
			bgaY = 270;
			bgaX = -181+(181+115);
		}
		if(bgbY<=-270) 
		{
			bgbY = 270;
			bgbX = -181+(181+115);
		}
		if(bgcY<=0) 
		{
			bgcY = 270;
			bgcX = -181-(181+256);
		}
		
		//-181
		//0
		if (i < 16) 
		{
			i += 1 * dt *24*mult;
			frame = (int)i;	
			
		}
		else 
		{
			i = 0;
			frame = (int)i;	
		}
		//System.out.println(i);
		if(gc.hasMusic) {
			if(!pax.isRunning()) 
			{
				pax.play();
			}
		}
		super.update(gc, dt);
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		
		
		r.clear(0xff000000);
		if(gc.isWideScreen) {
			r.drawImage(bga, (int)bgaX, (int)bgaY);
			r.drawImage(bgb, (int)bgbX, (int)bgbY);
			r.drawImage(bgc, (int)bgcX, (int)bgcY);
		}
		else {
			r.drawImage(bga, (int)bgaX-(int)offsetifnotwide, (int)bgaY);
			r.drawImage(bgb, (int)bgbX-(int)offsetifnotwide, (int)bgbY);
			r.drawImage(bgc, (int)bgcX-(int)offsetifnotwide, (int)bgcY);
		}
		/*
		r.drawText("BGAX: "+ bgaX, 0, 13, 0xffff0000);
		r.drawText("BGAY: "+ bgaY, 0, 13+13, 0xff0000ff);
		r.drawText("BGBX: "+ bgbX, 0, 13+13+13, 0xffff0000);
		r.drawText("BGBY: "+ bgbY, 0, 13+13+13+13, 0xff0000ff);
		r.drawText("BGCX: "+ bgcX, 0, 13+13+13+13+13, 0xffff0000);
		r.drawText("BGCY: "+ bgcY, 0, 13+13+13+13+13+13, 0xff0000ff);
		*/
		//r.drawText("Cur Selected: "+ curselect, 0, 13, 0xffff0000);
		r.drawText("Version: "+ version,0,gc.getHeight()-6,0xffFFDE59);
		r.drawText("BLOODIEY'S NIGHT",gc.getWidth()-100,32,0xffE4080A);
		
		if (curselect == 0) 
		{
			r.drawText("NEW GAME",gc.getWidth()-100,32+32+16,0xffFFDE59);
		}
		else
		{
			r.drawText("NEW GAME",gc.getWidth()-100,32+32+16,0xffffffff);
		}
		if (curselect == 1) 
		{
			r.drawText("LOAD GAME",gc.getWidth()-100,32+32+32,0xffFFDE59);
		}
		else
		{
			r.drawText("LOAD GAME",gc.getWidth()-100,32+32+32,0xffffffff);
		}
		if (curselect == 2) 
		{
			r.drawText("EXIT",gc.getWidth()-100,32+32+32+16,0xffFFDE59);
		}
		else
		{
			r.drawText("EXIT",gc.getWidth()-100,32+32+32+16,0xffffffff);
		}
		
		if(gc.isWideScreen) {
			r.drawTiledImage(sigma, (int)bloodieyX, (int)bloodieyY, frame, dir);
		}
		else
		{
			r.drawTiledImage(sigma, (int)bloodieyX-(int)offsetifnotwide, (int)bloodieyY, frame, dir);
		}
		if(gc.getFps() < 15) {
			r.drawText("FPS: "+gc.getFps(), 0, 0, 0xffff0000);
		}
		else 
		{
			r.drawText("FPS: "+gc.getFps(), 0, 0, 0xffffffff);
		}
		super.render(gc, r);
		
	}
	
	public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// Get the display mode of the screen
        DisplayMode displayMode = gd.getDisplayMode();
        
        // Retrieve the refresh rate
        int refreshRate = displayMode.getRefreshRate();
		GameLoop gc = new GameLoop(new MainMenuState());
		OptionsState options = new OptionsState();
		int resX = 480,resY = 270;
		float scale = 2f;
		gc.setWidth(resX);
		gc.setHeight(resY);
		gc.setScale(scale);
		gc.setWideScreen(true);
		gc.setHasMusic(true);
		gc.setHasSound(true);
		if(options.vsyncEnabled) 
		{
			gc.setFRAMERATE(refreshRate);
		}
		else 
		{
			gc.setFRAMERATE(options.framerate);
		}
		//gc.getWindow().SetTitle("Bloodiey's Night");
		gc.start();
	}

}
