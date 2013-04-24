import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Ball extends Ellipse2D {

	double x, y, vx, vy, r, xCenter, yCenter;
	
	public Ball(double xCenter, double yCenter, double vx, double vy, double r) {
		this.x = xCenter-r;
		this.y = yCenter-r;
		this.vx = vx;
		this.vy = vy;
		this.r = r;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		
	}

	@Override
	public double getHeight() {
		return r;
	}

	@Override
	public double getWidth() {
		return r;
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
		return xCenter;
	}

	@Override
	public double getCenterY() {
		return yCenter;
	}

	public double getVelocityX(){
		return vx;
	}
	
	public double getVelocityY(){
		return vy;
	}
	
	public void setVelocityX(double vx){
		this.vx = vx;
	}
	
	public void setVelocityY(double vy){
		this.vy = vy;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setCenterX(double xCenter) {
		this.xCenter = xCenter;
	}
	
	public void setCenterY(double yCenter) {
		this.yCenter = yCenter;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.r = w;
		this.xCenter = x+w;
		this.yCenter = y+w;
	}
}
