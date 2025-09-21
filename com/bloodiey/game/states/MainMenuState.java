package com.bloodiey.game.states;

import com.bloodiey.GGL.GameLoop;
import com.bloodiey.GGL.GenericRender;
import com.bloodiey.GGL.SoundClip;
import com.bloodiey.GGL.TiledImage;
import com.bloodiey.game.State;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuState extends State {
	
	public TiledImage sigma;
	public SoundClip pax;
	public float bloodieyX=480/2-64,bloodieyY=270/2-64;
	float i= 0;
	int dir = 4;
	int frame = 0;
	public MainMenuState() {
		sigma = new TiledImage("/sprites/bloodiey_isometric_less.png",128,128);
		pax = new SoundClip("/music/bgm/SillyCat.mid");
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
		
		
		
		
		if (i < 16) 
		{
			i += 1 * dt *24;
			frame = (int)i;	
			
		}
		else 
		{
			i = 0;
			frame = (int)i;	
		}
		//System.out.println(i);
		
		if(!pax.isRunning()) 
		{
			pax.play();
		}
		super.update(gc, dt);
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		
		
		r.clear(0xff000000);
		//r.drawText("Direction: "+ dir, 0, 32, 0xffffffff);
		//r.drawText("Frame: "+ frame, 0, 64, 0xffffffff);
		r.drawTiledImage(sigma, (int)bloodieyX, (int)bloodieyY, frame, dir);
		super.render(gc, r);
		
		
		
		
	}
	
	public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// Get the display mode of the screen
        DisplayMode displayMode = gd.getDisplayMode();
        
        // Retrieve the refresh rate
        int refreshRate = displayMode.getRefreshRate();
		GameLoop gc = new GameLoop(new MainMenuState());
		int resX = 480,resY = 270;
		float scale = 2f;
		gc.setWidth(resX);
		gc.setHeight(resY);
		gc.setScale(scale);
		gc.setFRAMERATE(refreshRate);
		//gc.getWindow().SetTitle("Bloodiey's Night");
		gc.start();
	}

}
