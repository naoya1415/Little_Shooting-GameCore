package gameLogic.mode.Bean;

public class ButtonBean extends TextBean{

	public Integer CollisionAreaX = null;
	public Integer CollisionAreaY = null;
	public Integer CollisionAreaWidth = null;
	public Integer CollisionAreaHeight = null;
	
	
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
	public void setColor(Integer R,Integer G,Integer B,Integer Alpha) {
		super.setColor(R, G, B, Alpha);
		if(super.getFontSize() != null){
			setCollisionArea();
		}
	}
	
	@Override
	public void setFontSize(Integer fontSize) {
		super.setFontSize(fontSize);
		if(super.getX() != null && super.getY() != null){
			setCollisionArea();
		}
	
	}
	
	private void setCollisionArea(){
		CollisionAreaX=getX();
		CollisionAreaY = getY()-(int)(getFontSize()*0.7);
		
		CollisionAreaWidth = (int)(getFontSize()*0.55*getText().length());
		CollisionAreaHeight = (int)(getFontSize()*0.8);
		
	}
}
