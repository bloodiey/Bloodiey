package com.bloodiey.pencylEngine;

import com.bloodiey.GGL.GameLoop;
import com.bloodiey.GGL.GenericRender;
import com.bloodiey.GGL.Image;

public class Entity extends Sprite
{

	public Entity(String objName, Image imges, Vector2 Pos, Vector2 Siz) {
		super(objName, imges, Pos, Siz);
	}
	public void update(GameLoop gc, float dt) {
		// TODO Auto-generated method stub
		super.position.x = super.position.x + (float)super.img.getW() / 2;
		super.position.y = super.position.y + (float)super.img.getH() / 2;
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		// TODO Auto-generated method stub
		
	}
	public void move(Vector2 moveAdd) {
		super.position.add(moveAdd.x, moveAdd.y);
	}
}
