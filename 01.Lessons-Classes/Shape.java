abstract class Shape {
	
	String name;

	abstract double getPerimeter();
	abstract double getArea();

	double getDistance(Point A, Point B) {
		
		return Math.sqrt( (A.x-B.x)*(A.x-B.x) + (A.y-B.y) * (A.y-B.y) );
	} 
}

