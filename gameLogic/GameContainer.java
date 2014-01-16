package gameLogic;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.GameModeIF;
import gameLogic.mode.Bean.FieldConfigBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ゲームコンテナ<br>
 * AnroidやPCのエントリーポイントから、
 * 画面表示・モード管理などの本ゲームを構成する全てのロジックの処理が委譲される。
 * <br>
 * 実行環境に依存した描画処理(DrawImplemation)や、<br>
 * スタート画面、プレイ中画面、リザルト画面などのゲーム状態(Mode)を管理し、<br>
 * タッチ、タップ、移動などのイベントをModeのメソッドに対応づける。<br>
 * <br>
 * 継承クラスでは、モードの登録・初期画面の決定、Updateメソッドの実装を行う。
 * @author NaoyaIchikawa
 * @version 1.00 2014/01/17
 */
public abstract class  GameContainer {

	/**
	 *実行環境に依存した描画処理の実装 
	 */
	protected DrawImplementIF di;
	
	/**
	 *実装クラスにて挿入される、Mode(ゲーム状態)を管理する 
	 */
	protected List<GameModeIF> modeSlot = new ArrayList<GameModeIF>();
	
	/**
	 * 現在のMode(ゲーム状態)
	 */
	protected GameModeIF currentMode =null;
	
	/**
	 *全モードで共有される設定情報 
	 */
	FieldConfigBean fc = new FieldConfigBean();

	/**
	 * コンストラクタ<br>
	 * 
	 * 本コンストラクタは、本クラスの実装クラスからのみ呼び出される。<br>
	 * @param Width   画面の幅
	 * @param Height  画面の高さ
	 * @param DI  描画メソッドの実装
	 */
	protected GameContainer(Integer Width,Integer Height,DrawImplementIF DI) {;
		fc.screenWidth = Width;
		fc.screenHeight = Height;
		
		this.di = DI;
		di.setConfig(fc);
	}
	
	/**
	 * Grapichc(AWT)や、Canvas(Android)のアップデート<br>
	 * @param panel 実装依存の描画対象
	 */
	public abstract <PanelType> void  update(PanelType panel);
	
	/**
	 * クリック時の処理をModeに委譲する<br>
	 * @param X x座標
	 * @param Y y座標
	 */
	public void touch(Integer X,Integer Y){
		changeMode(currentMode.touch(X,Y));
	}
	
	/**
	 * 移動時の処理をModeに委譲する<br>
	 * @param X x座標
	 * @param Y y座標
	 */
	public void move(Integer X,Integer Y){
		changeMode(currentMode.move(X, Y));
	}
	
	/**
	 * マウスダウン時の処理をModeに委譲する<br>
	 * @param X x座標
	 * @param Y y座標
	 */
	public void pointerDown(Integer X,Integer Y){
		changeMode(currentMode.pointerDown(X,Y));
	}
	
	/**
	 * マウスアップ時の処理をModeに委譲する<br>
	 * @param X x座標
	 * @param Y y座標
	 */
	public void pointerUp(Integer X,Integer Y){
		changeMode(currentMode.pointerUp(X,Y));
	}
	
	/**
	 * キーボード押下時の処理をModeに委譲する<br>
	 * @param KeyChar 押下されたキー
	 */
	public void keyPressed(char KeyChar){
		changeMode(currentMode.keyPressed(KeyChar));
	}
	
	
	/**
	 * モードの追加<br>
	 * @param add_mode 追加されるモード
	 */
	public void addMode(GameModeIF add_mode){
		if(add_mode == null){
			return;
		}
		//modeSlot.contains(add_mode)で判断しないのは、
		//modeのequalsをnameだけを対象に判断させる仕様にして良いか不安なため
		for(GameModeIF mode:modeSlot){
			if(mode.name().equals(add_mode.name())){
				throw new IllegalArgumentException("Collide name:Same name with " +  add_mode.name() +" had already added.");
			}
		}
			modeSlot.add(add_mode);	
	}
	/**
	 * 現在のモードを変更し、モードのLaunchメソッドを呼び出す<br>
	 * @param dst_mode 変更先のモード
	 */
	public void changeMode(String dst_mode){
		if(dst_mode == null){
			return;
		}
		
		if(currentMode != null){
			if(currentMode.name().equals(dst_mode)){
				return ;	
			}
		}
		
		for(GameModeIF mode:modeSlot){
			if(mode.name().equals(dst_mode)){
				currentMode = mode;
				changeMode(currentMode.launch(di,fc));
				return;
			}
		}
		throw new IllegalArgumentException("GameMode not found:Which name is \"" +  dst_mode +"\" has not found.");
	}
}