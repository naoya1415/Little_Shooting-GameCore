package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfig;

public class StartMode implements GameModeIF{

	private DrawImplementIF di;
	
	FieldConfig fc = new FieldConfig();
	
	ButtonBean startButton = new ButtonBean();
	
	final public static String name = "StartMode";

	@Override
	public String name() {
		return name;
	}
	
	@Override
	public String launch(DrawImplementIF DI, FieldConfig FC) {
		this.di = DI;
		this.fc = FC;
		
		init();
		return null;
	}

	void init(){
		setupStartButton();
	}
	
	void setupStartButton(){
		/*スタートボタンのコンフィグ*/
		//TODO:多言語化その他ように、まとめられるように
		startButton.setText("start");
		startButton.setLocation((int)(fc.screenWidth/4.5),fc.screenHeight/2);
		startButton.setFontSize(fc.screenHeight/8);
		startButton.setColor(255, 0, 0, 255);
	}
	
	@Override
	public String update() {
		di.setBackground();
		di.drawButton(startButton);
		
		return null;
	}

	@Override
	public String touch(Integer X, Integer Y) {
	
		return null;
	}

	@Override
	public String move(Integer X, Integer Y) {
		return null;
	}

	
	@Override
	public String pointerDown(Integer X, Integer Y) {		
		if(startButton.isInside(X, Y)){	
			startButton.setColor(0, 255, 0, 255);
		}
		return null;
	}

	@Override
	public String pointerUp(Integer X, Integer Y) {
		
		if(startButton.isInside(X, Y)){
			return PlayMode.name;
					
		}else{
			startButton.setColor(255, 0, 0, 255);
		}
		return null;
	}

	@Override
	public String keyPressed(char KeyChar) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
