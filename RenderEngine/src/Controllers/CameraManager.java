package Controllers;

import display.Display;
import display.Keyboard;
import geometry.Camera;
import utils.SphericalCoords;
import utils.Vector3;
import utils.VectorMath;

public class CameraManager {

    Vector3 cameraTarget = VectorMath.zero();
    SphericalCoords cameraRot = new SphericalCoords(4, Math.PI/2 , (Math.PI * 3)/2);

    double cameraMovementSpeed = 0.1;
    


    // this is the length from the camera to the virtual lens.
    // The actual value should not impact the calculations
    public final double FOCAL_LENGTH = 10;

    // factory architecture:
    private static CameraManager instance;
    private CameraManager(){};

    public static CameraManager getInstance() {
        if (instance == null) {
            instance = new CameraManager();
        }
        return instance;
    }

    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void updateCamPos(LightManager lightManager, Keyboard keyboard, Display display) {

        // move the focus point:

        // if (keyboard.isUP_HELD()) {
        //     cameraTarget.setY(cameraTarget.getY() + cameraMovementSpeed);
        // }
        // else if (keyboard.isDOWN_HELD()) {
        //     cameraTarget.setY(cameraTarget.getY() - cameraMovementSpeed);
        // }

        // else if (keyboard.isRIGHT_HELD()) {
        //     cameraTarget.setX(cameraTarget.getX() + cameraMovementSpeed);
        // }
        // else if (keyboard.isLEFT_HELD()) {
        //     cameraTarget.setX(cameraTarget.getX() - cameraMovementSpeed);
        // }

        // else if (keyboard.isSHIFT_HELD()) {
        //     cameraTarget.setZ(cameraTarget.getZ() + cameraMovementSpeed);
        // }
        // else if (keyboard.isCONTROL_HELD()) {
        //     cameraTarget.setZ(cameraTarget.getZ() - cameraMovementSpeed);
        // }


        if (keyboard.isQ_HELD()) {
            cameraRot.setR(cameraRot.getR() - cameraMovementSpeed);
        }
        else if (keyboard.isE_HELD()) {
            cameraRot.setR(cameraRot.getR() + cameraMovementSpeed);
        }
        else if (keyboard.isW_HELD()) {
            cameraRot.setPhi(cameraRot.getPhi() - cameraMovementSpeed);
        }
        else if (keyboard.isS_HELD()) {
            cameraRot.setPhi(cameraRot.getPhi() + cameraMovementSpeed);
        }
        else if (keyboard.isA_HELD()) {
            cameraRot.setTheta(cameraRot.getTheta() - cameraMovementSpeed);
        }
        else if (keyboard.isD_HELD()) {
            cameraRot.setTheta(cameraRot.getTheta() + cameraMovementSpeed);
        }
        else {
            return;
        }




        Vector3 newCamPos = VectorMath.add(cameraTarget, SphericalCoords.polarToVector(cameraRot));
        Vector3 newCamForward = VectorMath.scalarProduct(SphericalCoords.polarToVector(cameraRot), -1);



        SphericalCoords newCamUpwardRot;


        if (cameraRot.getPhi() > Math.PI/2) {
            newCamUpwardRot = new SphericalCoords(cameraRot.getR(), cameraRot.getPhi() - Math.PI/2, cameraRot.getTheta());
        }
        else {
            newCamUpwardRot = new SphericalCoords(cameraRot.getR(),  -(cameraRot.getPhi() - Math.PI/2), cameraRot.getTheta() - Math.PI);
        }

        Vector3 newCamUpward = VectorMath.unitVector(SphericalCoords.polarToVector(newCamUpwardRot));    

        camera.setPosition(newCamPos);
        camera.setForwardDirection(newCamForward);
        camera.setUpwardDirection(newCamUpward);

        SimpleRenderer.getInstance().updateScreenVertices(lightManager, GeometryManager.getInstance(), display, camera);


        
    }

}