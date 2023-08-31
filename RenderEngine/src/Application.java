import Controllers.CameraManager;
import Controllers.GeometryManager;
import Controllers.LightManager;
import Controllers.SimpleRenderer;
import display.Display;
import display.Keyboard;
import geometry.Camera;
import geometry.DirectionalLight;
import geometry.primitives.Cube;
import utils.Vector3;
import utils.VectorMath;

public class Application {

    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard();

        Display display = new Display(keyboard);
        
        //Vector3 camPos = new Vector3(0, -4, 0);
        Vector3 camPos = new Vector3(0, -4, 0);

        Camera cam = new Camera(camPos, VectorMath.forward(), VectorMath.upward());

        CameraManager cameraManager = CameraManager.getInstance();
        cameraManager.setCamera(cam);
        GeometryManager geometry = GeometryManager.getInstance();

        // add all the vertices of a cube
        Cube.generate(geometry, new Vector3(0, 0, 0), 1);


        // add lighting
        LightManager lightManager = LightManager.getInstance();
        DirectionalLight light = new DirectionalLight(new Vector3(-1, -2, -1));
        lightManager.setLight(light);


        //Cube.generate(geometry, new Vector3(0, -0.75, 0), 0.5);

        SimpleRenderer.getInstance().updateScreenVertices(lightManager, geometry, display, cam);


        while(true) {
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                System.err.println(e);
            }
            CameraManager.getInstance().updateCamPos(lightManager, keyboard, display);
        }
    }
}