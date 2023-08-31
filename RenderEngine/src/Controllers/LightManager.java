package Controllers;

import geometry.DirectionalLight;
import utils.Vector3;
import utils.VectorMath;

public class LightManager {

    // factory architecture:
    private static LightManager instance = new LightManager();

    private LightManager() {}
    public static LightManager getInstance() {
        return instance;
    }

    private DirectionalLight light;

    public DirectionalLight getLight() {
        return light;
    }
    public void setLight(DirectionalLight light) {
        this.light = light;
    }

    public double getFaceBrightness(Vector3 normal) {
        double angleBetween = VectorMath.angleBetween(normal, light.getDirection());

        return angleBetween / Math.PI;
    }


}