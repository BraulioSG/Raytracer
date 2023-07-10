/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * @author Braulio Solorio
 * @author Jafet Rodr�guez
 */

public class Camera extends Object3D {
    //FOV[0] = Horizontal | FOV[1] = Vertical
    private double[] fieldOfView = new double[2];
    private double focalPoint = 36.0;
    //0 is width
    //1 is height
    private int[] resolution;
    private double[] nearFarPlanes = new double[2];

    public Camera(Transform transform, double fieldOfViewHorizontal, double fieldOfViewVertical,
                  int widthResolution, int heightResolution, double nearPlane, double farPlane) {
        super(transform, Materials.Rough.getMaterial(Color.black));
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new double[]{nearPlane, farPlane});
    }

    public double[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    private void setNearFarPlanes(double[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }

    public double[] getFieldOfView() {
        return fieldOfView;
    }

    private void setFieldOfView(double[] fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public double getFieldOfViewHorizontal() {
        return fieldOfView[0];
    }

    public void setFieldOfViewHorizontal(double fieldOfViewHorizontal) {
        fieldOfView[0] = fieldOfViewHorizontal;
    }

    public double getFieldOfViewVertical() {
        return fieldOfView[1];
    }

    public void setFieldOfViewVertical(double fieldOfViewVertical) {
        fieldOfView[1] = fieldOfViewVertical;
    }

    public double getFocalPoint() {
        return focalPoint;
    }

    public void setFocalPoint(double defaultZ) {
        this.focalPoint = defaultZ;
    }

    public int[] getResolution() {
        return resolution;
    }

    public int getResolutionWidth() {
        return resolution[0];
    }

    public int getResolutionHeight() {
        return resolution[1];
    }

    private void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    public Vector3D[][] calculatePositionsToRay() {
        //angles
        double maxAngleX = getFieldOfViewHorizontal() / 2.0;
        double maxAngleY = getFieldOfViewVertical() / 2.0;

        //radius
        double maxRadX = getFocalPoint() / (double) Math.cos(Math.toRadians(maxAngleX));
        double maxRadY = getFocalPoint() / (double) Math.cos(Math.toRadians(maxAngleY));

        //positions
        double maxPosX = Math.sin(Math.toRadians(maxAngleX)) * maxRadX;
        double maxPosY = Math.sin(Math.toRadians(maxAngleY)) * maxRadY;
        double minPosX = -maxPosX;
        double minPosY = -maxPosY;


        Vector3D[][] positions = new Vector3D[getResolution()[0]][getResolution()[1]];
        double posZ = getFocalPoint();
        double widthStep = (maxPosX - minPosY) / getResolution()[0];
        double heightStep = (maxPosY - minPosY) / getResolution()[1];

        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                double posX = (minPosX + ((widthStep) * x)) / (getResolutionWidth() / (double)getResolutionHeight());
                double posY = (maxPosY - ((heightStep) * y)) / (getResolutionWidth() / (double)getResolutionHeight());;
                positions[x][y] = getTransform().applyLinearTransformation(new Vector3D(posX, posY, posZ));
            }
        }

        return positions;


    }

    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}
