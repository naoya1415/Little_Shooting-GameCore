package gameLogic.mode;

public class Result_GameOverMode extends Result_ClaerMode{


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
