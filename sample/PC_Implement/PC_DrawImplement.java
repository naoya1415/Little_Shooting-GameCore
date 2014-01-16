package sample.PC_Implement;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfigBean;
import gameLogic.mode.Bean.TextBean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * PC(AWT)依存コードの実装
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class PC_DrawImplement extends JPanel implements DrawImplementIF {


	/**
	 * 描画対象
	 */
	private  Graphics g = null;
	
	/**
	 *全モードで共有される設定情報 
	 */
	FieldConfigBean fc = null;
	
	/**
	 * コンストラクタ
	 */
	public PC_DrawImplement(){

	}
	
	@Override
	public void setConfig(FieldConfigBean FC){
		this.fc = FC;		
	}
	
		
	@SuppressWarnings("hiding")
	@Override
	public  <Graphics> void updatePanel(Graphics panel) {
		this.g = (java.awt.Graphics)panel;
	}
	
	
	@Override
	public void setBackground() {
		g.setColor(Color.black);
		g.fillRect(0, 0, fc.screenWidth,fc.screenHeight);
	}
	
	@Override
	public void drawMyPlane(Integer x, Integer y) {
		
	    int xPoints[] = {x, x+fc.myWidth/2,x-fc.myWidth/2};
	    int yPoints[] = {y, y+fc.myHeight/2, y+fc.myHeight/2};

	    g.setColor(Color.white);
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	@Override
	public void drawMyMissile(Boolean isMyMissileActive,Integer x,Integer y) {
		if(isMyMissileActive){
			g.setColor(Color.white);
			g.fillRect(x, y, fc.missileWidth, fc.missileHeight);
		}
	}

	@Override
	public void drawEnemyPlane(Boolean[] isEnemyAlive,Integer[] x,Integer[] y) {
		
		for (int i = 0; i < fc.numOfEnemy; i++) {
			// ミサイルの配置
			if (isEnemyAlive[i]) {
				g.setColor(Color.red);
//				g.fillRect(x[i], y[i], 2, 5);
				int xPoints[] = {x[i], x[i]+fc.enemyWidth/2,x[i]-fc.enemyWidth/2};
			    int yPoints[] = {y[i], y[i]-fc.enemyHeight/2, y[i]-fc.enemyHeight/2};

				g.fillPolygon(xPoints, yPoints, 3);

			}
		}

	}

	@Override
	public void drawEnemyMissile(Boolean[] isEnemyMissileActive,Integer[] x,Integer[] y) {
		for (int i = 0; i < fc.numOfEnemy; i++) {
			// ミサイルの配置
			if (isEnemyMissileActive[i]) {
				g.setColor(Color.red);
				g.fillRect(x[i], y[i], fc.missileWidth, fc.missileHeight);
			}
		}
	}

	
	@Override
	public void drawButton(ButtonBean Button) {

		insideOf_DrawText(Button);		
		g.drawRect(Button.getCollisionAreaX(),Button.getCollisionAreaY(),Button.getCollisionAreaWidth(),Button.getCollisionAreaHeight());
		
	}

	@Override
	public void drawText(TextBean Text) {
		insideOf_DrawText(Text);
	}
	
	
	/**
	 * テキストとボタンで共通の描画コード
	 * @param Bean 
	 */
	private void insideOf_DrawText(TextBean Bean){
	
		//大文字と小文字でサイズが一緒なConsolasを使用
		g.setFont(new Font("Consolas", Font.BOLD, Bean.getFontSize()));	  
		
		g.setColor(new Color(Bean.getColorR(),Bean.getColorG(),Bean.getColorB(),Bean.getColorAlpha()));
		
		g.drawString(Bean.getText(), Bean.getX() , Bean.getY());
	}

}
