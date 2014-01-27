package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfigBean;

/**
 * 初期表示のモード
 * @author NaoyaIchikawa
 * @version 1.00 2014/01/27
 */
public class StartMode implements GameModeIF{
	/**
	 * 本モードの名前
	 */
	final public static String name = "StartMode";

	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di = null;
	
	/**
	 *全モードで共有される設定情報 
	 */
	protected FieldConfigBean fc = null;
	
	/**
	 * スタートボタンのBean
	 */
	protected ButtonBean startButton = new ButtonBean();
	

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String open(DrawImplementIF DI, FieldConfigBean FC)throws Exception {
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
		startButton.setText("start");
		startButton.setStartPosition(null,fc.screenHeight/2- startButton.getTextHeight());
		startButton.setHorizonalPosition("center");
	}
	
	@Override
	public String update() {
		di.setBackground(FieldConfigBean.black);
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
			startButton.setColor(FieldConfigBean.DefaultPressedButtonColor);
		}
		return null;
	}

	@Override
	public String pointerUp(Integer X, Integer Y) {
		startButton.setColor(FieldConfigBean.DefaultButtonColor);
		
		if(startButton.isInside(X, Y)){
			return PlayMode.name;
		}
		return null;
	}

	@Override
	public String keyPressed(char KeyChar) throws Exception{
		if(KeyChar=='e'){
			throw new Exception();
		}
		return null;
	}

	@Override
	public String close() throws Exception {
		return null;
	}

}
