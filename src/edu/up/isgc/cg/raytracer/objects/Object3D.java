/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.lights.Light;

import java.awt.*;

/**
 * @author Braulio Solorio
 * @author Jafet Rodríguez
 */
public abstract class Object3D implements IIntersectable{
    private Material material;
    private Transform transform;

    public Object3D(Transform transform, Material material) {
        setTransform(transform);
        setMaterial(material);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Color getColor() {
        return getMaterial().getColor();
    }

    public void setColor(Color color) {getMaterial().setColor(color);
    }

    public Vector3D getPosition() {
        return getTransform().getPosition();
    }


}
