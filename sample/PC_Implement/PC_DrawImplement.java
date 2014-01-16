package sample.PC_Implement;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.ButtonBean;
import gameLogic.mode.Bean.FieldConfig;
import gameLogic.mode.Bean.TextBean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PC_DrawImplement extends JPanel  implements DrawImplementIF {

	ImageIcon iconMe, iconEnemy;
	Image imgMe, imgEnemy;

	
	private  Graphics g = null;
	
	FieldConfig fc = null;
	
	public PC_DrawImplement(){
//		loadIconPictures(null)

	}
	
	@Override
	public void setConfig(FieldConfig FC){
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
		g.fillRect(0, 0, fc.realScreenWidth,fc.realScreenHeight);
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
	public <ResourceType> void loadIconPictures(ResourceType resource) {
		// 画像の取り込み
		imgMe = getImg("jiki.jpg");
		imgEnemy = getImg("teki.jpg");
	}

	/* 画像ファイルからImage クラスへの変換 */
	public Image getImg(String filename) {
		ImageIcon icon = new ImageIcon(filename);
		Image img = icon.getImage();
		return img;
	}

	@Override
	public void drawButton(ButtonBean Button) {

		insideOf_DrawText(Button);		
		g.drawRect(Button.CollisionAreaX,Button.CollisionAreaY,Button.CollisionAreaWidth,Button.CollisionAreaHeight);
		
	}

	@Override
	public void drawText(TextBean Text) {
		insideOf_DrawText(Text);
	}
	
	private void insideOf_DrawText(TextBean Bean){
	
		//大文字と小文字でサイズが一緒なConsolasを使用
		g.setFont(new Font("Consolas", Font.BOLD, Bean.getFontSize()));	  
		
		g.setColor(new Color(Bean.getColorR(),Bean.getColorG(),Bean.getColorB(),Bean.getColorAlpha()));
		
		g.drawString(Bean.getText(), Bean.getX() , Bean.getY());
	}

}
