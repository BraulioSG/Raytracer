package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Transform;

import java.awt.*;

/**
 * @author Jafet Rodriguez
 * @author Braulio Solorio
 */
public class PointLight extends Light{

    /**
     * Constructor
     * @param position where is the light located
     * @param color which color is the light
     * @param intensity how much does the light illuminates
     */
    public PointLight(Vector3D position, Color color, double intensity) {
        super(new Transform(position, Vector3D.ZERO(), Vector3D.ONE()), color, intensity);
    }

    /**
     * Returns a number representing the intensity of the light based on the intersection
     * @param intersection intersection from the object with the light
     * @return
     */
    @Override
    public double getNDotL(Intersection intersection) {
        double max = Math.max(Vector3D.dotProduct(intersection.getNormal(),
                Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))), 0.0);

        return max;
    }
}
