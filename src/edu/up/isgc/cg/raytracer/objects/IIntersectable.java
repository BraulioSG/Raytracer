/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;

/**
 * @author Jafet Rodríguez
 */
public interface IIntersectable {
    /**
     * Returns the intersection if it was hit by the ray, null if not
     * @param ray ray
     * @return
     */
    Intersection getIntersection(Ray ray);
}
