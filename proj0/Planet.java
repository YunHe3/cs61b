public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx, dy;

		dx = p.xxPos - xxPos;
		dy = p.yyPos - yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p) {
		double dis = calcDistance(p);
		return (Planet.G * mass * p.mass) / (dis * dis);
	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - xxPos;
		return calcForceExertedBy(p) * dx / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - yyPos;
		return calcForceExertedBy(p) * dy / calcDistance(p);
	}

	private boolean equals(Planet p) {
		return this == p;
	}
	public double calcNetForceExertedByX(Planet[] allPlants) {
		double fx = 0;
		for (int i = 0; i < allPlants.length; i++) {
			if (equals(allPlants[i])) {
				continue;
			}
			fx += calcForceExertedByX(allPlants[i]);
		}
		return fx;
	}

	public double calcNetForceExertedByY(Planet[] allPlants) {
		double fy = 0;
		for (int i = 0; i < allPlants.length; i++) {
			if (equals(allPlants[i])) {
				continue;
			}
			fy += calcForceExertedByY(allPlants[i]);
		}
		return fy;
	}

	public void update(double dt, double fX, double fY) {
		xxVel += dt * (fX / mass);
		yyVel += dt * (fY / mass);

		xxPos += dt * xxVel;
		yyPos += dt * yyVel; 
	}

	public void draw() {
		StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
	}

}