package com.bloodiey.game;

import com.bloodiey.GGL.Abstract;
import com.bloodiey.GGL.GameLoop;
import com.bloodiey.GGL.GenericRender;

public class State extends Abstract {

	@Override
	public void update(GameLoop gc, float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameLoop gc, GenericRender r) {
		// TODO Auto-generated method stub
		r.drawText("FPS: " + gc.getFps(), 0, 0, 0xffffffff);
	}

}
