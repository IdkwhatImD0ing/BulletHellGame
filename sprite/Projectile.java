package sprite;

import java.awt.Image;

import game.Board;

/**
 * Projectile class
 * 
 * @author Bill Zhang
 *
 */
public class Projectile extends Sprite {
    String image;
    private double projectileSpeed;
    private int range;
    private double startx;
    private double starty;
    private double distanceX;
    private double distanceY;
    private double angle;
    private int bulletDamage;
    private Image projectileImage;

    /**
     * Constructor
     * 
     * @param x                position
     * @param y                y position
     * @param futurex          future x position
     * @param futurey          future y position
     * @param projectileSprite sprite image
     * @param range            range of projectile
     * @param speed            speed of projectile
     * @param damage           damage of projectie
     * 
     */
    public Projectile(double x, double y, double futurex, double futurey, String projectileSprite, int range,
            double speed, int damage) {
        super(x, y, null);
        startx = x;
        starty = y;
        distanceX = futurex - x;
        distanceY = futurey - y;
        angle = Math.atan2(distanceY, distanceX);
        image = projectileSprite;
        this.range = range;
        projectileSpeed = speed;
        bulletDamage = damage;

        initProjectile();
    }

    /**
     * Constructor
     * 
     * @param x                position
     * @param y                y position
     * @param futurex          future x position
     * @param futurey          future y position
     * @param projectileSprite sprite image
     * @param range            range of projectile
     * @param speed            speed of projectile
     * @param damage           damage of projectie
     * 
     */
    public Projectile(double x, double y, double futurex, double futurey, Image projectileImage, int range,
            double speed, int damage) {
        super(x, y, null);
        startx = x;
        starty = y;
        distanceX = futurex - x;
        distanceY = futurey - y;
        angle = Math.atan2(distanceY, distanceX);
        this.projectileImage = projectileImage;
        this.range = range;
        projectileSpeed = speed;
        bulletDamage = damage;

        initProjectile2();
    }

    /**
     * Constructor
     * 
     * @param x                position
     * @param y                y position
     * @param angle            of bullet
     * @param projectileSprite sprite image
     * @param range            range of projectile
     * @param speed            speed of projectile
     * @param damage           damage of projectie
     */
    public Projectile(double x, double y, double angle, String projectileSprite, int range, double speed, int damage) {
        super(x, y, null);
        startx = x;
        starty = y;
        this.angle = angle;
        image = projectileSprite;
        this.range = range;
        projectileSpeed = speed;
        bulletDamage = damage;

        initProjectile();
    }

    /**
     * Constructor
     * 
     * @param x                position
     * @param y                y position
     * @param angle            of bullet
     * @param projectileSprite sprite image
     * @param range            range of projectile
     * @param speed            speed of projectile
     * @param damage           damage of projectie
     */
    public Projectile(double x, double y, double angle, Image projectileSprite, int range, double speed, int damage) {
        super(x, y, null);
        startx = x;
        starty = y;
        this.angle = angle;
        this.projectileImage = projectileSprite;
        this.range = range;
        projectileSpeed = speed;
        bulletDamage = damage;

        initProjectile2();
    }

    /**
     * INitializes the projectile
     * 
     */
    private void initProjectile() {

        loadImage(image);
        getImageDimensions();
    }

    /**
     * Iniializes the projectile with a image
     * 
     */
    private void initProjectile2() {

        loadImage(projectileImage);
        getImageDimensions();
    }

    /**
     * Move function for the bullet
     * 
     */
    public void move() {

        x += projectileSpeed * Math.cos(angle);
        y += projectileSpeed * Math.sin(angle);

        if (Math.sqrt(Math.pow(startx - x, 2) + Math.pow(starty - y, 2)) >= range) {
            visible = false;
        }
        if (x < 0 || y < 0 || x > 1920 || y > 1080) {
            visible = false;
        }

    }

    /**
     * Gets the bullet damage
     * 
     * @return the damage of this bullet
     */
    public int getBulletDamage() {
        return bulletDamage;
    }
}
