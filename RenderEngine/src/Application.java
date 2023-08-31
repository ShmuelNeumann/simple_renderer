import Controllers.CameraManager;
import Controllers.GeometryManager;
import Controllers.LightManager;
import Controllers.SimpleRenderer;
import display.Display;
import display.Keyboard;
import geometry.Camera;
import geometry.DirectionalLight;
import geometry.primitives.Cube;
import utils.RenderType;
import utils.Vector3;
import utils.VectorMath;

public class Application {

    public static void main(String[] args) {

        // Change the render mode
        RenderType renderType = RenderType.FACES;

        // initialise display and keyboard
        Keyboard keyboard = new Keyboard();
        Display display = new Display(keyboard);

        // initialise camera
        Vector3 camPos = new Vector3(0, -4, 0);
        Camera cam = new Camera(camPos, VectorMath.forward(), VectorMath.upward());
        CameraManager cameraManager = CameraManager.getInstance();
        cameraManager.setCamera(cam);

        // initialise light
        LightManager lightManager = LightManager.getInstance();
        DirectionalLight light = new DirectionalLight(new Vector3(-1, -2, -1));
        lightManager.setLight(light);

        // initialise the geometry
        GeometryManager geometry = GeometryManager.getInstance();


        // Add a primative cube
        Cube.generate(geometry, new Vector3(0, 0, 0), 1);


        // Add a second smaller cube
        // Cube.generate(geometry, new Vector3(0, -0.75, 0), 0.5);

        
        // set the initial frame
        SimpleRenderer.getInstance().updateScreenVertices(renderType, lightManager, geometry, display, cam);


        // for each frame, check for user input and update the display if required
        while(true) {
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                System.err.println(e);
            }
            CameraManager.getInstance().updateCamPos(renderType, lightManager, keyboard, display);
        }
    }
}