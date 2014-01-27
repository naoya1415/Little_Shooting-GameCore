package gameLogic.mode.Bean;

/**
 * ボタン表示用の情報を保持
 * @author NaoyaIchikawa
 * @version 1.10 2014/01/27
 */
public class ButtonBean extends TextBean{


	/**
	 * 当たり判定領域の左上x座標
	 */
	private Integer CollisionAreaX = null;
	
	/**
	 * 当たり判定領域の左上y座標
	 */
	private Integer CollisionAreaY = null;
	
	/**
	 * 当たり判定領域の幅
	 */
	private Integer CollisionAreaWidth = null;
	/**
	 * 当たり判定領域の高さ
	 */
	private Integer CollisionAreaHeight = null;
	
	
	/**
	 * 引数の座標がボタン内かどうかを判定
	 * @param X 
	 * @param Y 
	 * @return 引数の座標がボタン内かどうかを判定
	 */
	public Boolean isInside(Integer X,Integer Y){
		if(CollisionAreaX<=X && X<=CollisionAreaX+CollisionAreaWidth&&
		   CollisionAreaY<=Y && Y<=CollisionAreaY+CollisionAreaHeight
		){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	@Override
	public void setFontSize(Integer fontSize) {
		super.setFontSize(fontSize);
		setCollisionArea();
	}
	
	@Override
	public void setText(String Text){
		super.setText(Text);
		setCollisionArea();
	}
	
	@Override
	public void setStartPosition(Integer x,Integer y){
		super.setStartPosition(x, y);
		setCollisionArea();
	}
	
	/**
	 * 当たり判定領域を計算
	 */
	private void setCollisionArea(){
		CollisionAreaX=getX();
		CollisionAreaY = getY();
		
		CollisionAreaWidth = (int)(getFontSize()*0.55*getText().length());
		CollisionAreaHeight = (int)(getFontSize()*0.8);
		
	}

	/**
	 * @return collisionAreaX
	 */
	public Integer getCollisionAreaX() {
		return CollisionAreaX;
	}

	/**
	 * @return collisionAreaY
	 */
	public Integer getCollisionAreaY() {
		return CollisionAreaY;
	}

	/**
	 * @return collisionAreaWidth
	 */
	public Integer getCollisionAreaWidth() {
		return CollisionAreaWidth;
	}

	/**
	 * @return collisionAreaHeight
	 */
	public Integer getCollisionAreaHeight() {
		return CollisionAreaHeight;
	}
}
