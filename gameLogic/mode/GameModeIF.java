package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfigBean;


/**
 * 画面(Mode)の表示内容や動作を規定するモードのインタフェース<br>
 * Webで言えばページのようなもの
 * @author Naoya Ichikawa
 * @version 1.10 2014/01/27
 */
public interface GameModeIF {

	
	//--------------------------------------------------------------------------------
	//モード管理用メソッド
	//GameContainer以下で使用し、基本的にEntryPointからは使用されない。
	//--------------------------------------------------------------------------------
	
	/**
	 * モードの名前<br>
	 * モードの呼び出し時に使用、重複は許されない。
	 * @return
	 */
	public String getName();
	
	/**
	 * モードが実際に表示される前に実行されるメソッド<br>
	 * @param DI 実行環境に依存した描画処理の実装
	 * @param FC 全モードで共有される設定情報
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String open(DrawImplementIF DI,FieldConfigBean FC)throws Exception;
	

	/**
	 * 他のモードへ切り替わる際に呼び出される
	 * @return
	 * @throws Exception 
	 */
	public String close()throws Exception;
	

	
	
	//--------------------------------------------------------------------------------
	//イベント処理用メソッド
	//EntryPointからは呼び出される
	//--------------------------------------------------------------------------------
	
	/**
	 * 画面内容のアップデート
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String update()throws Exception;
	
	/**
	 * クリック時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String touch(Integer X,Integer Y)throws Exception;
	
	/**
	 * マウスダウン時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String pointerDown(Integer X,Integer Y)throws Exception;
	
	/**
	 * マウスアップ時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String pointerUp(Integer X,Integer Y)throws Exception;
	
	/**
	 * 移動時の処理
	 * @param X 
	 * @param Y 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String move(Integer X,Integer Y)throws Exception;
	
	/**
	 * キーボード押下時の処理
	 * @param KeyChar 
	 * @return 遷移先モードの名前(移動しない際はnullも可能)
	 */
	public String keyPressed(char KeyChar)throws Exception;
}
