/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Transform;

import java.awt.*;

/**
 * @author Jafet Rodríguez
 * @author Braulio Solorio
 */
public class DirectionalLight extends Light{
    private Vector3D direction;

    /**
     * Constructor
     * @param direction where does the light points to
     * @param color color of the light
     * @param intensity how much does de light illuminate
     */
    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(new Transform(Vector3D.ZERO(), Vector3D.ZERO(), Vector3D.ONE()), color, intensity);
        setDirection(Vector3D.normalize(direction));
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }
}
