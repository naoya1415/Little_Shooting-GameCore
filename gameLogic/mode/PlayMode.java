package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfigBean;

/**
 * インベーダーゲーム風シューティングゲームのロジッククラス
 * @author n-dolphin
 * @version 1.00 2014/01/17
 */
public class PlayMode implements GameModeIF{
	
	/**
	 * 本モードの名前
	 */
	final public static String name = "PlayMode";

	
	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di;
	
	/**
	 *全モードで共有される設定情報 
	 */
	protected FieldConfigBean fc = new FieldConfigBean();
			
	
	/**
	 * 自機の座標
	 */
	protected Integer myX, myY, tempMyX;
	
	/**
	 * 自機ミサイルの座標
	 */
	protected Integer myMissileX, myMissileY;
	/**
	 * 自機ミサイルの死活
	 */
	protected boolean isMyMissileActive;
	

	
	
	/**
	 * 各敵機のx座標
	 */
	protected Integer[] enemyX = new Integer[fc.numOfEnemy];
	
	/**
	 * 各敵機のy座標 
	 */
	protected Integer[] enemyY = new Integer[fc.numOfEnemy];
	
	/**
	 * 各敵機の移動方向
	 */
	protected Integer[] enemyMove = new Integer[fc.numOfEnemy];
	/**
	 * 各敵機の死活
	 */
	protected Boolean[] isEnemyAlive = new Boolean[fc.numOfEnemy];
	
	
	/**
	 * 各敵機ミサイルのX座標
	 */
	protected Integer[] enemyMissileX = new Integer[fc.numOfEnemy];
	/**
	 * 各敵機ミサイルのY座標
	 */
	protected Integer[] enemyMissileY = new Integer[fc.numOfEnemy];
	
	/**
	 * 残り敵数
	 */
	protected Integer numOfAlive ;
	
	/**
	 * 各敵機ミサイルの死活
	 */
	protected Boolean[] isEnemyMissileActive = new Boolean[fc.numOfEnemy];
	


	@Override
	public String name() {
		return name;
	}
	@Override
	public String launch(DrawImplementIF DI, FieldConfigBean fc) {
		this.di = DI;
		this.fc = fc;
		
		initMine();
		initEnemies();
		
		this.di.setConfig(fc);
		
		return null;
	}

	
	/**
	 * 自機の初期化 
	 */
	protected void initMine() {
		myX = fc.screenWidth / 2;
		myY = fc.screenHeight /10 * 7;
		tempMyX = fc.screenWidth / 2;
		
		myMissileX = myX;
		myMissileY = myY;
		
		isMyMissileActive = false;
	}

	/**
	 * 敵機の初期化 
	 */
	protected void initEnemies() {
		
		numOfAlive = fc.numOfEnemy;
		
		for (Integer i = 0; i < 7; i++) {
			enemyX[i] = 70 * i;
			enemyY[i] = 50;
		}
		for (Integer i = 7; i < fc.numOfEnemy; i++) {
			enemyX[i] = 70 * (i - 6);
			enemyY[i] = 100;
		}
		for (Integer i = 0; i < fc.numOfEnemy; i++) {
			isEnemyAlive[i] = true;
			enemyMove[i] = 1;
		}
		for (Integer i = 0; i < fc.numOfEnemy; i++) {
			isEnemyMissileActive[i] = true;
			enemyMissileX[i] = enemyX[i] + fc.enemyWidth / 2;
			enemyMissileY[i] = enemyY[i];
			fc.enemyMissileSpeed[i] = 10 + (i % 6);
		}
	}
	
	@Override
	public String update() {
		di.setBackground();
		
		
		calcMyPlane();
		calcMyMissile();

		calcEnemyPlane();
		String isLose = calcEnemyMissile();
		
		di.drawMyPlane(myX,myY);
		di.drawMyMissile(isMyMissileActive,myMissileX,myMissileY);
				
		di.drawEnemyPlane(isEnemyAlive,enemyX,enemyY);
		di.drawEnemyMissile(isEnemyMissileActive,enemyMissileX,enemyMissileY);
		
		
		if(numOfAlive == 0){
			return Result_ClaerMode.name;
		}
	
		return isLose;
	}

	@Override
	public String touch(Integer X, Integer Y) {
		return null;
	}

	@Override
	public String move(Integer X, Integer Y) {
		myX = X;
		return null;
	}
	
	
	/**
	 * 自機の座標計算
	 */
	protected void calcMyPlane() {
		if (Math.abs(tempMyX - myX) < fc.gap) {
			if (myX < 0) {
				myX = 0;
			} else if (myX + fc.myWidth > fc.screenWidth) {
				myX = fc.screenWidth - fc.myWidth;
			}
			tempMyX = myX;
		}
	}


	/**
	 * 自機ミサイルの座標計算
	 */
	protected void calcMyMissile() {
		if (!isMyMissileActive) {
			return ;
		}
		
		// ミサイルの配置
		myMissileY -= fc.myMissileSpeed;
		// 自機のミサイルの敵機各機への当たり判定
		for (Integer i = 0; i < fc.numOfEnemy; i++) {
			
			if (isEnemyAlive[i]) {
				if ((myMissileX >= enemyX[i])
						&& (myMissileX <= enemyX[i] + fc.enemyWidth)
						&& (myMissileY >= enemyY[i])
						&& (myMissileY <= enemyY[i] + fc.enemyHeight)) {
					isEnemyAlive[i] = false;
					isMyMissileActive = false;
					numOfAlive--;
				}
			}
		}
		// ミサイルがウィンドウ外に出たときのミサイルの再初期化
		if (myMissileY < 0){
			isMyMissileActive = false;
		}
	}

	
	/**
	 * 敵機の計算
	 */
	protected void calcEnemyPlane() {
		for (Integer i = 0; i < fc.numOfEnemy; i++) {
			if (isEnemyAlive[i]) {
				if (enemyX[i] > fc.screenWidth - fc.enemyWidth) {
					enemyMove[i] = -1;
				} else if (enemyX[i] < 0) {
					enemyMove[i] = 1;
				}
				enemyX[i] += enemyMove[i] * 10;
			}
		}
	}
	
	
	/**
	 * 敵機のミサイルの計算
	 * @return 敗北時の遷移先名
	 */
	protected String calcEnemyMissile() {
		for (Integer i = 0; i < fc.numOfEnemy; i++) {
			// ミサイルの配置
			if (isEnemyMissileActive[i]) {
				enemyMissileY[i] += fc.enemyMissileSpeed[i];
			}
			// 敵機のミサイルの自機への当たり判定
			if ((enemyMissileX[i] >= tempMyX)
					&& (enemyMissileX[i] <= tempMyX + fc.myWidth)
					&& (enemyMissileY[i] + 5 >= myY)
					&& (enemyMissileY[i] + 5 <= myY + fc.myHeight)) {
				
				return MyDamage();
			}
			// ミサイルがウィンドウ外に出たときのミサイルの再初期化
			if (enemyMissileY[i] > fc.screenHeight) {
				if (isEnemyAlive[i]) {
					enemyMissileX[i] = enemyX[i] + fc.enemyWidth / 2;
					enemyMissileY[i] = enemyY[i] + fc.enemyHeight;
				} else {
					isEnemyMissileActive[i] = false;
				}
			}
		}
		return null;
	}
	
	/**
	 * 被ダメージ処理
	 * @return 敗北時の遷移先名
	 */
	private String MyDamage(){
		return Result_GameOverMode.name;
	}


	@Override
	public String pointerDown(Integer X, Integer Y) {
		if (!isMyMissileActive) {
			myMissileX = myX ;
			myMissileY = myY;
			isMyMissileActive = true;
		}
		return null;
	}


	@Override
	public String pointerUp(Integer X, Integer Y) {
		return null;
	}


	@Override
	public String keyPressed(char KeyChar) {
		
		if(numOfAlive ==0){
			return Result_ClaerMode.name;
		}
		
		if(KeyChar=='k'){
			for (Integer i = 0; i < fc.numOfEnemy; i++) {
				if (isEnemyAlive[i]) {
					isEnemyAlive[i] =false;
					break;
				}
			}
			

			numOfAlive--;			
		}

		return null;
	}
}
