package edu.up.isgc.cg.raytracer.objects;

import java.awt.*;

/**
 * @author Braulio Solorio
 * Defines how objects reacts to the light
 */
public class Material {
    private Color color;
    private double shininess;
    private double reflectiveness;
    private double specular;
    private double ambient;
    private double transparency;
    private double refraction;

    /**
     * Constructor
     * @param color color of the object
     * @param specular specular
     * @param ambient how object react to ambient light
     * @param shininess shininess
     * @param reflectiveness how much does the reflection affects 0 <= r <= 1
     * @param transparency how much does th transparency affects 0 <= r <= 1
     * @param refraction refraction index
     */
    public Material(Color color,double specular, double ambient, double shininess, double reflectiveness, double transparency, double refraction){
        setAmbient(ambient);
        setSpecular(specular);
        setColor(color);
        setShininess(shininess);
        setReflectiveness(reflectiveness);
        setTransparency(transparency);
        setRefraction(refraction);
    }

    public double getRefraction() {
        return refraction;
    }

    public void setRefraction(double refraction) {
        this.refraction = refraction;
    }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public double getSpecular() {
        return specular;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getReflectiveness() {
        return reflectiveness;
    }

    public void setReflectiveness(double reflectiveness) {
        this.reflectiveness = reflectiveness;
    }

    /**
     * Multiplies each color channel by a scalar
     * @param color color to be multiplied
     * @param scalar number to be multiplied
     * @return
     */
    public static Color colorByScalar(Color color, double scalar){
        double[] colorAChannels = new double[]{color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0};
        float[] colorBChannels = new float[]{0,0,0};


        for(int i = 0; i < 3; i++){
            colorBChannels[i] = (float)(colorAChannels[i] * scalar);
            if(colorBChannels[i] >= 1) colorBChannels[i] = 1;
            else if(colorBChannels[i] <= 0) colorBChannels[i] = 0;
        }

        return  new Color(colorBChannels[0], colorBChannels[1], colorBChannels[2]);
    }

    /**
     * The sum of two colors A and B
     * @param colorA first color
     * @param colorB second color
     * @return
     */
    public static Color addColors(Color colorA, Color colorB){
        double[] colorAChannels = new double[]{colorA.getRed() / 255.0, colorA.getGreen() / 255.0, colorA.getBlue() / 255.0};
        double[] colorBChannels = new double[]{colorB.getRed() / 255.0, colorB.getGreen() / 255.0, colorB.getBlue() / 255.0};

       double[] colorCChannels = new double[]{0.0,0.0,0.0};

       for(int i = 0; i < 3; i++){
           colorCChannels[i] = colorAChannels[i] + colorBChannels[i];
           if(colorCChannels[i] >= 1) colorCChannels[i] = 1;
           else if(colorCChannels[i] <= 0) colorCChannels[i] = 0;
       }

        return  new Color((float)colorCChannels[0], (float)colorCChannels[1], (float)colorCChannels[2]);
    }

    /**
     * The multiplication by channels of two colors A and B
     * @param colorA first color
     * @param colorB second color
     * @return
     */
    public static Color multiplyColors(Color colorA, Color colorB){
        double[] colorAChannels = new double[]{colorA.getRed() / 255.0, colorA.getGreen() / 255.0, colorA.getBlue() / 255.0};
        double[] colorBChannels = new double[]{colorB.getRed() / 255.0, colorB.getGreen() / 255.0, colorB.getBlue() / 255.0};

        double[] colorCChannels = new double[]{0.0,0.0,0.0};

        for(int i = 0; i < 3; i++){
            colorCChannels[i] = colorAChannels[i] * colorBChannels[i];
            if(colorCChannels[i] >= 1) colorCChannels[i] = 1;
            else if(colorCChannels[i] <= 0) colorCChannels[i] = 0;
        }

        return  new Color((float)colorCChannels[0], (float)colorCChannels[1], (float)colorCChannels[2]);
    }



}
