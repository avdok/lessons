public class Classes {
	public static void main(String args[]) {
		System.out.println("Classes");
		

		
		Point somePoint = new Point();
		Point somePoint1 = new Point(5, 10);

		System.out.println("somePoint have coordinates");
		somePoint.getPoint();

		System.out.println("somePoint1 have coordinates");
		somePoint1.getPoint();

		System.out.println("---------------------------");
		System.out.println("Make square:");
	
		Square box = new Square(5);
		System.out.println("with side 5 and perimеter " + box.getPerimeter() + " and area " + box.getArea() + " named " + box.name);

		System.out.println("---------------------------");
		System.out.println("Make another square:");
	
		Square box1 = new Square();
		System.out.println("with side 1 and perimеter " + box1.getPerimeter() + " and area " + box1.getArea() + " named " + box1.name);

		System.out.println("---------------------------");
		System.out.println("Make triangle:");
	
		Triangle tri = new Triangle(5);
		System.out.println("with side 5 and perimеter " + tri.getPerimeter() + " and area " + tri.getArea() + " named " + tri.name);


		System.out.println("---------------------------");
		System.out.println("Make circle:");
		Circle crcl = new Circle(5);
		System.out.println("with radius 5 and perimеter " + crcl.getPerimeter() + " and area " + crcl.getArea() + " named " + crcl.name);

		System.out.println("---------------------------");
		System.out.println("Make four-piece board ");
		Board board = new Board();
		
		board.setShape(crcl, 0);
		board.setShape(tri, 2);
		board.getState();

		System.out.println("---------------------------");
		System.out.println("");

        }
}
