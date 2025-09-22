package com.bloodiey.GGL;
import java.awt.event.KeyEvent;
import java.io.IOException;



public class GameLoop implements Runnable {
  private GenericRender Bx_generic;
  private Input inp;
  public Input getInp() {
	return inp;
}

private Thread thread;
  private Abstract game;
  private FormClass window;
  public SoundClip StartChime = new SoundClip("/sounds/snd_launch.wav");
  public boolean running = false;
  public double FRAMERATE = 60;
  public double UPDATE_CAP = 1.0/FRAMERATE;
  private int fps;
  public int width = 320;
  public int height = 240;
  public float scale = 2F;
  public String title = "Bloodiey's Night";
  public String iconDir= "assets/icon/icon.png"; // default Icon for program|1|
  public String iconDirAlt= "assets/icon/icon.png"; // default Icon for program
  public boolean isWideScreen = false; // Identificator without Options
  public boolean hasSound = true; // Makes sound Disableable Through GameLoops
  public boolean hasMusic = true; // Makes Music Disableable Through GameLoops
  public GameLoop(Abstract game)
  {
	  StartChime = new SoundClip("/sounds/snd_launch.wav");
	  this.game = game;
	  
  }
  
  
  public void start() {
	StartChime.play();
    this.window = new FormClass(this);
    this.thread = new Thread(this);
    this.Bx_generic = new GenericRender(this);
    this.inp = new Input(this);
    this.thread.run();
  }
  
  public void stop() {
	  Bx_generic.clear(0xff000000);
	  window.setVisible(false);
	  window.Close();
	  running = false;
	  thread.stop();
	  
	  //window.Close();
	  
  }
  public void exit() {
	  Bx_generic.clear(0xff000000);
	  window.setVisible(false);
	  window.Close();
	  running = false;
	  thread.stop();
	  window.Quit();
	  
  }
  
  public void run() {
	 /*
	boolean isDebug;
	if(isDebug  = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0) 
	{
		this.window.SetImage(iconDir);
	}
	else 
	{
		this.window.SetImage(iconDirAlt);
	}
	*/
    this.running = true;
    boolean render = false;
    double firstTime = 0.0D;
    double lastTime = System.nanoTime() / 1.0E9D;
    double passedTime = 0.0D;
    double unprocessedTime = 0.0D;
    double frameTime = 0.0D;
    int frames = 0;
    fps = 0;
    while (this.running) {
    FRAMERATE = FRAMERATE;
    UPDATE_CAP = 1.0/FRAMERATE;
      render = false;
      firstTime = System.nanoTime() / 1.0E9D;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      unprocessedTime += passedTime;
      frameTime += passedTime;
      while (unprocessedTime >= UPDATE_CAP) {
        unprocessedTime -= UPDATE_CAP;
        render = true;
       
        game.update(this, (float)UPDATE_CAP);
        inp.update();
        
        if (frameTime >= 1.0D) {
          frameTime = 0.0D;
          fps = frames;
          frames = 0;
          //System.out.println("FPS :" + fps);
          
        } 
      } 
      if (render) {
        //this.Bx_generic.clear();
        game.render(this, Bx_generic);
        //this.Bx_generic.drawText("FPS: " + fps, 0, 0, 0xffffffff);
        this.window.update();
        frames++;
        continue;
      } 
      try {
        Thread.sleep(1L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } 
    } 
    dispose();
  }
  
  private void dispose() {}
  
  
  public int getWidth() {
    return this.width;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  public float getScale() {
    return this.scale;
  }
  
  public void setScale(float scale) {
    this.scale = scale;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
    window.SetTitle(title);
    window.UpdateForm();
  }
  
  public FormClass getWindow() {
    return this.window;
  }


  public int getFps() {
	return fps;
  }


public SoundClip getStartChime() {
	return StartChime;
}


public void setStartChime(SoundClip startChime) {
	StartChime = startChime;
}


public String getIconDir() {
	return iconDir;
}


public void setIconDir(String iconDir) {
	this.iconDir = iconDir;
}


public double getFRAMERATE() {
	return FRAMERATE;
}


public void setFRAMERATE(double fRAMERATE) {
	FRAMERATE = fRAMERATE;
	UPDATE_CAP = 1.0/FRAMERATE;
	
}


public boolean isWideScreen() {
	return isWideScreen;
}


public void setWideScreen(boolean isWideScreen) {
	this.isWideScreen = isWideScreen;
}


public boolean isHasSound() {
	return hasSound;
}


public void setHasSound(boolean hasSound) {
	this.hasSound = hasSound;
}


public boolean isHasMusic() {
	return hasMusic;
}


public void setHasMusic(boolean hasMusic) {
	this.hasMusic = hasMusic;
}
  
  
}

