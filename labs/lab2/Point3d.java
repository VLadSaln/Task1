package labs.lab2;

public class Point3d extends Point2d {

    private double zCoord;

    public Point3d(double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }

    public Point3d() {
        super();
        this.zCoord = 0.0;
    }

    public boolean isEqual(Point3d points1) {
        if (points1.getX() == this.getX() & points1.getY() == this.getY() & points1.getZ() == this.getZ()) {
            return true;
        }
        return false;
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ(double val) {
        zCoord = val;
    }

    public double distanceTo(Point3d points1) {
        return Math.sqrt(Math.pow(this.getX() - points1.getX(), 2) + Math.pow(this.getY() - points1.getY(), 2)
                + Math.pow(this.getZ() - points1.getZ(), 2));
    }

    public static double computeArea(Point3d points1, Point3d points2, Point3d points3) {

        double a = Math.abs(points2.distanceTo(points1));
        double b = Math.abs(points3.distanceTo(points2));
        double c = Math.abs(points1.distanceTo(points3));

        double half_perimeter = (a + b + c) / 2;

        double square = Math.sqrt(half_perimeter * (half_perimeter - a) * (half_perimeter - b) * (half_perimeter - c));
        double scale = Math.pow(10, 2);

        return Math.ceil(square * scale) / scale;

    }
   
}
   