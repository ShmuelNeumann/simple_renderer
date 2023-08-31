package geometry.primitives;

import Controllers.GeometryManager;
import geometry.Edge;
import geometry.Face;
import geometry.Vertex;
import utils.Vector3;
import utils.VectorMath;

public class Cube {
    
    public static void generate(GeometryManager geometry, Vector3 centre, double edgeLength) {

        double cornerOffset = (edgeLength/2);

        Vertex v1 = new Vertex(new Vector3(centre.getX() + cornerOffset, centre.getY() + cornerOffset, centre.getZ() + cornerOffset));
        Vertex v2 = new Vertex(new Vector3(centre.getX() - cornerOffset, centre.getY() + cornerOffset, centre.getZ() + cornerOffset));
        Vertex v3 = new Vertex(new Vector3(centre.getX() + cornerOffset, centre.getY() - cornerOffset, centre.getZ() + cornerOffset));
        Vertex v4 = new Vertex(new Vector3(centre.getX() - cornerOffset,  centre.getY() - cornerOffset, centre.getZ() + cornerOffset));
        Vertex v5 = new Vertex(new Vector3(centre.getX() + cornerOffset, centre.getY() + cornerOffset, centre.getZ() - cornerOffset));
        Vertex v6 = new Vertex(new Vector3(centre.getX() - cornerOffset, centre.getY() + cornerOffset, centre.getZ() - cornerOffset));
        Vertex v7 = new Vertex(new Vector3(centre.getX() + cornerOffset,  centre.getY() - cornerOffset, centre.getZ() - cornerOffset));
        Vertex v8 = new Vertex(new Vector3(centre.getX() - cornerOffset,  centre.getY() - cornerOffset, centre.getZ() - cornerOffset));

        geometry.addVertex(v1);
        geometry.addVertex(v2);
        geometry.addVertex(v3);
        geometry.addVertex(v4);
        geometry.addVertex(v5);
        geometry.addVertex(v6);
        geometry.addVertex(v7);
        geometry.addVertex(v8);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v1);
        Edge e4 = new Edge(v3, v4);
        Edge e5 = new Edge(v2, v4);
        Edge e6 = new Edge(v1, v5);
        Edge e7 = new Edge(v3, v5);
        Edge e8 = new Edge(v2, v6);
        Edge e9 = new Edge(v2, v5);
        Edge e10 = new Edge(v5, v6);
        Edge e11 = new Edge(v3, v7);
        Edge e12 = new Edge(v5, v7);
        Edge e13 = new Edge(v7, v8);
        Edge e14 = new Edge(v4, v8);
        Edge e15 = new Edge(v3, v8);
        Edge e16 = new Edge(v6, v8);
        Edge e17 = new Edge(v5, v8);
        Edge e18 = new Edge(v2, v8);



        geometry.addEdge(e1);
        geometry.addEdge(e2);
        geometry.addEdge(e3);
        geometry.addEdge(e4);
        geometry.addEdge(e5);
        geometry.addEdge(e6);
        geometry.addEdge(e7);
        geometry.addEdge(e8);
        geometry.addEdge(e9);
        geometry.addEdge(e10);
        geometry.addEdge(e11);
        geometry.addEdge(e12);
        geometry.addEdge(e13);
        geometry.addEdge(e14);
        geometry.addEdge(e15);
        geometry.addEdge(e16);
        geometry.addEdge(e17);
        geometry.addEdge(e18);


        Face f1 = new Face(e1,e2, e3, VectorMath.upward());
        Face f2 = new Face(e2, e4, e5, VectorMath.upward());
        Face f3 = new Face(e3, e6, e7, VectorMath.right());
        Face f4 = new Face(e8, e9, e10, VectorMath.forward());
        Face f5 = new Face(e1, e6, e9, VectorMath.forward());
        Face f6 = new Face(e7, e11, e12, VectorMath.right());
        Face f7 = new Face(e11, e13, e15, VectorMath.backward());
        Face f8 = new Face(e4, e14, e15, VectorMath.backward());
        Face f9 = new Face(e12, e13, e17, VectorMath.downward());
        Face f10 = new Face(e10, e16, e17, VectorMath.downward());
        Face f11 = new Face(e8, e16, e18, VectorMath.left());
        Face f12 = new Face(e5, e14, e18, VectorMath.left());


        geometry.addFace(f1);
        geometry.addFace(f2);
        geometry.addFace(f3);
        geometry.addFace(f4);
        geometry.addFace(f5);
        geometry.addFace(f6);
        geometry.addFace(f7);
        geometry.addFace(f8);
        geometry.addFace(f9);
        geometry.addFace(f10);
        geometry.addFace(f11);
        geometry.addFace(f12);
    }

}
