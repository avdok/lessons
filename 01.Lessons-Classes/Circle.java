// Класс круг можно определить без параметров, можно через радиус (тогда координаты центра нулевые), либо через точку центра и радиус 
//
class Circle extends Shape {

	Point pointA;
	double radius;

	static int count = 0;
	
	Circle() {
		pointA = new Point();
		radius = 1;
		name = "Circle" + count++;
		
	}

	Circle(double radius) {
	
		pointA = new Point();
		this.radius = radius;
		name = "Circle" + count++;
		
	}

	Circle(Point pointA, double radius) {
		this.pointA = pointA;
		this.radius = radius;
		name = "Circle" + count++;
		
	}

	double getPerimeter() {
		
		return 2 * Math.PI * radius;

	}

	double getArea() {
		
		return Math.PI * radius * radius;

	}
}
