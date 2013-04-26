import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravitation = -9.82;
	private List<Ball> myBalls = new LinkedList<Ball>();

	public DummyModel(double width, double height) {
		//myBalls.add(new Ball(450, 220, 150, 0, 50));
		myBalls.add(new Ball(760, 360, 200, 0, 75));
		myBalls.add(new Ball(330, 125, 23, 0, 25));
		this.areaWidth = width;
		this.areaHeight = height;
	}

	@Override
	public void tick(double deltaT) {
		for (int i = 0; i < myBalls.size(); i++) {

			double x = myBalls.get(i).getX();
			double y = myBalls.get(i).getY();
			double velocityX = myBalls.get(i).getVelocityX();
			double velocityY = myBalls.get(i).getVelocityY();
			double diameter = myBalls.get(i).getWidth();
			

			velocityY += gravitation;
			myBalls.get(i).setVelocityY(velocityY);
			
			if ((x <= 0 && velocityX < 0)|| (x >= areaWidth - diameter && velocityX > 0)) {
				velocityX *= -1;
				myBalls.get(i).setVelocityX(velocityX);

			}
			if ((y <= 0 && velocityY < 0)|| (y >= areaHeight - diameter && velocityY > 0)) {
				velocityY *= -1;
				myBalls.get(i).setVelocityY(velocityY);
			}

			//Ball-to-Ball-Collision
			for (int j = i+1; j < myBalls.size(); j++) {
				if(Math.abs(myBalls.get(i).getCenterX() - myBalls.get(j).getCenterX()) 
						< (myBalls.get(i).getWidth()/2 + myBalls.get(j).getWidth()/2) 
						&& Math.abs(myBalls.get(i).getCenterY() - myBalls.get(j).getCenterY()) 
						< (myBalls.get(i).getWidth()/2 + myBalls.get(j).getWidth()/2) ){
					
					myBalls.get(i).setVelocityX(collisionSpeed(Result.FIRSTX, myBalls.get(i), myBalls.get(j)));
					myBalls.get(i).setVelocityY(collisionSpeed(Result.FIRSTY, myBalls.get(i), myBalls.get(j)));
					myBalls.get(j).setVelocityX(collisionSpeed(Result.SECONDX, myBalls.get(i), myBalls.get(j)));
					myBalls.get(j).setVelocityY(collisionSpeed(Result.SECONDY, myBalls.get(i), myBalls.get(j)));
				}
			}


			velocityY += gravitation;
			myBalls.get(i).setVelocityY(velocityY);
			
			myBalls.get(i).setX(x + velocityX * deltaT);
			myBalls.get(i).setY(y + velocityY * deltaT);
		}
	}

	@Override
	public List<Ball> getBalls() {
		return myBalls;
	}
	
	private enum Result{
		FIRSTX, FIRSTY, SECONDX, SECONDY;
	}
	
	private double collisionSpeed(Result type, Ball firstBall, Ball secondBall){
		double m1 = firstBall.getWeigth();
		double m2 = secondBall.getWeigth();
		double u1, u2, v1, v2, alpha, deltaX, deltaY, adjX1, adjX2, adjY1, adjY2;

		deltaX = firstBall.getCenterX() - secondBall.getCenterX();
		deltaY = firstBall.getCenterY() - secondBall.getCenterY();
		
		alpha = Math.atan(Math.abs(deltaX/deltaY));
		
		if(deltaX*deltaY < 0){
			adjX1 = firstBall.getVelocityX()*Math.cos(alpha)+firstBall.getVelocityY()*Math.sin(alpha);
			adjY1 = firstBall.getVelocityY()*Math.cos(alpha)-firstBall.getVelocityX()*Math.sin(alpha);
			adjX2 = secondBall.getVelocityX()*Math.cos(alpha)+secondBall.getVelocityY()*Math.sin(alpha);
			adjY2 = secondBall.getVelocityY()*Math.cos(alpha)-secondBall.getVelocityX()*Math.sin(alpha);
		}else{
			adjX1 = firstBall.getVelocityX()*Math.cos(alpha)-firstBall.getVelocityY()*Math.sin(alpha);
			adjY1 = firstBall.getVelocityX()*Math.sin(alpha)+firstBall.getVelocityY()*Math.cos(alpha);
			adjX2 = secondBall.getVelocityX()*Math.cos(alpha)-secondBall.getVelocityY()*Math.sin(alpha);
			adjY2 = secondBall.getVelocityX()*Math.sin(alpha)+secondBall.getVelocityY()*Math.cos(alpha);
		}
		
		u1 = adjY1;
		u2 = adjY2;
		
		/*if(type == Result.FIRSTX || type == Result.SECONDX){
			u1 = firstBall.getVelocityX();
			u2 = secondBall.getVelocityX();
		}else{
			u1 = firstBall.getVelocityY();
			u2 = secondBall.getVelocityY();
		}*/
		v1 = ((m1*u1+m2*u2)-m2*(-(u2-u1)))/(m1+m2);
		v2 = ((m1*u1+m2*u2)-m1*(-(u2-u1)))/(m1+m2);
		
		//TODO: reTranslate...
		if(deltaX*deltaY < 0){
			adjX1 = adjX1*Math.cos(alpha)-v1*Math.sin(alpha);
			adjY1 = adjX1*Math.sin(alpha)+v1*Math.cos(alpha);
			adjX2 = adjX2*Math.cos(alpha)-v2*Math.sin(alpha);
			adjY2 = adjX2*Math.sin(alpha)+v2*Math.cos(alpha);
		}else{
			adjX1 = adjX1*Math.cos(alpha)+v1*Math.sin(alpha);
			adjY1 = v1*Math.cos(alpha)-adjX1*Math.sin(alpha);
			adjX2 = adjX2*Math.cos(alpha)+v2*Math.sin(alpha);
			adjY2 = v2*Math.cos(alpha)-adjX2*Math.sin(alpha);
		}
		
		if(type == Result.FIRSTX || type == Result.FIRSTY){
			return adjY1;
		}else{
			return adjY2;
			
		}
	}
	
}
