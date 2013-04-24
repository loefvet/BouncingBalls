import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private List<Ball> myBalls = new LinkedList<Ball>();

	public DummyModel(double width, double height) {
		myBalls.add(new Ball(450, 220, 50, 36, 50));
		myBalls.add(new Ball(760, 360, 100, 300, 75));
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
			double radius = myBalls.get(i).getWidth();

			if (x < radius || x > areaWidth - radius) {
				velocityX *= -1;
				myBalls.get(i).setVelocityX(velocityX);

			}
			if (y < radius || y > areaHeight - radius) {
				velocityY *= -1;
				myBalls.get(i).setVelocityY(velocityY);
			}

			for (int j = 0; j < myBalls.size(); j++) {
				for (int n = 0; n < myBalls.size(); n++) {

				}
			}

			myBalls.get(i).setX(x + velocityX * deltaT);
			myBalls.get(i).setY(y + velocityY * deltaT);
		}
	}

	@Override
	public List<Ball> getBalls() {
		return myBalls;
	}
}
