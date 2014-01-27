package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfigBean;

/**
 * インベーダーゲーム風ゲームのロジッククラス
 * @author NaoyaIchikawa
 * @version 1.10 2014/01/27
 */
public class PlayMode implements GameModeIF{
	
	/**
	 * 本モードの名前
	 */
	final public static String name = "PlayMode";

	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di = null;
	
	/**
	 *全モードで共有される設定情報 
	 */
	protected FieldConfigBean fc = null;
			
	/**
	 * 自機の座標
	 */
	protected Integer myX= null, myY= null, tempMyX= null;
	
	/**
	 * 自機ミサイルの座標
	 */
	protected Integer myMissileX= null, myMissileY= null;
	/**
	 * 自機ミサイルの死活
	 */
	protected Boolean isMyMissileActive= null;
	
	/**
	 * 各敵機のx座標
	 */
	protected Integer[] enemyX = null;
	
	/**
	 * 各敵機のy座標 
	 */
	protected Integer[] enemyY = null;
	
	/**
	 * 各敵機の移動方向
	 */
	protected Integer[] enemyMove = null;
	/**
	 * 各敵機の死活
	 */
	protected Boolean[] isEnemyAlive = null;
	
	
	/**
	 * 各敵機ミサイルのX座標
	 */
	protected Integer[] enemyMissileX = null;
	/**
	 * 各敵機ミサイルのY座標
	 */
	protected Integer[] enemyMissileY = null;
	
	/**
	 * 残り敵数
	 */
	protected Integer numOfAlive = null;
	
	/**
	 * 各敵機ミサイルの死活
	 */
	protected Boolean[] isEnemyMissileActive = null;
	

	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String open(DrawImplementIF DI, FieldConfigBean fc) throws Exception{
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
		enemyX = new Integer[fc.numOfEnemy];
		enemyY = new Integer[fc.numOfEnemy];
		enemyMove = new Integer[fc.numOfEnemy];
		isEnemyAlive = new Boolean[fc.numOfEnemy];
		enemyMissileX = new Integer[fc.numOfEnemy];
		enemyMissileY = new Integer[fc.numOfEnemy];
		isEnemyMissileActive = new Boolean[fc.numOfEnemy];
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
	public String update() throws Exception{
		di.setBackground(FieldConfigBean.black);
		
		
		calcMyPlane();
		calcMyMissile();

		calcEnemyPlane();
		String isLose = calcEnemyMissile();
		
		di.drawMyPlane(myX,myY);
		di.drawMyMissile(isMyMissileActive,myMissileX,myMissileY);
				
		di.drawEnemyPlane(isEnemyAlive,enemyX,enemyY);
		di.drawEnemyMissile(isEnemyMissileActive,enemyMissileX,enemyMissileY,FieldConfigBean.red);
		
		
		if(numOfAlive == 0){
			return Result_ClearMode.name;
		}
	
		return isLose;
	}

	@Override
	public String touch(Integer X, Integer Y) throws Exception{
		return null;
	}

	@Override
	public String move(Integer X, Integer Y) throws Exception{
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
				if ((myMissileX >= enemyX[i] - fc.enemyWidth)
						&& (myMissileX <= enemyX[i] + fc.enemyWidth)
						&& (myMissileY >= enemyY[i] - fc.enemyHeight)
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
			if ((enemyMissileX[i] >= tempMyX -fc.myWidth)
					&& (enemyMissileX[i] <= tempMyX + fc.myWidth)
					&& (enemyMissileY[i] + 5 >= myY - fc.myHeight)
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
	public String pointerDown(Integer X, Integer Y) throws Exception{
		if (!isMyMissileActive) {
			myMissileX = myX ;
			myMissileY = myY;
			isMyMissileActive = true;
		}
		return null;
	}


	@Override
	public String pointerUp(Integer X, Integer Y)throws Exception {
		return null;
	}


	@Override
	public String keyPressed(char KeyChar)throws Exception {
		
		if(numOfAlive ==0){
			return Result_ClearMode.name;
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
	@Override
	public String close() throws Exception {
		return null;
	}
}
