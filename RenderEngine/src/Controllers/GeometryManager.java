package Controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import geometry.Edge;
import geometry.Face;
import geometry.Vertex;
import utils.Vector3;

public class GeometryManager {
    
    // factory architecture:
    private static GeometryManager instance = new GeometryManager();
    private GeometryManager() {}
    public static GeometryManager getInstance() {
        return instance;
    }

    private Map<Vector3, Vertex> vertices = new HashMap<Vector3, Vertex>();
    private Map<Vertex[], Edge> edges = new HashMap<Vertex[], Edge>();
    private Map<Edge[], Face> faces = new HashMap<Edge[], Face>();


    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getPosition(), vertex);
    }
    public void removeVertex(Vector3 position) {
        vertices.remove(position);
    }
    public Vertex getVertex(Vector3 position) {
        return vertices.get(position);
    }
    public Collection<Vertex> getVertices() {
        return vertices.values();
    }


    public void addEdge(Edge edge) {
        Vertex points[] = new Vertex[2];
        points[0] = edge.getV1();
        points[1] = edge.getV2();
        edges.put(points, edge);
    }
    public void removeEdge(Vertex[] points) {
        edges.remove(points);
    }
    public Edge getEdge(Vertex[] points) {
        return edges.get(points);
    }
    public Collection<Edge> getEdges() {
        return edges.values();
    }


    public void addFace(Face face) {
        Edge faceEdges[] = new Edge[3];
        faceEdges[0] = face.getE1();
        faceEdges[1] = face.getE2();
        faceEdges[2] = face.getE3();

        faces.put(faceEdges, face);
    }
    public void removeFace(Edge[] faceEdges) {
        faces.remove(faceEdges);
    }
    public Face getFace(Edge[] faceEdges) {
        return faces.get(faceEdges);
    }
    public Collection<Face> getFaces() {
        return faces.values();
    }
}
