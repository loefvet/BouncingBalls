import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private final double gravitation = -9.82;
	private List<Ball> myBalls = new LinkedList<Ball>();

	public DummyModel(double width, double height) {
		myBalls.add(new Ball(450, 220, 50, 36, 50));
		myBalls.add(new Ball(760, 360, 100, -300, 75));
		myBalls.add(new Ball(330, 125, 500, 500, 25));
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
				System.out.println(velocityX + " ; " + velocityY);

			}
			if ((y <= 0 && velocityY < 0)|| (y >= areaHeight - diameter && velocityY > 0)) {
				velocityY *= -1;
				myBalls.get(i).setVelocityY(velocityY);
				System.out.println(velocityX + " ; " + velocityY);
			}

			//Ball-to-Ball-Collision
			for (int j = 0; j < myBalls.size(); j++) {
				for (int n = 0; n < myBalls.size(); n++) {

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
}
