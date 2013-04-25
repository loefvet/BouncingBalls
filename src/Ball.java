import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Ball extends Ellipse2D {

	private double x, y, vx, vy, d, xCenter, yCenter, weigth, density = 3.26;
	private Ellipse2D.Double ellipse;
	
	public Ball(double xCenter, double yCenter, double vx, double vy, double d) {
		this.x = xCenter-d;
		this.y = yCenter-d;
		this.vx = vx;
		this.vy = vy;
		this.d = d;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		weigth = 2 * Math.PI * Math.pow(d/2, 2) * density;
		ellipse = new Ellipse2D.Double(x, y, d, d);
		
	}
	
	public Ellipse2D getEllipse(){
		return ellipse;
	}

	@Override
	public double getHeight() {
		return d;
	}

	@Override
	public double getWidth() {
		return d;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public double getCenterX() {
		return x+d/2;
	}

	@Override
	public double getCenterY() {
		return y+d/2;
	}

	public double getVelocityX(){
		return vx;
	}
	
	public double getVelocityY(){
		return vy;
	}
	
	public double getWeigth(){
		return weigth;
	}
	
	public void setVelocityX(double vx){
		this.vx = vx;
	}
	
	public void setVelocityY(double vy){
		this.vy = vy;
	}

	public void setX(double x) {
		this.x = x;
		ellipse.x = x;
	}

	public void setY(double y) {
		this.y = y;
		ellipse.y = y;
	}

	@Override
	public boolean isEmpty() {
		return ellipse.isEmpty();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return ellipse.getBounds2D();
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		ellipse.setFrame(x, y, w, h);
	}
}
