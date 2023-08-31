package geometry;

import utils.Vector3;

public class Camera {
    
    private Vector3 position;

    private Vector3 forwardDirection;
    private Vector3 upwardDirection;

    private double fieldOfViewAngle = Math.PI/4;

    public Camera(Vector3 position, Vector3 forwardDirection, Vector3 upwardDirection) {
        this.position = position;
        this.forwardDirection = forwardDirection;
        this.upwardDirection = upwardDirection;
    }

    public Vector3 getPosition() {
        return position;
    }
    public void setPosition(Vector3 position) {
        this.position = position;
    }
    public Vector3 getForwardDirection() {
        return forwardDirection;
    }
    public void setForwardDirection(Vector3 forwardDirection) {
        this.forwardDirection = forwardDirection;
    }
    public Vector3 getUpwardDirection() {
        return upwardDirection;
    }
    public void setUpwardDirection(Vector3 upwardDirection) {
        this.upwardDirection = upwardDirection;
    }
    public double getFieldOfViewAngle() {
        return fieldOfViewAngle;
    }
    public void setFieldOfViewAngle(int fieldOfViewAngle) {
        this.fieldOfViewAngle = fieldOfViewAngle;
    }

    @Override
    public String toString() {
        return "Cam{position=" + getPosition() +", forwardDirection="+getForwardDirection()+" ,upwardDirection="+getUpwardDirection()+", fieldOfViewAngle="+getFieldOfViewAngle()+"}";
    }
}
