package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Vector3D;

/**
 * @author Braulio Solorio
 */
public class Transform {
    private Vector3D position;
    private Vector3D rotation;
    private Vector3D scale;


    public Transform(Vector3D position, Vector3D rotation, Vector3D scale){
        setPosition(position);
        setRotation(rotation);
        setScale(scale);
    }

    public static Transform ZERO(){
        return new Transform(new Vector3D(0,0,0), new Vector3D(0,0,0), new Vector3D(1,1,1));
    }
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Applies rotation, scaling and translation to a vector
     * @param vec
     * @return
     */
    public Vector3D applyLinearTransformation(Vector3D vec){
        Vector3D vector = vec.clone();
        vector.setX(vector.getX() * getScale().getX());
        vector.setY(vector.getY() * getScale().getY());
        vector.setZ(vector.getZ() * getScale().getZ());
        //rotation
        Vector3D angles = new Vector3D(Math.toRadians(getRotation().getX()),Math.toRadians(getRotation().getY()),Math.toRadians(getRotation().getZ()));
        Vector3D cosine = new Vector3D(Math.cos(angles.getX()), Math.cos(angles.getY()), Math.cos(angles.getZ()));
        Vector3D sine = new Vector3D(Math.sin(angles.getX()), Math.sin(angles.getY()), Math.sin(angles.getZ()));

        double rotX = (cosine.getY() * cosine.getZ() * vector.getX()) + (((sine.getX() * sine.getY() * cosine.getZ()) - (cosine.getX()* sine.getZ())) * vector.getY()) + (((cosine.getX() * sine.getY() * cosine.getZ()) + (sine.getX()* sine.getZ())) * vector.getZ());
        double rotY = (cosine.getY() * sine.getZ() * vector.getX()) + (((sine.getX() * sine.getY() * sine.getZ()) + (cosine.getX()* cosine.getZ())) * vector.getY()) + (((cosine.getX() * sine.getY() * sine.getZ()) - (sine.getX()* cosine.getZ())) * vector.getZ());
        double rotZ = (((-sine.getY()) * vector.getX()) + (sine.getX()*cosine.getY() * vector.getY()) + (cosine.getX()*cosine.getY() * vector.getZ()));

        vector.setX(rotX);
        vector.setY(rotY);
        vector.setZ(rotZ);

        //scaling and translation
        vector.setX(vector.getX()+ getPosition().getX());
        vector.setY(vector.getY() + getPosition().getY());
        vector.setZ(vector.getZ() + getPosition().getZ());

        return vector;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public void setRotation(Vector3D rotation) {
        this.rotation = rotation;
    }

    public Vector3D getScale() {
        return scale;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }
}
