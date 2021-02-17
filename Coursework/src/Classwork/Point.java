package Classwork;

public class Point implements Cloneable{
	
	private int x;
	private int y;
	
	public Point() {
		//Assign x and y default values
		x = y = 0;
	}
	
	public Point(int x, int second) {
		//x = x; both the local variable (parameter) x
		this.x = x; //Assigns local variable to instance variable
		y = second;
	}
	
	//Accessors that return the values of the instance variables
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//Mutators that set the values of the instance variables
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double distance(Point other) {
		double dx = x - other.x;
		double dy = y - other.y;
		
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}
		
		Point other = (Point) obj;
		
		return x == other.x && y == other.y;
	}
	
	@Override
	public Object clone() {
		Point copy;
		
		try {
			copy = (Point) super.clone();
		}
		catch(CloneNotSupportedException e) {
			//Should never happen
			return null;
		}
		
		return copy;
	}
	
	public static void main(String args[]) {
		Point p = new Point(1,2);
		Point q;
		
		q = p;
		
		p.setX(2);
		System.out.println(q.getX()); //Prints 2
		
		q = (Point) p.clone();
		
		p.setX(3);
		System.out.println(q.getX());
		
	}

}
