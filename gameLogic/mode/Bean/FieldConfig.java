package gameLogic.mode.Bean;

public class FieldConfig {
	
	public static Integer defaultScreenWidth = 500;
	public static Integer defaultScreenHeight = 800;
	
	public Integer screenWidth;
	public Integer screenHeight;
	
	
	public Integer realScreenWidth;
	public Integer realScreenHeight;
	
	/* 自機に関する変数 */
	public Integer myHeight=30;
	public Integer myWidth=20;

	public Integer gap = 100;
	

	/* 敵機に関する変数 */
	public Integer numOfEnemy = 12;
	public Integer enemyWidth = 30;
	public Integer enemyHeight = 30;

	public Integer missileWidth =2;
	public Integer missileHeight =5;
	
	public Integer[] enemyMissileSpeed = new Integer[numOfEnemy];
}
