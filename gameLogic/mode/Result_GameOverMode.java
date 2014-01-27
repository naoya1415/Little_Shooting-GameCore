package gameLogic.mode;

/**
 * ゲームオーバ時に表示されるモード
 * @author NaoyaIchikawa
 * @version 1.00 2014/01/27
 */
public class Result_GameOverMode extends Result_ClearMode{

	/**
	 * 本モードの名前
	 */
	final public static String name = "Result_GameOverMode";
	
	@Override
	public String getName() {
		return Result_GameOverMode.name;
	}
	@Override
	protected void setupResultText(){
		super.setupResultText();
		
		resultText.setText("GameOver");
		resultText.setStartPosition(null,fc.screenHeight/3- resultText.getTextHeight());
		resultText.setHorizonalPosition("center");
	}
	
}
