import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private List<Ball> myBalls = new LinkedList<Ball>();

	public DummyModel(double width, double height) {
		myBalls.add(new Ball(450, 220, 5, 3.6, 50));
		myBalls.add(new Ball(760, 360, 1, 3, 75));
		this.areaWidth = width;
		this.areaHeight = height;
	}

	@Override
	public void tick(double deltaT) {
		for (int i = 0; i < myBalls.size(); i++) {

			double centerX = myBalls.get(i).getCenterX();
			double centerY = myBalls.get(i).getCenterY();
			double velocityX = myBalls.get(i).getVelocityX();
			double velocityY = myBalls.get(i).getVelocityY();
			double radius = myBalls.get(i).getWidth();

			if (centerX < radius || centerX > areaWidth - radius) {
				velocityX *= -1;
				myBalls.get(i).setVelocityX(velocityX);

			}
			if (centerY < radius || centerY > areaHeight - radius) {
				velocityY *= -1;
				myBalls.get(i).setVelocityY(velocityY);
			}
			myBalls.get(i).setCenterX(centerX + velocityX * deltaT);
			myBalls.get(i).setCenterY(centerY + velocityY * deltaT);
		}
	}

	@Override
	public List<Ball> getBalls() {
		return myBalls;
	}
}
