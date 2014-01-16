package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfig;


public interface GameModeIF {

	public String name();
	public String launch(DrawImplementIF DI,FieldConfig FC);
	public String update();
	public String touch(Integer X,Integer Y);
	
	public String pointerDown(Integer X,Integer Y);
	public String pointerUp(Integer X,Integer Y);
	
	public String move(Integer X,Integer Y);
	
	
	public String keyPressed(char KeyChar);
}
