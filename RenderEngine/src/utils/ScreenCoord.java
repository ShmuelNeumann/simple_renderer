package utils;

import java.util.HashMap;
import java.util.Map;

public class ScreenCoord {
    
    private int x;
    private int y;

    public ScreenCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return "ScreenCoord{x=" + x + ", y=" + y + "}";
    }

    public static Map<String, ScreenCoord> getPointsBetween(ScreenCoord v1, ScreenCoord v2) {
        
        Map<String, ScreenCoord> points = new HashMap<String,ScreenCoord>();

        return getPointsBetweenRecursive(points, v1, v2);
        
    }


    private static Map<String, ScreenCoord> getPointsBetweenRecursive(Map<String, ScreenCoord> points, ScreenCoord v1, ScreenCoord v2) {


        int middleX = (int)((v1.x + v2.x) / 2);
        int middleY = (int)((v1.y + v2.y) / 2);

        String key = middleX + "-" + middleY;



        if (points.containsKey(key)) {
            return points;
        }


        ScreenCoord middle = new ScreenCoord(middleX, middleY);
        points.put(key, middle);

        points = getPointsBetweenRecursive(points, v1, middle);
        points = getPointsBetweenRecursive(points, middle, v2);

        return points;


    }

    public static Map<String, ScreenCoord> getPointsBetweenFace(ScreenCoord v1, ScreenCoord v2, ScreenCoord v3) {
        
        Map<String, ScreenCoord> points = new HashMap<String,ScreenCoord>();

        return getPointsBetweenFaceRecursive(points, v1, v2, v3);
        
    }

    private static Map<String, ScreenCoord> getPointsBetweenFaceRecursive(Map<String, ScreenCoord> points, ScreenCoord v1, ScreenCoord v2, ScreenCoord v3) {


        int middleX = (int)((v1.x + v2.x + v3.x) / 3);
        int middleY = (int)((v1.y + v2.y + v3.y) / 3);

        String key = middleX + "-" + middleY;



        if (points.containsKey(key)) {
            return points;
        }


        ScreenCoord middle = new ScreenCoord(middleX, middleY);
        points.put(key, middle);

        points = getPointsBetweenFaceRecursive(points, v1, middle, v2);
        points = getPointsBetweenFaceRecursive(points, v2, middle, v3);
        points = getPointsBetweenFaceRecursive(points, v3, middle, v1);


        return points;


    }
}
