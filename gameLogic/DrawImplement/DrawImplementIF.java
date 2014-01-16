package gameLogic.DrawImplement;

import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfig;
import gameLogic.mode.Bean.TextBean;

public interface DrawImplementIF {
	
	
	//--------------------------------------------------------------------------
	//プレイモード用
	//--------------------------------------------------------------------------
	/* 内蔵Panelのアップデート */
	public  <PanelType> void updatePanel(PanelType panel);
	
	/* 自機の描画 */
	public abstract  void drawMyPlane(Integer x,Integer y);
	
	/* 自機のミサイルの描画 */
	public abstract  void drawMyMissile(Boolean isMyMissileActive,Integer x,Integer y);
	
	/* 敵機の描画 */
	public abstract	void drawEnemyPlane(Boolean[] isEnemyAlive,Integer[] x,Integer[] y);

	/* 敵機のミサイルの描画 */
	public abstract  void drawEnemyMissile(Boolean[] isEnemyMissileActive,Integer[] x,Integer[] y);

	/*アイコンのロード機能の実装を強制する*/
	public abstract <ResourceType> void loadIconPictures(ResourceType resource);


	/*背景色の設定機能の実装を強制する*/
	public abstract void setBackground();
	
	
	//--------------------------------------------------------------------------
	//共通
	//--------------------------------------------------------------------------	

	/* 描画の前提となる情報を格納する */
	public abstract void setConfig(FieldConfig FC);
	
	/* スタートボタンの描画 */
	public abstract void drawButton(ButtonBean Button);

	/* テキストの描画 */
	public abstract void drawText(TextBean Text);
}
