package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Transform;

import java.awt.*;

public class SpotLight extends Light{
    private Vector3D direction;
    private double angle;

    /**
     * Constructor
     * @param position where is located
     * @param direction what direction does the light points
     * @param angle angle of opening
     * @param color color of the light
     * @param intensity how much does the light illuminates
     */
    public SpotLight(Vector3D position, Vector3D direction, double angle, Color color, double intensity) {
        super(new Transform(position, Vector3D.ZERO(), Vector3D.ONE()), color, intensity);
        setDirection(Vector3D.normalize(direction));
        setAngle(angle);
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Returns a number representing the intensity of the light based on the intersection
     * and the angle between the intersection and the direction, 0 if is not in the cone
     * @param intersection
     * @return
     */
    @Override
    public double getNDotL(Intersection intersection) {
        Vector3D toCaster = Vector3D.substract(intersection.getPosition(), getPosition());
        double angleBetween = Vector3D.dotProduct(toCaster, getDirection()) / (Vector3D.magnitude(toCaster) * Vector3D.magnitude(getDirection()));

        if (angleBetween <= 0) return  0;

        if(angleBetween >= Math.cos(Math.toRadians(getAngle()))) return  angleBetween;
        else return 0;
    }
}
