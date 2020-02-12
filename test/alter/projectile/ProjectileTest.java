package alter.projectile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ProjectileTest {
    @Test
    public void getX() {

        //given
        Projectile projectile = new Projectile(53.26, 82);  // x = 82 * cos(53.26) * time
        projectile.increaseTime(5);

        //when  --> calls method that we need
        double x = projectile.getX();

        //then
        assertEquals(245.2557, x, 0.0001);  //margin of error


    }
}