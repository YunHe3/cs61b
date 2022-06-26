public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	} 

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i = 0; i < num; ++i) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();

			Planet p = new Planet(xP, yP, xV, yV, mass, img);
			planets[i] = p;
		}
		return planets;
	}

	public static int readNum(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		return num;
	}
	public static void main(String args[]) {
		/** Read the information about this universe.*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = readRadius(fileName);
		Planet[] planets = readPlanets(fileName);
		int num = readNum(fileName);

		/** Drawing the background.*/
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");

		/** Drawing the stars.*/
		for (int i = 0; i < num; i++) {
			planets[i].draw();
		}

		/**Creating an Animation.*/
		StdDraw.enableDoubleBuffering();

		int waitTimeMillionseconds = 10;
		int clock = 0;
		while (clock < T) {
			/** Arrays for forces.*/
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for (int i = 0; i < num; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			StdDraw.clear();
			StdDraw.picture(0,0,"./images/starfield.jpg");
			for (int i = 0; i < num; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
				/** Updatint the picture.*/
				planets[i].draw();
			}	
			StdDraw.show();
			StdDraw.pause(waitTimeMillionseconds);
			clock += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}
}