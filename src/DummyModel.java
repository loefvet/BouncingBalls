import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravitation = -9.82;
	private List<Ball> myBalls = new LinkedList<Ball>();

	public DummyModel(double width, double height) {
		myBalls.add(new Ball(200, 220, 55, 0, 50));
		myBalls.add(new Ball(100, 180, -65, 0, 40));
		//myBalls.add(new Ball(250, 250, 50, 0, 75));
		//myBalls.add(new Ball(500, 150, 250, 0, 75));
		//myBalls.add(new Ball(500, 350, 100, 0, 75));
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


			velocityY += gravitation*deltaT*15;
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
				if(Math.sqrt(Math.pow(Math.abs(myBalls.get(i).getCenterX() - myBalls.get(j).getCenterX()), 2)
						+ Math.pow(Math.abs(myBalls.get(i).getCenterY() - myBalls.get(j).getCenterY()), 2)) 
						< (myBalls.get(i).getWidth()/2 + myBalls.get(j).getWidth()/2)){
					
					collision(myBalls.get(i), myBalls.get(j));
				}
			}
			velocityX = myBalls.get(i).getVelocityX();
			velocityY = myBalls.get(i).getVelocityY();

			velocityY += gravitation*deltaT*15;
			myBalls.get(i).setVelocityY(velocityY);

			myBalls.get(i).setX(x + velocityX * deltaT);
			myBalls.get(i).setY(y + velocityY * deltaT);
		}
	}

	@Override
	public List<Ball> getBalls() {
		return myBalls;
	}

	private class Vector{
		private double x, y;
		public Vector(double x, double y){
			this.x = x;
			this.y = y;
		}

		public void transform(double alpha){
			double newX = x*Math.cos(alpha)-y*Math.sin(alpha);
			double newY = x*Math.sin(alpha)+y*Math.cos(alpha);
			x = newX;
			y = newY;
		}
	}

	private void collision(Ball firstBall, Ball secondBall){
		double m1 = firstBall.getWeigth();
		double m2 = secondBall.getWeigth();
		double u1, u2, v1, v2, alpha, deltaX, deltaY;

		Vector firstVector = new Vector(firstBall.getVelocityX(), firstBall.getVelocityY());
		Vector secondVector = new Vector(secondBall.getVelocityX(), secondBall.getVelocityY());


		deltaX = firstBall.getCenterX() - secondBall.getCenterX();
		deltaY = firstBall.getCenterY() - secondBall.getCenterY();

		alpha = 0;
		if(deltaY != 0){
			alpha = Math.atan(Math.abs(deltaX/deltaY));
			if(deltaX*deltaY < 0){
				alpha = 2*Math.PI - alpha;
			}
		}

		

		firstVector.transform(alpha);
		secondVector.transform(alpha);

		u1 = firstVector.y;
		u2 = secondVector.y;

		double I = m1*u1+m2*u2;
		double U = -(u2-u1);
		v1 = (I-m2*U)/(m1+m2);
		v2 = U+v1;

		firstVector.y = v1;
		secondVector.y = v2;

		alpha = 2*Math.PI-alpha;

		firstVector.transform(alpha);
		secondVector.transform(alpha);

		firstBall.setVelocityX(firstVector.x);
		firstBall.setVelocityY(firstVector.y);
		secondBall.setVelocityX(secondVector.x);
		secondBall.setVelocityY(secondVector.y);

	}

}
