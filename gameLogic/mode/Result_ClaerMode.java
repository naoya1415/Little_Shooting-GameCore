package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfig;
import gameLogic.mode.Bean.TextBean;



public class Result_ClaerMode implements GameModeIF{

	private DrawImplementIF di;
	
	FieldConfig fc = new FieldConfig();
	
	TextBean resultText = new TextBean();
	ButtonBean restartButton = new ButtonBean();
	
	
	final public static String name = "Resul_ClearMode";

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
		setupResultText();
		setupRestartButton();
	}
	
	 void setupResultText(){
		/*スタートボタンのコンフィグ*/
		//TODO:多言語化その他ように、まとめられるように
		resultText.setText("clear!!");
		resultText.setLocation((int)(fc.screenWidth/5),fc.screenHeight/3);
		resultText.setFontSize(fc.screenHeight/8);
		resultText.setColor(255, 0, 0, 255);
	}
	 
	void setupRestartButton(){
		/*スタートボタンのコンフィグ*/
		//TODO:多言語化その他ように、まとめられるように
		restartButton.setText("restart");
		restartButton.setLocation((int)(fc.screenWidth/7),fc.screenHeight/2);
		restartButton.setFontSize(fc.screenHeight/8);
		restartButton.setColor(255, 0, 0, 255);
	}
	
	@Override
	public String update() {
		di.setBackground();
		di.drawText(resultText);
		di.drawButton(restartButton);
		return null;
	}

	@Override
	public String touch(Integer X, Integer Y) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String move(Integer X, Integer Y) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String pointerDown(Integer X, Integer Y) {
		if(restartButton.isInside(X, Y)){
			restartButton.setColor(0, 255, 0, 255);
		}
		return null;
	}

	@Override
	public String pointerUp(Integer X, Integer Y) {

		if(restartButton.isInside(X, Y)){
			return StartMode.name;
					
		}else{
			restartButton.setColor(255, 0, 0, 255);
		}
		return null;
	}

	@Override
	public String keyPressed(char KeyChar) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


}
