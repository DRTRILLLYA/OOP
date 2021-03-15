import java.util.Scanner;

public class Lab2 {
    public static void main(String args[]){
        System.out.println("Введите координаты трех трехмерных точек в три строками");
        Scanner scan = new Scanner(System.in);
        String C1 = scan.nextLine();
        String C2 = scan.nextLine();
        String C3 = scan.nextLine();

        double x1 = Double.parseDouble(C1.split(" ")[0]);
        double y1 = Double.parseDouble(C1.split(" ")[1]);
        double z1 = Double.parseDouble(C1.split(" ")[2]);
        double x2 = Double.parseDouble(C2.split(" ")[0]);
        double y2 = Double.parseDouble(C2.split(" ")[1]);
        double z2 = Double.parseDouble(C2.split(" ")[2]);
        double x3 = Double.parseDouble(C3.split(" ")[0]);
        double y3 = Double.parseDouble(C3.split(" ")[1]);
        double z3 = Double.parseDouble(C3.split(" ")[2]);

        Point3d point1 = new Point3d(x1, y1, z1);
        Point3d point2 = new Point3d(x2, y2, z2);
        Point3d point3 = new Point3d(x3, y3, z3);
        scan.close();

        if (point1.isEqual(point2) || point2.isEqual(point3) || point1.isEqual(point3))
        {
            System.out.println("Вы ввели одинаковые точки. Попробуйте снова");
        }
        else
        {
            System.out.printf("Площадь треугольника с координатами вершин (%.3f; %.3f; %.3f)" +
                            " (%.3f; %.3f; %.3f)" +
                            " (%.3f; %.3f; %.3f) равна %.3f",
                    point1.getX(), point1.getY(), point1.getZ(),
                    point2.getX(), point2.getY(), point2.getZ(),
                    point3.getX(), point3.getY(), point3.getZ(),
                    Lab2.computeArea(point1, point2, point3));
        }
    }

    public static double computeArea (Point3d point1, Point3d point2, Point3d point3){
        double halfPerimeter = (point1.distanceTo(point2) + point2.distanceTo(point3) + point3.distanceTo(point1)) / 2;
        return Math.sqrt(halfPerimeter *
                (halfPerimeter - point1.distanceTo(point2)) *
                (halfPerimeter - point2.distanceTo(point3)) *
                (halfPerimeter - point3.distanceTo(point1)));
    }
}