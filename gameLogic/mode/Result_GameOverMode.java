package gameLogic.mode;

/**
 * ゲームオーバ時に表示されるモード
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class Result_GameOverMode extends Result_ClaerMode{

	/**
	 * 本モードの名前
	 */
	final public static String name = "Result_GameOverMode";
	
	@Override
	public String name() {
		return Result_GameOverMode.name;
	}
	@Override
	void setupResultText(){
		super.setupResultText();
		
		resultText.setText("GameOver!!");
		resultText.setLocation((int)(fc.screenWidth/10),resultText.getY());
		resultText.setFontSize(fc.screenHeight/10);
		
	}
	
}
