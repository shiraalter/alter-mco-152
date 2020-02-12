package alter.projectile;

import java.util.ArrayList;
import java.util.Random;

public class ProjectileMath {
    public static final int NUM_PROJECTILES = 5;

    public static void main(String[] args) {

        //create random angle and velocity
        Random random = new Random();


        ArrayList<Projectile> list = new ArrayList<>();
        for (int i = 0; i < NUM_PROJECTILES; i++) {   //iterate over all projectiles and calc the time for each one
            list.add(new Projectile(random.nextDouble() * 180, random.nextDouble() * 100));
            /*
            OR:
            double angleInDegrees = random.nextDouble() * 180;
            double velocity = random.nextDouble() * 100;
            list.add(new Projectile(angleInDegrees, velocity));
             */
        }
        for (Projectile projectile : list) {
            for (int i = 0; i < 11; i++) {
                System.out.println("Projectile at time " + projectile.toString());
                projectile.increaseTime(1);

            }
            System.out.println("-------------------");
        }


    }
}

