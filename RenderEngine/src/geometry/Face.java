package geometry;

import utils.Vector3;

public class Face {
    private Edge e1;
    private Edge e2;
    private Edge e3;

    private Vector3 normal;

    public Face(Edge e1, Edge e2, Edge e3, Vector3 normal) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.normal = normal;
    }

    public Edge getE1() {
        return e1;
    }
    public void setE1(Edge e1) {
        this.e1 = e1;
    }
    public Edge getE2() {
        return e2;
    }
    public void setE2(Edge e2) {
        this.e2 = e2;
    }
    public Edge getE3() {
        return e3;
    }
    public void setE3(Edge e3) {
        this.e3 = e3;
    }
    public Vector3 getNormal() {
        return normal;
    }
    public void setNormal(Vector3 normal) {
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "Face{e1=" + e1 + ", e2=" + e2 + ", e3=" + e3 + ", normal=" + normal + "}";
    }
}
