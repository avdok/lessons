// Класс треугольник можно 
//
class Triangle extends Shape {

	Point pointA;
	Point pointB;
	Point pointC;

	static int count = 0;
	
	Triangle() {
		pointA = new Point();
		pointB = new Point(0, 1);
		pointC = new Point(1, 1);
		name = "Triangle" + count++;
		
	}

	Triangle(double side) {
	
		pointA = new Point();
		pointB = new Point(0, side);
		pointC = new Point(side, side);
		name = "Triangle" + count++;
		
	}

	Triangle(Point pointA, Point pointB, Point pointC) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
		name = "Triangle" + count++;
		
	}

	double getPerimeter() {
		double side1 = getDistance(pointA, pointB);
		double side2 = getDistance(pointB, pointC);
		double side3 = getDistance(pointC, pointA);

		return side1 + side2 + side3;

	}

	double getArea() {
		double area = Math.abs( (pointA.x-pointC.x) * (pointB.y-pointA.y) - (pointA.x-pointB.x) * (pointC.y-pointA.y) ) * 0.5;
		return area;

	}
}
