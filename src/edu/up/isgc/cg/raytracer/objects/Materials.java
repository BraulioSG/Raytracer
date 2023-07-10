package edu.up.isgc.cg.raytracer.objects;

import java.awt.*;

/**
 * @author Braulio Solorio
 * List of pre-defined materials
 */
public enum Materials {
    Rough(0,0.1,0,0,0, 1),
    Glossy(20, 0, 500,0,0, 1),
    Glossy2(10, 0, 10, 0.5, 0,1),
    Glossy3(0, 0, 200, 0.3, 0,1),
    Shiny(2500, 0, 500, 0.5, 0, 1),
    Shine(2500, 0, 500, 0.2, 0, 1),
    GlassOpaque(2500, 0, 500, 0.2, 0.7, 1.5),
    GlassOpaque2(2500, 0, 500, 0.2, 0.5, 1.5),
    Mirror(0,0,0,1,0,0),
    Refractive(0,0, 0, 0,1, 1),
    Glassy(2500,0,500, 0.5,0.9, 1.5),
    Reflective(1,0, 900, 1,0, 1);

    public final Material material;

    private Materials(double specular, double ambient, double shininess, double reflectiveness, double transparency, double refraction){
        this.material = new Material(Color.white, specular, ambient, shininess, reflectiveness, transparency, refraction);
    }

    /**
     * Returns the material parameters with the desired color
     * @param color desired color
     * @return
     */
    public Material getMaterial(Color color){
        return new Material(color, this.material.getSpecular(), this.material.getAmbient(), this.material.getShininess(), this.material.getReflectiveness(), this.material.getTransparency(), this.material.getRefraction());
    }

}
