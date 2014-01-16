package gameLogic.DrawImplement;

import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfigBean;
import gameLogic.mode.Bean.TextBean;


/**
 * 実行環境依存の描画処理実装についてのインタフェース
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public interface DrawImplementIF {
	
	
	//--------------------------------------------------------------------------
	//共通
	//--------------------------------------------------------------------------	

	/**
	 * 描画の前提となる情報を格納
	 * @param FC 全モードで共有される設定情報 
	 */
	public  void setConfig(FieldConfigBean FC);
	
	/**
	 * ボタンの描画 
	 * @param Button 描画するボタンの情報
	 */
	public  void drawButton(ButtonBean Button);

	/**
	 * テキストの描画
	 * @param Text 描画するテキストの情報
	 */
	public  void drawText(TextBean Text);
	
	
	//--------------------------------------------------------------------------
	//プレイモード用
	//--------------------------------------------------------------------------
	
	/**
	 * 内蔵Panelのアップデート 
	 * @param panel 
	 */
	public abstract <PanelType> void updatePanel(PanelType panel);
	
	/**
	 * 自機の描画 
	 * @param x x座標
	 * @param y y座標
	 */
	public  void drawMyPlane(Integer x,Integer y);
	
	/**
	 * 自機のミサイルの描画 
	 * @param isMyMissileActive 
	 * @param x x座標
	 * @param y y座標
	 */
	public  void drawMyMissile(Boolean isMyMissileActive,Integer x,Integer y);
	
	/**
	 * 敵機の描画 
	 * @param isEnemyAlive 
	 * @param x x座標
	 * @param y y座標
	 */
	public 	void drawEnemyPlane(Boolean[] isEnemyAlive,Integer[] x,Integer[] y);

	/**
	 * 敵機のミサイルの描画 
	 * @param isEnemyMissileActive 
	 * @param x x座標
	 * @param y y座標
	 */
	public  void drawEnemyMissile(Boolean[] isEnemyMissileActive,Integer[] x,Integer[] y);

	/**
	 *背景色の設定
	 */
	public  void setBackground();
	
	

}
