package utils;

public class SphericalCoords {
    private double r;
    private double phi;
    private double theta;

    public SphericalCoords(double r, double phi, double theta) {
        setR(r);
        setPhi(phi);
        setTheta(theta);
    }

    public double getR() {
        return r;
    }
    public void setR(double r) {
        if (r < 0) {
            this.r = 0;
        }
        else {
            this.r = r;
        }
    }
    public double getPhi() {
        return phi;
    }
    public void setPhi(double phi) {

        if (phi < 0) {
            this.phi = 0;
        }
        else if (phi > Math.PI) {
            this.phi = Math.PI;
        }
        else {
            this.phi = phi;
        }
    }
    public double getTheta() {
        return theta;
    }
    public void setTheta(double theta) {
        while (theta < 0) {
            theta += 2*Math.PI;
        }
        while (theta > Math.PI*2) {
            theta -= 2*Math.PI;
        }

        this.theta = theta;
        
    }


    public static Vector3 polarToVector(SphericalCoords sphericalCoords) {
        double x = sphericalCoords.getR() * Math.sin(sphericalCoords.getPhi()) * Math.cos(sphericalCoords.getTheta());
        double y = sphericalCoords.getR() * Math.sin(sphericalCoords.getPhi()) * Math.sin(sphericalCoords.getTheta());
        double z = sphericalCoords.getR() * Math.cos(sphericalCoords.getPhi());

        return new Vector3(x, y, z);
    }

    @Override
    public String toString() {
        return "SphericalCoords{r=" + r + ", phi=" + phi + ", theta=" + theta + "}";
    }
}
