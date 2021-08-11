package game.geometryLogic;

import java.util.ArrayList;
/**
 * Rectangle for various objects in the game.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft upper left point of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.topLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        this.bottomLine = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.rightLine = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.leftLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     * @param line line to check if intersects with.
     * @return List<Point> returns list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new ArrayList<Point>();
        if (line.intersectionWith(topLine) != null) {
            intersections.add(line.intersectionWith(topLine));
        }
        if (line.intersectionWith(bottomLine) != null) {
            intersections.add(line.intersectionWith(bottomLine));
        }
        if (line.intersectionWith(rightLine) != null) {
            intersections.add(line.intersectionWith(rightLine));
        }
        if (line.intersectionWith(leftLine) != null) {
            intersections.add(line.intersectionWith(leftLine));
        }
        return intersections;
    }

    /**
     * Return the width and height of the rectangle.
     * @return width of rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return height of rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @param point sets upper left point of rectangle to given point.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }
}