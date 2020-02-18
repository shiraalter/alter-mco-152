package alter.projectile;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Projectile {
    public static final double EARTH_GRAVITY = 9.8;

    private final double angle;
    private final double velocity;
    private double time;

    public Projectile(double angle, double velocity) {
        this.angle = angle;
        this.velocity = velocity;
    }

    public void increaseTime(double timeChange) {
        this.time += timeChange;
    }

    public double getX() {
        return velocity * cos(Math.toRadians(angle)) * time;
    }

    public double getY() {
        return velocity * sin((Math.toRadians(angle))) * time - EARTH_GRAVITY * time * time;
    }

    @Override
    public String toString() {
        return time + ":( " + String.format("%.4f", getX())
                + ',' + String.format("%.4f", getY()) + ')';
    }


}
