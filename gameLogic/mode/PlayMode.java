package gameLogic.mode;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.Bean.FieldConfig;

public class PlayMode implements GameModeIF{
	
	

	private DrawImplementIF di;
	
	FieldConfig fc = new FieldConfig();
			
	
	private Integer myX, myY, tempMyX;
	private Integer myMissileX, myMissileY;
	private boolean isMyMissileActive;
	private Integer numOfAlive ;
	
	
	private Integer[] enemyX = new Integer[fc.numOfEnemy];
	private Integer[] enemyY = new Integer[fc.numOfEnemy];
	private Integer[] enemyMove = new Integer[fc.numOfEnemy];
	private Integer[] enemyMissileX = new Integer[fc.numOfEnemy];
	private Integer[] enemyMissileY = new Integer[fc.numOfEnemy];
	
	private Boolean[] isEnemyAlive = new Boolean[fc.numOfEnemy];
	private Boolean[] isEnemyMissileActive = new Boolean[fc.numOfEnemy];
	

	
	final public static String name = "PlayMode";

	@Override
	public String name() {
		return name;
	}
	@Override
	public String launch(DrawImplementIF DI, FieldConfig fc) {
		this.di = DI;
		this.fc = fc;
		

		initMine();
		initEnemies();
		
		//TODO:ここでDPIのへんこうなどで、FCの中身を更新する
		this.di.setConfig(fc);
		
		return null;
	}

	
	/* 自機の初期化 */
	public void initMine() {
		myX = fc.screenWidth / 2;
		myY = fc.screenHeight /10 * 7;
		tempMyX = fc.screenWidth / 2;
		
		myMissileX = myX;
		myMissileY = myY;
		
		isMyMissileActive = false;
	}

	/* 敵機の初期化 */
	public void initEnemies() {
		
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
	
	/*当たり判定領域を設定(Androidでのみ使用を想定=DPIによって画像のサイズが変わるため)*/
	public  void setCollisionDetection(Integer defaultDPI,Integer deviceDPI){
		Float scale = (float)deviceDPI/(float)defaultDPI;
		fc.myWidth = (int)(30*scale);
		fc.myHeight = (int)(20*scale);
		
		fc.enemyWidth = (int)(30*scale);
		fc.enemyHeight = (int)(20*scale);
		
		fc.missileWidth = (int)(4*scale);
		fc.missileHeight = (int)(10*scale);
		
		//TODO:自・敵のミサイルもDPIによってサイズが代わるので、あたり判定のサイズも変える処理を実装
	};
	
	/* 自機の描画 */
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

	/***
	 * 自機のミサイルの計算 
	 * 返り値は自ミサイルを描画するかどうか
	 * trueならするfalseならしない(==)
	 *  */
	
	public Boolean calcMyMissile() {
		if (!isMyMissileActive) {
			return false;
		}
		
		// ミサイルの配置
		myMissileY -= 15;
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
			return false;
		}
		return true;
	}

	
	/* 敵機の計算 */
	public void calcEnemyPlane() {
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
	
	
	/* 敵機のミサイルの計算 */
	public String calcEnemyMissile() {
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
				
				//TODO: 自機のダメージ処理?敗北処理?を実装
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
		// TODO 自動生成されたメソッド・スタブ
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
