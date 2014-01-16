package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfigBean;

/**
 * 初期表示のモード
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class StartMode implements GameModeIF{
	/**
	 * 本モードの名前
	 */
	final public static String name = "StartMode";

	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di;
	
	/**
	 *全モードで共有される設定情報 
	 */
	protected FieldConfigBean fc = new FieldConfigBean();
	
	/**
	 * スタートボタンのBean
	 */
	protected ButtonBean startButton = new ButtonBean();
	

	@Override
	public String name() {
		return name;
	}
	
	@Override
	public String launch(DrawImplementIF DI, FieldConfigBean FC) {
		this.di = DI;
		this.fc = FC;
		

		setupStartButton();
		return null;
	}

	/**
	 * スタートボタンのBeanを設定
	 */
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
