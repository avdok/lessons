class Square extends Shape {

	Point pointA;
	Point pointB;
	Point pointC;
	Point pointD;

	static int count = 0;
	
	Square() {
		pointA = new Point();
		pointB = new Point(0, 1);
		pointC = new Point(1, 1);
		pointD = new Point(1, 0);
		name = "Square" + count++;
		
	}

	Square(double side) {
	
		pointA = new Point();
		pointB = new Point(0, side);
		pointC = new Point(side, side);
		pointD = new Point(side, 0);
		name = "Square" + count++;
		
	}

	Square(Point pointA, Point pointB, Point pointC, Point pointD) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		name = "Square" + count++;
		
	}

	double getPerimeter() {
		double side = getDistance(pointA, pointB);
		return side * 4;

	}

	double getArea() {
		double side = getDistance(pointA, pointB);
		return side * side;

	}
}
