/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;


import edu.up.isgc.cg.raytracer.lights.*;
import edu.up.isgc.cg.raytracer.objects.*;
import edu.up.isgc.cg.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Braulio Solorio
 * @author Jafet Rodríguez
 */
public class Raytracer {

    public static final int TOTAL_BOUNCES = 4;
    public static final int THREADS = 14;
    public static final double FINAL_AMBIENT = 0.0001;

    public static int total_pixels = 0;
    public static int current_pixel = 0;

    public static void main(String[] args) {
        System.out.println(new Date());

        // SCENE 01
        /*
        Scene scene01 = new Scene();
        scene01.setCamera(new Camera(new Transform(new Vector3D(-0.7, 2,-4), new Vector3D(0, 0, 0), Vector3D.ONE()), 120, 120, 1280, 720, 0.6, 50.0));
        scene01.setAmbient(1);
        scene01.addLight(new SpotLight(new Vector3D(-0.7, 8, 1), new Vector3D(0, -1, 0), 30, Color.WHITE, 30));
        scene01.addLight(new SpotLight(new Vector3D(3, 8, 1), new Vector3D(0, -1, 0), 30, Color.WHITE, 30));
        scene01.addLight(new SpotLight(new Vector3D(-4.4, 8, 1), new Vector3D(0, -1, 0), 30, Color.WHITE, 30));
        ObjectParams wall = new ObjectParams("CubeQuad.obj", new Transform(new Vector3D(0,4,2), Vector3D.ZERO(), new Vector3D(10, 6.5, 0.1)), Materials.Rough.getMaterial(Color.darkGray));
        ObjectParams table = new ObjectParams("CubeHigh.obj", new Transform(new Vector3D(0,0,0), Vector3D.ZERO(), new Vector3D(10, 0.5, 2)), Materials.Shiny.getMaterial(Color.RED));
        ObjectParams tp1 = new ObjectParams("SmallTeapot.obj", new Transform(new Vector3D(-2.5,0.5,-0.5), new Vector3D(0, 100, 0), new Vector3D(0.7,0.7, 0.7)), Materials.Shiny.getMaterial(Color.GREEN));
        scene01.addObject(new Sphere(new Vector3D(-1,1.3,0.5), 0.8, Materials.Mirror.getMaterial(Color.CYAN)));
        scene01.addObject(new Sphere(new Vector3D(1,1.3,0.2), 0.7, Materials.Glossy.getMaterial(Color.MAGENTA)));
        scene01.addObject(new Sphere(new Vector3D(0,1.3,-1), 0.7, Materials.Glassy.getMaterial(Color.YELLOW)));
        loadObjectIntoScene(scene01, wall, table, tp1);
         */

        // SCENE 02
        Scene scene02 = new Scene();
        scene02.setCamera(new Camera(new Transform(new Vector3D(0,2.5,-5),Vector3D.ZERO(), Vector3D.ONE()), 120, 120, 1280, 720, 0.6, 50.0));

        scene02.addLight(new PointLight(new Vector3D(0,3,-5), Color.WHITE, 30));
        scene02.addLight(new SpotLight(new Vector3D(0,10, 0), new Vector3D(0,-1,0), 20, Color.WHITE, 50));

        ObjectParams table = new ObjectParams("CubeHigh.obj", new Transform(new Vector3D(0,-1,0), new Vector3D(0,0,0), new Vector3D(3,1,2)), Materials.Shine.getMaterial(new Color(106,	75,	53)));
        ObjectParams wall = new ObjectParams("CubeHigh.obj", new Transform(new Vector3D(0,0,1.3), new Vector3D(0,0,0), new Vector3D(10,10,0.2)), Materials.Rough.getMaterial(Color.WHITE));
        ObjectParams bottle = new ObjectParams("bottle.obj", new Transform(new Vector3D(-1.5,0, 0.5), Vector3D.ZERO(), new Vector3D(1,1.7,1)), Materials.GlassOpaque.getMaterial(Color.darkGray));
        ObjectParams glass = new ObjectParams("glass.obj", new Transform(new Vector3D(1.5,0, 0.5), Vector3D.ZERO(), new Vector3D(1,1.7,1)), Materials.Glassy.getMaterial(Color.WHITE));
        ObjectParams dish = new ObjectParams("dish.obj", new Transform(new Vector3D(0,0.3, -0.5), Vector3D.ZERO(), new Vector3D(1,1,1)), Materials.Shiny.getMaterial(Color.WHITE));
        loadObjectIntoScene(scene02, table, wall, bottle,dish, glass);


        // Scene 03
        /*
        Scene scene03 = new Scene();
        scene03.setCamera(new Camera(new Transform(new Vector3D(-2,4,-5),new Vector3D(10,0,0), Vector3D.ONE()), 120, 120, 1280, 720, 0.6, 50.0));
        //scene03.addLight(new PointLight(new Vector3D(0,1,0), Color.WHITE, 20));

        scene03.addLight(new SpotLight(new Vector3D(-1,7, -3), new Vector3D(2, -7, 3), 35, Color.GREEN, 50));
        scene03.addLight(new SpotLight(new Vector3D(1,7, -3), new Vector3D(0, -7, 3), 35, Color.CYAN, 20));
        scene03.addLight(new SpotLight(new Vector3D(3,7, -3), new Vector3D(-2, -7, 3), 35, Color.BLUE, 50));

        ObjectParams table = new ObjectParams("CubeQuad.obj", new Transform(new Vector3D(0,-1,-1), new Vector3D(0,0,0), new Vector3D(20,1,20)), Materials.Glossy3.getMaterial(new Color(255, 255, 255)));
        ObjectParams wall = new ObjectParams("CubeHigh.obj", new Transform(new Vector3D(0,0,3), new Vector3D(0,0,0), new Vector3D(10,10,0.2)), Materials.Rough.getMaterial(Color.DARK_GRAY));
        ObjectParams pyramid = new ObjectParams("maya.obj", new Transform(new Vector3D(1,0,0), new Vector3D(0,45,0), new Vector3D(1.3,2.3,1.3)), Materials.Shiny.getMaterial(new Color(255,255,255)));

        scene03.addLight(new SpotLight(new Vector3D(-13,7, 3), new Vector3D(2, -7, 3), 30, Color.RED, 50));
        scene03.addLight(new SpotLight(new Vector3D(-11,7, 3), new Vector3D(0, -7, 3), 30, Color.MAGENTA, 20));
    scene03.addLight(new SpotLight(new Vector3D(-9,7, 3), new Vector3D(-2, -7, 3), 30, Color.BLUE, 50));
        ObjectParams boat1 = new ObjectParams("boat.obj", new Transform(new Vector3D(-10,0,7), new Vector3D(0,205,0), new Vector3D(0.7,0.7,0.7)), Materials.Shiny.getMaterial(new Color(255,255,255)));
        ObjectParams boat2 = new ObjectParams("boat.obj", new Transform(new Vector3D(-10,0,5.2), new Vector3D(0,205,0), new Vector3D(0.7,0.7,0.7)), Materials.Shiny.getMaterial(new Color(255,255,255)));
        ObjectParams boat3 = new ObjectParams("boat.obj", new Transform(new Vector3D(-12,0,4), new Vector3D(0,205,0), new Vector3D(0.7,0.7,0.7)), Materials.Shiny.getMaterial(new Color(255,255,255)));

        loadObjectIntoScene(scene03 ,table, pyramid,boat1,boat2,boat3);
         */

        // Scene 04
        /*
        Scene scene04 = new Scene();
        scene04.setCamera(new Camera(new Transform(new Vector3D(0,4,-5),new Vector3D(10,0,0), Vector3D.ONE()), 120, 120, 1280, 720, 0.6, 50.0));
        scene04.addLight(new PointLight(new Vector3D(0, 4.5, -5), Color.WHITE, 50));
        scene04.addLight(new PointLight(new Vector3D(0, 4.5, 5), Color.WHITE, 50));
        scene04.addLight(new PointLight(new Vector3D(5, 4.5, 0), Color.WHITE, 50));
        scene04.addLight(new PointLight(new Vector3D(-5, 4.5, 0), Color.WHITE, 50));

        //scene04.addLight(new PointLight(new Vector3D(0, 2, -1), Color.WHITE, 50));

        ObjectParams table = new ObjectParams("CubeHigh.obj", new Transform(new Vector3D(0,-1,0), new Vector3D(0,0,0), new Vector3D(3,1,2)), Materials.Shine.getMaterial(new Color(85,60,42)));
        ObjectParams cake = new ObjectParams("Cake.obj", new Transform(new Vector3D(0,0,0), new Vector3D(0,0,0), new Vector3D(1.5,2,1.5)), Materials.Shine.getMaterial(Color.WHITE));

        ObjectParams cherry1 = new ObjectParams("Cherry.obj", new Transform(new Vector3D(0,2,-0.5), new Vector3D(0,0,0), new Vector3D(0.5,0.5,0.5)), Materials.Shine.getMaterial(new Color(210, 4, 45)));
        ObjectParams cherry2 = new ObjectParams("Cherry.obj", new Transform(new Vector3D(0,2,0.5), new Vector3D(0,0,0), new Vector3D(0.5,0.5,0.5)), Materials.Shine.getMaterial(new Color(210, 4, 45)));
        ObjectParams cherry3 = new ObjectParams("Cherry.obj", new Transform(new Vector3D(0.5,2,0), new Vector3D(0,0,0), new Vector3D(0.5,0.5,0.5)), Materials.Shine.getMaterial(new Color(210, 4, 45)));
        ObjectParams cherry4 = new ObjectParams("Cherry.obj", new Transform(new Vector3D(-0.5,2,0), new Vector3D(0,0,0), new Vector3D(0.5,0.5,0.5)), Materials.Shine.getMaterial(new Color(210, 4, 45)));

        ObjectParams balloon1 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(-1.7,5,0), new Vector3D(0,10,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.RED));
        ObjectParams balloon2 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(1.7,6,0), new Vector3D(0,30,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.YELLOW));
        ObjectParams balloon3 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(-0.7,5.5,0.5), new Vector3D(0,50,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.GREEN));
        ObjectParams balloon4 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(-3,6,-0.5), new Vector3D(0,70,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.BLUE));
        ObjectParams balloon5 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(3,5,-0.5), new Vector3D(0,90,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.CYAN));
        ObjectParams balloon6 = new ObjectParams("Balloon.obj", new Transform(new Vector3D(0.7,6,0.5), new Vector3D(0,110,0), new Vector3D(0.7,1,0.7)), Materials.Shine.getMaterial(Color.MAGENTA));


        loadObjectIntoScene(scene04, table, cake, cherry1, cherry2, cherry3, cherry4, balloon1, balloon2, balloon3, balloon4, balloon5, balloon6);
        */
        BufferedImage image = raytrace(scene02);
        File outputImage = new File("img-Scene02.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());
    }

    public static class ObjectParams{
        public String path;
        public Transform transform;
        public Material material;

        public ObjectParams(String path, Transform transform, Material material){
            this.path = path;
            this.transform = transform;
            this.material = material;
        }
    }

    /**
     * Reads all the .obj files and add it to the scene
     * @param scene
     * @param objFiles
     */
    public static void loadObjectIntoScene(Scene scene, ObjectParams... objFiles){
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (ObjectParams obj : objFiles){
            Runnable runnable = runnableScene(scene, obj);
            executorService.execute(runnable);
        }
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(10, TimeUnit.MINUTES)){
                executorService.shutdown();
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if(!executorService.isTerminated()){
                System.err.println("Cancel no-finished");
            }

        }
        executorService.shutdown();

    }

    /**
     * Runnable for the load objects
     * @param scene
     * @param params
     * @return
     */
    public static Runnable runnableScene(Scene scene, ObjectParams params){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                addObjToScene(scene, params);
            }
        };
        return runnable;
    }

    /**
     * read an obj and add it to the scene
     * @param scene
     * @param params
     */
    public static synchronized void addObjToScene(Scene scene, ObjectParams params){
        scene.addObject(OBJReader.getModel3D(params.path, params.transform, params.material));
    }

    /**
     * Paints the image pixel by pixel using threads
     * @param mainCamera
     * @param positionsToRaytrace
     * @param objects
     * @param lights
     * @param cameraZ
     * @param nearFarPlanes
     * @param image
     */
    public static void renderPixel(Camera mainCamera, Vector3D[][] positionsToRaytrace, List<Object3D> objects, List<Light> lights, double cameraZ, double[] nearFarPlanes, BufferedImage image){
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        for(int i = 0; i < positionsToRaytrace.length; i++){
            for(int j = 0; j < positionsToRaytrace[0].length; j++){
                Runnable runnable = renderRunnable(i, j, mainCamera, positionsToRaytrace, objects, lights, cameraZ, nearFarPlanes, image);
                executorService.execute(runnable);
            }
        }
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(10, TimeUnit.MINUTES)){
                executorService.shutdown();
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            if(!executorService.isTerminated()){
                System.err.println("Cancel no-finished");
            }

        }
        executorService.shutdown();
    }

    /**
     * Runnable for render pixel
     * @param i
     * @param j
     * @param mainCamera
     * @param positionsToRaytrace
     * @param objects
     * @param lights
     * @param cameraZ
     * @param nearFarPlanes
     * @param image
     * @return
     */
    public static Runnable renderRunnable(int i, int j, Camera mainCamera, Vector3D[][] positionsToRaytrace, List<Object3D> objects, List<Light> lights, double cameraZ, double[] nearFarPlanes, BufferedImage image){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                Intersection closestIntersection = raycast(ray, objects, null, new double[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                Color pixelColor = getIntersectionColor(mainCamera, mainCamera.getPosition(), closestIntersection, objects, lights, TOTAL_BOUNCES);
                paintRay(i, j, image, pixelColor);
            }
        };
        return  runnable;
    }

    /**
     * Given an intersection, calculates its material, reflection and refraction.
     * @param caster
     * @param origin
     * @param intersection
     * @param objects
     * @param lights
     * @param bounces
     * @return
     */
    public static Color getIntersectionColor(Object3D caster, Vector3D origin, Intersection intersection, List<Object3D> objects, List<Light> lights, int bounces){
        Color surface;
        Color ambient = Color.black;
        Color diffuse = Color.black;
        Color specular = Color.black;
        Color phong = Color.black;

        double epsilon = 0.001;
        if(intersection == null || bounces == 0) return phong;

        surface = intersection.getObject().getColor();

        double force = bounces / (double) TOTAL_BOUNCES;

        for(Light light: lights){
            Vector3D lightRayDirection = Vector3D.substract(light.getPosition(), intersection.getPosition());
            Ray lightRay = new Ray(Vector3D.add(intersection.getPosition(), Vector3D.scalarMultiplication(Vector3D.normalize(lightRayDirection), epsilon)), Vector3D.normalize(lightRayDirection));
            double distanceToLight = Vector3D.magnitude(lightRayDirection);
            Intersection lightIntersection = raycast(lightRay, objects, null, null);
            Vector3D lightDirection = Vector3D.substract(intersection.getPosition(), light.getPosition());
            double lightIntensity = light.getIntensity() *  light.getNDotL(intersection) / Math.pow(Vector3D.magnitude(lightDirection), 2.5);

            if(lightIntersection != null){
                if(lightIntersection.getDistance() <= distanceToLight) {
                    Object3D objIntersected = lightIntersection.getObject();
                    if (objIntersected.getMaterial().getTransparency() != 0) {
                        Color transparency = Material.colorByScalar(objIntersected.getColor(), objIntersected.getMaterial().getTransparency());
                        diffuse = Material.multiplyColors(diffuse, transparency);
                    }else{
                        continue;
                    }
                }

            }


            //Ambient
            double ambientIntensity = FINAL_AMBIENT * intersection.getObject().getMaterial().getAmbient();
            ambient = addColor(ambient, Material.colorByScalar(surface, ambientIntensity));

            //diffuse
            double diffuseIntensity = lightIntensity *  Vector3D.dotProduct(Vector3D.normalize(Vector3D.substract(light.getPosition(), intersection.getPosition())), Vector3D.normalize(intersection.getNormal()));
            diffuse = addColor(diffuse, Material.colorByScalar(Material.multiplyColors(light.getColor(), surface), diffuseIntensity));

            //specular
            Vector3D toViewer = Vector3D.substract(caster.getPosition(), intersection.getPosition());
            Vector3D toLight = Vector3D.substract(light.getPosition(), intersection.getPosition());
            Vector3D sum = Vector3D.add(toViewer, toLight);
            Vector3D half = Vector3D.scalarMultiplication(sum, 1 / Vector3D.magnitude(sum));

            double specularIntensity = Math.pow(Vector3D.dotProduct(intersection.getNormal(), half), intersection.getObject().getMaterial().getShininess()) * light.getNDotL(intersection);
            if (intersection.getObject().getMaterial().getShininess() == 0) specularIntensity = 0;
            specular = addColor(specular, Material.colorByScalar(Material.multiplyColors(light.getColor(), surface), intersection.getObject().getMaterial().getSpecular() * specularIntensity));

        }

        if(intersection.getObject().getMaterial().getReflectiveness() > 0)  {

            Vector3D fromOrigin = Vector3D.substract(intersection.getPosition(), caster.getPosition());
            Vector3D normal = Vector3D.normalize(intersection.getNormal());
            Vector3D twoDotNormal = Vector3D.scalarMultiplication(normal, Vector3D.dotProduct(Vector3D.scalarMultiplication(fromOrigin, 2), normal));
            Vector3D reflected = Vector3D.substract(fromOrigin, twoDotNormal);
            Vector3D reflectionRayDirection = Vector3D.normalize(reflected);

            Ray reflectedRay = new Ray(Vector3D.add(intersection.getPosition(), Vector3D.scalarMultiplication(reflectionRayDirection, epsilon)), reflectionRayDirection);
            Intersection reflectedIntersection = raycast(reflectedRay, objects, intersection.getObject(), null);

            Color prev = Material.colorByScalar(diffuse, 1 - intersection.getObject().getMaterial().getReflectiveness());
            Color refC = Material.colorByScalar(getIntersectionColor(intersection.getObject(), intersection.getPosition(), reflectedIntersection, objects, lights, bounces - 1), intersection.getObject().getMaterial().getReflectiveness() * force);

            diffuse = addColor(prev, refC);

        }

        if(intersection.getObject().getMaterial().getTransparency() > 0) {
            Vector3D I = Vector3D.normalize(Vector3D.substract(caster.getPosition(), intersection.getPosition()));
            Vector3D N = Vector3D.normalize(intersection.getNormal());

            double nr = caster.getMaterial().getRefraction() / intersection.getObject().getMaterial().getRefraction();
            double cosT = Vector3D.dotProduct(N,I);

            double scalarN = (nr * cosT) - cosT;
            Vector3D T = Vector3D.substract(Vector3D.scalarMultiplication(N, scalarN), Vector3D.scalarMultiplication(I,nr));
            Vector3D refractedRayDirection = Vector3D.normalize(T);

            Ray refractedRay = new Ray(Vector3D.add(intersection.getPosition(), Vector3D.scalarMultiplication(refractedRayDirection, epsilon)), refractedRayDirection);
            Intersection refractedIntersection = raycast(refractedRay, objects, null, null);

            Color prev = Material.colorByScalar(diffuse, 1 - intersection.getObject().getMaterial().getTransparency());
            Color refC = Material.colorByScalar(getIntersectionColor(intersection.getObject(), intersection.getPosition(), refractedIntersection, objects, lights, bounces - 1), intersection.getObject().getMaterial().getTransparency() * force);

            diffuse = Material.addColors(prev, refC);
        }

        return addColor(phong, addColor(ambient, addColor(diffuse, specular)));
    }

    /**
     * Paints the pixel in the buffered image
     * @param i
     * @param j
     * @param image
     * @param pixelColor
     */
    public synchronized static void paintRay(int i, int j, BufferedImage image, Color pixelColor){
        image.setRGB(i, j, pixelColor.getRGB());
        current_pixel++;
        System.out.printf("(%d%%)\r", current_pixel * 100/total_pixels);
    }

    /**
     * Starts the ray tracer given a scene
     * @param scene
     * @return
     */
    public static BufferedImage raytrace(Scene scene) {
        System.out.println("rendering");
        Camera mainCamera = scene.getCamera();
        total_pixels = mainCamera.getResolutionHeight()* mainCamera.getResolutionWidth();
        double[] nearFarPlanes = mainCamera.getNearFarPlanes();
        double cameraZ = mainCamera.getPosition().getZ();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();


        renderPixel(mainCamera,positionsToRaytrace,objects,lights,cameraZ,nearFarPlanes,image);

        System.out.println();

        return image;
    }

    /**
     * limits the value of something
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float clamp(double value, double min, double max) {
        if (value < min) {
            return (float) min;
        }
        if (value > max) {
            return (float) max;
        }
        return (float) value;
    }

    /**
     * The sum of two colors
     * @param original
     * @param otherColor
     * @return
     */
    public static Color addColor(Color original, Color otherColor) {
        float red = clamp((original.getRed() / 255.0) + (otherColor.getRed() / 255.0), 0, 1);
        float green = clamp((original.getGreen() / 255.0) + (otherColor.getGreen() / 255.0), 0, 1);
        float blue = clamp((original.getBlue() / 255.0) + (otherColor.getBlue() / 255.0), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * Shots a ray in a direction and return the closest intersection or null
     * @param ray
     * @param objects
     * @param caster
     * @param clippingPlanes
     * @return
     */
    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }

}
