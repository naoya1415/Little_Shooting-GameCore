package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfigBean;


/**
 * 画面の表示内容や動作を規定するモードのインタフェース<br>
 * Webで言えばページのようなもの
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
/**
 * @author n-dolphin
 *
 */
public interface GameModeIF {

	/**
	 * モードの名前<br>
	 * モードの呼び出し時に使用、重複は許されない。
	 * @return
	 */
	public String name();
	
	/**
	 * モードが実際に表示される前に実行されるメソッド<br>
	 * @param DI 実行環境に依存した描画処理の実装
	 * @param FC 全モードで共有される設定情報
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String launch(DrawImplementIF DI,FieldConfigBean FC);
	
	/**
	 * 画面内容のアップデート
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String update();
	
	/**
	 * クリック時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String touch(Integer X,Integer Y);
	
	/**
	 * マウスダウン時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String pointerDown(Integer X,Integer Y);
	
	/**
	 * マウスアップ時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String pointerUp(Integer X,Integer Y);
	
	/**
	 * 移動時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String move(Integer X,Integer Y);
	
	/**
	 * キーボード押下時の処理
	 * @param KeyChar 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String keyPressed(char KeyChar);
}
