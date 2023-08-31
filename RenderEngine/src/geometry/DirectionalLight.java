package geometry;

import utils.Vector3;

public class DirectionalLight {
    private Vector3 direction;

    public DirectionalLight(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "DirectionalLight{direction=" + direction + "}";
    }
}
