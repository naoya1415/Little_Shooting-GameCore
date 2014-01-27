package sample.PC_Implement;

import gameLogic.GameContainer;
import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.PlayMode;
import gameLogic.mode.Result_ClearMode;
import gameLogic.mode.Result_GameOverMode;
import gameLogic.mode.StartMode;
import gameLogic.mode.Bean.FieldConfigBean;

/**
 * PC(AWT)向けサンプル実装の、ゲームコンテナ
 * @author Naoya Ichikawa
 * @version 1.10 2014/01/27
 */
public class PC_GameContainer extends GameContainer{

	/**
	 * コンストラクタ
	 * @param Width 画面の幅
	 * @param Height 画面の高さ
	 */
	public PC_GameContainer(Integer Width, Integer Height) {
		super(Width,Height,(DrawImplementIF)new PC_DrawImplement(),new FieldConfigBean());
		
		super.addMode(new StartMode());
		super.addMode(new PlayMode());
		super.addMode(new Result_ClearMode());
		super.addMode(new Result_GameOverMode());
		
		super.changeMode(StartMode.name);
	}

	
	@Override
	public <Graphics> void  update(Graphics panel) {
		super.update(panel);
	}

}
