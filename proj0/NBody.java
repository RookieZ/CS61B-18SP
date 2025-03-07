public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int line = 0;
        while(!in.isEmpty()) {
            line++;
            in.readLine();
        }
        in.close();
        Planet[] planets = new Planet[line - 2];

        int index = 0;
        in = new In(fileName);
        in.readLine();
        in.readLine();

        while(!in.isEmpty()) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[index] = planet;
            index++;
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double time = 0.0;
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        while(time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++) {
                Planet planet = planets[i];
                xForces[i] = planet.calcNetForceExertedByX(planets);
                yForces[i] = planet.calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(-100, -100, "images/starfield.jpg");

            for(Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
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
