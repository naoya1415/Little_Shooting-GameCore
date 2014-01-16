package gameLogic.mode.Bean;

public class TextBean {
	private String Text = null;
	private Integer X = null;
	private Integer Y = null;
	private Integer fontSize = null;

	private Integer[] color = {0,0,0,255};
	
	/**
	 * Textの左上位置を設定
	 * @param Integer x,Integer y
	 */
	public void setLocation(Integer x,Integer y) {
		X = x;
		Y = y;
	}
	
	/**
	 * オブジェクトの色を設定
	 * @param Integer R,Integer G,Integer B,Integer Alpha
	 */
	public void setColor(Integer R,Integer G,Integer B,Integer Alpha) {
		color[0] = R;
		color[1] = G;
		color[2] = B;
		color[3] = Alpha;
	}
	
	
	/**
	 * fontSizeを設定します。
	 * @param fontSize fontSize
	 */
	public void setFontSize(Integer fontSize) {
	    this.fontSize = fontSize;
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


	
}
