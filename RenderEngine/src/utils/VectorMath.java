package utils;

public class VectorMath {
    public static double magnitude(Vector3 a) {
        return (double)Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2) + Math.pow(a.getZ(), 2));
    }

    public static Vector3 unitVector(Vector3 a) {
        if (magnitude(a) == 0) {
            return null;
        }

        return new Vector3(a.getX()/magnitude(a), a.getY()/magnitude(a), a.getZ()/magnitude(a));
    }

    public static Vector3 forward() {
        return new Vector3(0, 1, 0);
    }
    public static Vector3 backward() {
        return new Vector3(0, -1, 0);
    }
    public static Vector3 right() {
        return new Vector3(1, 0, 0);
    }
    public static Vector3 left() {
        return new Vector3(-1, 0, 0);
    }
    public static Vector3 upward() {
        return new Vector3(0, 0, 1);
    }
    public static Vector3 downward() {
        return new Vector3(0, 0, -1);
    }
    public static Vector3 zero() {
        return new Vector3(0, 0, 0);
    }
    public static double dotProduct(Vector3 a, Vector3 b) {
        return a.getX()*b.getX() + a.getY()*b.getY() + a.getZ()*b.getZ();
    }
    public static Vector3 crossProduct(Vector3 a, Vector3 b) {
        double x = a.getY()*b.getZ() - a.getZ()*b.getY();
        double y = a.getZ()*b.getX() - a.getX()*b.getZ();
        double z = a.getX()*b.getY() - a.getY()*b.getX();
        return new Vector3(x, y, z);
    }
    public static Vector3 scalarProduct(Vector3 a, double s) {
        return new Vector3(a.getX()*s, a.getY()*s, a.getZ()*s);
    }
    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }
    public static Vector3 inverse(Vector3 a) {
        return new Vector3(-a.getX(), -a.getY(), -a.getZ());
    }
    public static Vector3 subtract(Vector3 a, Vector3 b) {
        return new Vector3(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    public static Vector3 projection(Vector3 a, Vector3 b) {
        // projection of a on b
        double scalarProjection = dotProduct(a, b)/magnitude(b);
        return scalarProduct(b, scalarProjection);
    }

    public static Vector3 projectionOnSurface(Vector3 a, Vector3 n) {
        return subtract(a, projection(a, n));
    }
    public static double angleBetween(Vector3 a, Vector3 b) {
        return Math.acos(dotProduct(a, b) / (magnitude(a)*magnitude(b)));
    }
    
}
