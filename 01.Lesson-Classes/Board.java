class Board {
	Shape[] shapes;

	Board() {
		shapes = new Shape[4];
	}

	void getState() {
		double area = 0;
		for (int i = 0; i < shapes.length; i++) {
			if (shapes[i] != null) {
				System.out.println("Number " + i + " part has " + shapes[i].name);
				area = area + shapes[i].getArea();
			}
		}
		System.out.println("Total area: " + area);
	}	

	void setShape(Shape shape, int i) {

		if (i > shapes.length) {
			System.out.println("Wrong part");
		}
		else {

			if (shapes[i] == null) {
				shapes[i] = shape;
				System.out.println("Put " + shape.name + " into " + i + " part of board");
			}
			else {
				System.out.println("Can`t put " + shape.name + " into " + i + " part of board, because it has " + shapes[i].name);
			}
		}
	}

	Shape getShape(int i) {

		if (shapes[i] == null) {
			System.out.println("Can`t get shape from " + i + " part of board, because it is empty");
			return null;
		}
		else {
			Shape templ = shapes[i];
			System.out.println("Get " + shapes[i].name + " from " + i + " part of board");
			shapes[i] = null;
			return templ;
		}

		
	}
}
