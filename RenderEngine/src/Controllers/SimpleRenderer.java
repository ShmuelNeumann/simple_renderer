package Controllers;
import java.util.Map;

import display.Display;
import display.Rgb;
import geometry.Camera;
import geometry.Edge;
import geometry.Face;
import geometry.Vertex;
import utils.RenderType;
import utils.ScreenCoord;
import utils.Vector3;
import utils.VectorMath;

public class SimpleRenderer {
    // factory architecture:
    private static SimpleRenderer instance = new SimpleRenderer();
    private SimpleRenderer() {};
    public static SimpleRenderer getInstance() {
        return instance;
    }

    // returns null if the vertex is perpendicular to the lens.
    public ScreenCoord getVertexScreenCoordinates(Vertex vertex, Display display) {

        CameraManager camMan = CameraManager.getInstance();

        // we are calculating the FOV to the horizontal edges

        double lensWidth = 2*camMan.FOCAL_LENGTH*Math.tan(camMan.getCamera().getFieldOfViewAngle());
        double lensRatio = (double)display.getHeight()/(double)display.getWidth();
        double lensHeight = lensRatio * lensWidth;

        //double lensHorizontalAngle = camMan.getCamera().getFieldOfViewAngle();
        //double lensVerticalAngle = Math.atan((lensHeight/2)/camMan.FOCAL_LENGTH);

        Vector3 camToVertex = VectorMath.subtract(vertex.getPosition(), camMan.getCamera().getPosition());

        Vector3 camVerticalPlaneNormal = VectorMath.crossProduct(camMan.getCamera().getForwardDirection(), camMan.getCamera().getUpwardDirection());
        Vector3 camToVertexVerticalComponent = VectorMath.projectionOnSurface(camToVertex, camVerticalPlaneNormal);

        Vector3 camHorizontalPlaneNormal = camMan.getCamera().getUpwardDirection();
        Vector3 camToVertexHorizontalComponent = VectorMath.projectionOnSurface(camToVertex, camHorizontalPlaneNormal);


        // find the angle from the cam upwards vector to the vertical projection of the camToVertex.
        double angleFromCamUpTovertex = VectorMath.angleBetween(camToVertexVerticalComponent, camMan.getCamera().getUpwardDirection());
        
        // check if the vertex is perpendicular to the lens:
        if (Math.abs(angleFromCamUpTovertex - 3.141593) < 1E-4 || Math.abs(angleFromCamUpTovertex) < 1E-4) {
            return null;
        }
        
        // find the vertical distance between the lens centre and the intercept point of the camToVertex
        double verticalIntercept;
        
        if (angleFromCamUpTovertex > Math.PI/2){
            verticalIntercept = -Math.tan(angleFromCamUpTovertex - Math.PI/2)*camMan.FOCAL_LENGTH;
        }
        else {
            verticalIntercept = Math.tan(Math.PI/2 - angleFromCamUpTovertex)*camMan.FOCAL_LENGTH;
        }

        // find the angle from the cam right vector to the horizontal projection of the camToVertex.
        double angleFromCamRightToVertex = VectorMath.angleBetween(camToVertexHorizontalComponent, camVerticalPlaneNormal);
        

        // check if the vertex is perpendicular to the lens:
        if (Math.abs(angleFromCamRightToVertex - 3.141593) < 1E-4 || Math.abs(angleFromCamRightToVertex) < 1E-4) {
            return null;
        }
        
        // find the vertical distance between the lens centre and the intercept point of the camToVertex
        double horizontalIntercept;
        
        if (angleFromCamRightToVertex > Math.PI/2){
            horizontalIntercept = -Math.tan(angleFromCamRightToVertex - Math.PI/2)*camMan.FOCAL_LENGTH;
        }
        else {
            horizontalIntercept = Math.tan(Math.PI/2-angleFromCamRightToVertex)*camMan.FOCAL_LENGTH;
        }

        // find the relative distances from the left edge and the top edge:
        double relativeHorizontalOffset = ((lensWidth/2)+horizontalIntercept)/lensWidth;
        double relativeVerticalOffset = ((lensHeight/2)-verticalIntercept)/lensHeight;

        return new ScreenCoord((int)(relativeHorizontalOffset*display.getWidth()), (int)(relativeVerticalOffset*display.getHeight()));
    }

    public void updateScreenVertices(RenderType renderType, LightManager lightManager, GeometryManager geometry, Display display, Camera cam) {

        display.clear();
        
        if (renderType == RenderType.POINTS) {
            for (Vertex vertex : geometry.getVertices()) {
                ScreenCoord vertexCoord = getVertexScreenCoordinates(vertex, display);
                
                if (vertexCoord == null) { 
                    continue;
                }
                
                System.out.println(vertex);
                for (int x = -2; x < 3; x++) {
                    for (int y = -2; y < 3; y++) {
                        if (vertexCoord.getX()+x >= 0 && vertexCoord.getX()+x < display.getWidth()) {
                            if (vertexCoord.getY()+y >= 0 && vertexCoord.getY()+y < display.getHeight()) {
                                display.setPixel(vertexCoord.getX()+x, vertexCoord.getY()+y, new Rgb(0, 0, 0, 255));

                            }
                        }
                    }
                }
            }

            return;
        }
            
        
        for (Face face : geometry.getFaces()) {
            // skip the vertex if it's not visible
            double angleBetween = VectorMath.angleBetween(VectorMath.subtract(cam.getPosition(), face.getE1().getV1().getPosition()), face.getNormal());

            if (angleBetween > (Math.PI/2)) {
                continue;
            }

            if (renderType == RenderType.EDGES) {
                displayEdge(face.getE1(), display);
                displayEdge(face.getE2(), display);
                displayEdge(face.getE3(), display);
                continue;
            }


            ScreenCoord v1Coord = getVertexScreenCoordinates(face.getE1().getV1(), display);
            ScreenCoord v2Coord = getVertexScreenCoordinates(face.getE1().getV2(), display);
            ScreenCoord v3Coord = getVertexScreenCoordinates(face.getE2().getV2(), display);

        
            if (v1Coord == null || v2Coord == null || v3Coord == null) {
                System.out.println("Skipping perpendicular vertex");
                return;
            }

            int alphaBrightness = 255 - (int)(255 *LightManager.getInstance().getFaceBrightness(face.getNormal()));

            Map<String, ScreenCoord> points = ScreenCoord.getPointsBetweenFace(v1Coord, v2Coord, v3Coord);

            for (ScreenCoord point : points.values()) {
                if (point.getY() >= 0 && point.getY() < display.getHeight() && point.getX() >= 0 && point.getX() < display.getWidth()) {
                    display.setPixel(point.getX(), point.getY(), new Rgb(100, 100, 100, alphaBrightness));

                }
            }

        }

    }


    public void displayEdge(Edge edge, Display display) {

        ScreenCoord coord1 = getVertexScreenCoordinates(edge.getV1(), display);
        ScreenCoord coord2 = getVertexScreenCoordinates(edge.getV2(), display);


        Map<String, ScreenCoord> points = ScreenCoord.getPointsBetween(coord1,coord2);

        for (ScreenCoord point : points.values()) {
            if (point.getY() >= 0 && point.getY() < display.getHeight() && point.getX() >= 0 && point.getX() < display.getWidth()) {
                display.setPixel(point.getX(), point.getY(), new Rgb(100, 100, 100, 255));
            }
        }

    }
}
