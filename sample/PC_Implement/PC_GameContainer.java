package sample.PC_Implement;

import gameLogic.GameContainer;
import gameLogic.mode.PlayMode;
import gameLogic.mode.Result_ClaerMode;
import gameLogic.mode.Result_GameOverMode;
import gameLogic.mode.StartMode;

/**
 * PC(AWT)向けサンプル実装の、ゲームコンテナ
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class PC_GameContainer extends GameContainer{
	
	/**
	 * コンストラクタ
	 * @param Width 画面の幅
	 * @param Height 画面の高さ
	 */
	public PC_GameContainer(Integer Width, Integer Height) {
		super(Width, Height, new PC_DrawImplement());
		
		super.addMode(new StartMode());
		super.addMode(new PlayMode());
		
		super.addMode(new Result_ClaerMode());

		super.addMode(new Result_GameOverMode());
		
		super.changeMode(StartMode.name);
	}
	
	@Override
	public <Graphics> void  update(Graphics panel){
		super.di.updatePanel(panel);
		super.changeMode(super.currentMode.update());
	}

}
