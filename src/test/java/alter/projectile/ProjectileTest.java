package alter.projectile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ProjectileTest {

    @Test
    public void getX() {

        //given
        Projectile projectile = new Projectile(53.26, 82);  // x = 82 * cos(0.92956236) * 5

        //when  --> calls method that we need
        projectile.increaseTime(5);
        double x = projectile.getX();

        //then
        assertEquals(245.2557, x, 0.0001);  //margin of error
    }

    @Test
    public void getY() {
        //given
        Projectile projectile = new Projectile(53.26, 82);   // 82 * sin(0.92956236) * 5 - 9.8 * 5 * 5;

        //when
        projectile.increaseTime(5);
        double x = projectile.getY();

        //then
        assertEquals(83.556873, x, 0.0001);  //margin of error
    }

    @Test
    public void increaseTime() {
        //given
        Projectile projectile = new Projectile(10, 30); //x = 30 * cos(0.174533) * 1

        /*test getX() with certain time, then multiple time by 2 and see if it changes correctly
         */
        //when
        projectile.increaseTime(1);
        double x = projectile.getX();
        //then
        assertEquals(29.5442, x, 0.0001);

        //when
        projectile.increaseTime(1); //x = 30 * cos(0.174533) * 2
        x = projectile.getX();
        //then
        assertEquals(59.0884, x, 0.0001);   //should be double
    }
}
