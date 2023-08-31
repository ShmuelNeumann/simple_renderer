package geometry;

import utils.Vector3;

public class Vertex {
    
    private Vector3 position;

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vertex(Vector3 position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Vertex{position=" +position+"}";
    }

}
