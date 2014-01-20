package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfigBean;
import gameLogic.mode.Bean.TextBean;



/**
 * ゲームクリア時に表示されるモード
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class Result_ClaerMode implements GameModeIF{

	/**
	 * 本モードの名前
	 */
	final public static String name = "Resul_ClearMode";
	
	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di;
	
	/**
	 *全モードで共有される設定情報 
	 */
	protected FieldConfigBean fc = new FieldConfigBean();
	
	/**
	 * ゲームの結果によって表示されるテキストのBean
	 */
	protected TextBean resultText = new TextBean();
	
	/**
	 * リスタートボタンのBean
	 */
	protected ButtonBean restartButton = new ButtonBean();
	
	
	@Override
	public String name() {
		return name;
	}
	
	@Override
	public String launch(DrawImplementIF DI, FieldConfigBean FC) {
		this.di = DI;
		this.fc = FC;

		setupResultText();
		setupRestartButton();
		return null;
	}

	 /**
	 * ゲームの結果によって表示されるテキストのBeanを設定
	 */
	void setupResultText(){
		/*スタートボタンのコンフィグ*/
		//TODO:多言語化その他ように、まとめられるように
		resultText.setText("clear!!");
		resultText.setLocation((int)(fc.screenWidth/5),fc.screenHeight/3);
		resultText.setFontSize(fc.screenHeight/8);
		resultText.setColor(255, 0, 0, 255);
	}
	 
	
	/**
	 * リスタートボタンのBeanを設定
	 */
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
		di.setBackground(fc.black);
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
