/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Materials;
import edu.up.isgc.cg.raytracer.objects.Object3D;
import edu.up.isgc.cg.raytracer.objects.Transform;

import java.awt.*;

/**
 * @author Jafet Rodríguez
 * @author Braulio Solorio
 */

public abstract class Light extends Object3D {

    private double intensity;

    /**
     * Constructor
     * @param transform where is located the light
     * @param color color of the light
     * @param intensity how much does it iluminates
     */
    public Light(Transform transform, Color color, double intensity) {
        super(transform, Materials.Rough.getMaterial(color));
        setIntensity(intensity);
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * Determines the intensity based on the intersection
     * @param intersection
     * @return
     */
    public abstract double getNDotL(Intersection intersection);

    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}
