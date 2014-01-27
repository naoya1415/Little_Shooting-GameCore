package gameLogic.mode.Bean;

/**
 * テキスト表示用の情報を保持
 * @author NaoyaIchikawa
 * @version 1.10 2014/01/27
 */
public class TextBean {
	
	/**
	 * 標準フォントサイズ
	 */
	public static Integer DefaultFontSize = FieldConfigBean.defaultScreenHeight/10;
	
	/**
	 * 一文字当たりの幅
	 */
	public static float OneLetterWidthScale = 0.55F; 
	
	/**
	 * 一文字当たりの高さ
	 */
	public static float OneLetterHeightScale = 0.8F;
	
	/**
	 * フォントが設定されたか
	 */
	private Boolean FontSetted = false;
	

	/**
	 * テキストの水平表示位置を「左寄せ、中央寄せ、右寄せ」のうちいずれかに設定する
	 * @param position "left" or "center" or "right"
	 */
	public void setHorizonalPosition(String position){
		
		if(position.equals("left")){
			setStartPosition(0,null);
			return;
		}
		if(position.equals("center")){
			setStartPosition(FieldConfigBean.defaultScreenWidth/2-getTextWidth()/2,null);
			return ;
		}
		if(position.equals("right")){
			setStartPosition(FieldConfigBean.defaultScreenWidth-getTextWidth(),null);	
			return ;
		}
	}
	
	/**
	 * テキストの左上座標を設定
	 * @param x 
	 * @param y 
	 */
	public void setStartPosition(Integer x,Integer y)
	{
		if(x!=null){
			X = x;	
		}
		if(y!=null){
			Y = y;
		}
		calcFontSize();
	}
	
	/**
	 * テキストの幅を取得
	 * @return
	 */
	public  Integer getTextWidth(){
		return (int)(getFontSize()*OneLetterWidthScale*getText().length());
	}
	
	/**
	 * テキストの高さを取得
	 * @return
	 */
	public  Integer getTextHeight(){
		return (int)(getFontSize()*OneLetterHeightScale);
	}
	
	/**
	 * 引数(fontSize)をフォントサイズに設定した際のテキストの幅を取得
	 * @param fontSize 
	 * @return 
	 */
	public  Integer getTextWidth(Integer fontSize){
		return (int)(fontSize*OneLetterWidthScale*getText().length());
	}
	
	/**
	 * 引数(fontSize)をフォントサイズに設定した際のテキストの高さを取得
	 * @param fontSize 
	 * @return
	 */
	public  Integer getTextHeight(Integer fontSize){
		return (int)(fontSize*OneLetterHeightScale);
	}
	
	
	/**
	 * フォントのサイズを計算する
	 */
	private void calcFontSize(){

		if(this.Text==null){
			return;
		}
		if(FontSetted){
			return ;
		}

		Integer textWidth = getTextWidth(getFontSize());
		if(X+textWidth > EndX){
			this.fontSize = Math.min(getMaxWidth(EndX-X),this.fontSize);
		}		

		return;
	}
	
	
	/**
	 * フォントが取りうる最大の幅を計算する
	 * @param widthLimit 
	 * @return
	 */
	private Integer getMaxWidth(Integer widthLimit){
		Integer tryFontsize = getFontSize();
		Integer needToScale = getTextWidth(tryFontsize) - widthLimit ;
		
		Integer fontDiff = getTextWidth(tryFontsize+1) - getTextWidth(tryFontsize);
		
		return tryFontsize -  needToScale / fontDiff;
	}
	
	
	/**
	 * BeanのID
	 */
	private String id = null;
	
	/**
	 * 表示するテキスト
	 */
	private String Text = null;
	
	/**
	 * テキストの左上x座標
	 */
	private Integer X = 0;
	
	/**
	 * テキストの左上y座標 
	 */
	private Integer Y = 0;
	
	/**
	 * テキストの右下x座標
	 */
	private Integer EndX = FieldConfigBean.defaultScreenWidth;
	
	/**
	 * テキストの右下y座標 
	 */
	@SuppressWarnings("unused")
	private Integer EndY = FieldConfigBean.defaultScreenHeight;
	
	
	/**
	 * テキストのフォントサイズ
	 */
	private Integer fontSize = DefaultFontSize;

	/**
	 * テキストの色・透過
	 */
	private Integer[] color = FieldConfigBean.DefaultButtonColor;
	
	
	/**
	 * fontSizeを設定します。
	 * @param fontSize fontSize
	 */
	public void setFontSize(Integer fontSize) {
	    this.fontSize = fontSize;
	    FontSetted = true;
	}
	
	/**
	 * ColorのRを取得します。
	 * @return Integer color[0] 
	 */
	public Integer getColorR(){
		return color[0];
	}
	
	/**
	 * ColorのGを取得します。
	 * @return Integer color[0] 
	 */
	public Integer getColorG(){
		return color[1];
	}
	
	/**
	 * ColorのBを取得します。
	 * @return Integer color[0] 
	 */
	public Integer getColorB(){
		return color[2];
	}
	/**
	 * ColorのAlphaを取得します。
	 * @return Integer color[0] 
	 */
	public Integer getColorAlpha(){
		return color[3];
	}

	/**
	 * Textを取得します。
	 * @return Text
	 */
	public String getText() {
		return Text;
	}

	/**
	 * Textを設定します。
	 * @param Text Text
	 */
	public void setText(String Text) {
	    this.Text = Text;
	    calcFontSize();
	}

	/**
	 * Xを取得します。
	 * @return X
	 */
	public Integer getX() {
	    return X;
	}
	/**
	 * Yを取得します。
	 * @return Y
	 */
	public Integer getY() {
	    return Y;
	}
	/**
	 * fontSizeを取得します。
	 * @return fontSize
	 */
	public Integer getFontSize() {
	    return fontSize;
	}

	/**
	 * colorを取得します。
	 * @return color
	 */
	public Integer[] getColor() {
	    return color;
	}

	/**
	 * colorを設定します。
	 * @param color color
	 */
	public void setColor(Integer[] color) {
	    this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
