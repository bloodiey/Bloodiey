package com.bloodiey.game.entities;

import com.bloodiey.GGL.GameLoop;
import com.bloodiey.GGL.GenericRender;
import com.bloodiey.GGL.Image;
import com.bloodiey.pencylEngine.Entity;
import com.bloodiey.pencylEngine.Vector2;

public class Spikes extends Entity {
	public int jPosition = 0;
	public int height = 1;
	public int jSize = 4;
	public Spikes(String objName, Image imges, Vector2 Pos, Vector2 Siz) {
		
		super(objName, imges, Pos, Siz);
		// TODO Auto-generated constructor stub
	}
	
	public void update(GameLoop gc, float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		// TODO Auto-generated method stub
		
	}
	public void drawSpike(GameLoop gc, GenericRender r) 
	{
		r.drawImage(super.img, (int)super.position.x-super.img.getW() / 2, (int)super.position.y-super.img.getH() / 2);
	}

}
