package gameLogic;

import gameLogic.DrawImplement.DrawImplementIF;
import gameLogic.mode.GameModeIF;
import gameLogic.mode.Bean.FieldConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * ゲームコンテナ
 * 
 * AnroidやPCのエントリーポイントから、画面表示・モード管理などの
 * 本ゲームを構成する全てのロジックの処理が委譲される。
 * 
 * DrawImplemation(実行環境に依存した描画処理)や、
 * Mode(スタート画面、プレイ中画面、リザルト画面などのゲーム状態)を管理し、
 * タッチ、タップ、移動などのイベントをModeのメソッドに対応づける
 * 
 * @author NaoyaIchikawa
 * @version 1.00 2014/01/17
 */
public abstract class  GameContainer {

	/*実行環境に依存した描画処理の実装*/
	protected DrawImplementIF di;
	
	/*実装クラスにて挿入される、Mode(ゲーム状態)を管理する*/
	protected List<GameModeIF> modeSlot = new ArrayList<GameModeIF>();
	
	/*現在のMode(ゲーム状態)*/
	protected GameModeIF currentMode =null;
	
	/*全モードで共有される設定情報*/
	FieldConfig fc = new FieldConfig();


	/**
	 * コンストラクタ
	 * 
	 * 本コンストラクタは、本クラスの実装クラスからのみ、
	 * 呼び出される。
	 * 
	 * @param Width 
	 * @param Height 
	 * @param DI 
	 */
	protected GameContainer(Integer Width,Integer Height,DrawImplementIF DI) {;
		fc.screenWidth = Width;
		fc.screenHeight = Height;
		
		fc.realScreenWidth = Width;
		fc.realScreenHeight = Height;
		
		this.di = DI;
		di.setConfig(fc);
	}
	
	public abstract <PanelType> void  update(PanelType panel);
	
	public void touch(Integer X,Integer Y){
		changeMode(currentMode.touch(X,Y));
	}
	
	public void move(Integer X,Integer Y){
		changeMode(currentMode.move(X, Y));
	}
	
	public void pointerDown(Integer X,Integer Y){
		changeMode(currentMode.pointerDown(X,Y));
	}
	
	public void pointerUp(Integer X,Integer Y){
		changeMode(currentMode.pointerUp(X,Y));
	}
	
	public void keyPressed(char KeyChar){
		changeMode(currentMode.keyPressed(KeyChar));
	}
	
	
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