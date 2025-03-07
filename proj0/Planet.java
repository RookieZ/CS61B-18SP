public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double rSquared = Math.pow(this.calcDistance(p), 2);
        return G * this.mass * p.mass / rSquared;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return dx * this.calcForceExertedBy(p) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return dy * this.calcForceExertedBy(p) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double result = 0;
        for(Planet p : allPlanets) {
            if(this.equals(p)) {
                continue;
            }
            result += calcForceExertedByX(p);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double result = 0;
        for(Planet p : allPlanets) {
            if(this.equals(p)) {
                continue;
            }
            result += calcForceExertedByY(p);
        }
        return result;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
