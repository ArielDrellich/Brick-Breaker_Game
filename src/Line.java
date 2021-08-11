import java.util.List;

/**
 *
 * @author Ariel Drellich 328935275
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Line start and end points.
     * @param start start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Line start and end points.
     * @param x1 start x
     * @param y1 start y
     * @param x2 end x
     * @param y2 end y
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return returns the middle point of the line.
     */
    public Point middle() {
        return new Point((this.end.getX() + this.start.getX()) / 2, (this.end.getY() + this.start.getY()) / 2);
    }

    /**
     * @return returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculates slope of a line.
     * @return returns slope of line
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * finds maximun x and y on line segment.
     * @return returns max x and y on line segment
     */
    public Point maxPoint() {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        return new Point(maxX, maxY);
    }

    /**
     * finds minimun x and y on line segment.
     * @return returns min x and y on line segment
     */
    public Point minPoint() {
        double minX = Math.min(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        return new Point(minX, minY);
    }

    /**
     * check if two lines are intersecting.
     * @param other line to check if they intersect
     * @return returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * finds intersection point of two lines. has special case for vertical lines.
     * @param other line to check where is intersecting with
     * @return returns the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            if ((other.start().getX() <= this.start.getX() && other.end.getX() >= this.start.getX())
                    || (other.start().getX() >= this.start.getX() && other.end.getX() <= this.start.getX())) {
                double yOnLine = other.slope() * (this.start.getX() - other.start.getX()) + other.start.getY();
                if (yOnLine >= this.start.getY() && yOnLine <= this.end.getY()
                        || yOnLine <= this.start.getY() && yOnLine >= this.end.getY()) {
                    return new Point(this.start.getX(), yOnLine);
                        }
            }
        }
        if (other.start.getX() == other.end.getX() && this.start.getX() != this.end.getX()) {
            if ((this.start().getX() >= other.start.getX() && this.end.getX() <= other.start.getX())
                    || (this.start().getX() <= other.start.getX() && this.end.getX() >= other.start.getX())) {
                double yOnLine = this.slope() * (other.start.getX() - this.start.getX()) + this.start.getY();
                if (yOnLine >= other.start.getY() && yOnLine <= other.end.getY()
                        || yOnLine <= other.start.getY() && yOnLine >= other.end.getY()) {
                    return new Point(other.start.getX(), yOnLine);
                        }
            }
        }
        if (this.slope() == other.slope()) {
            return null;
        }
        double intersectX = (this.slope() * this.start.getX() - other.slope() * other.end.getX() + other.end.getY()
                - this.start.getY()) / (this.slope() - other.slope());
        double intersectY = ((this.slope() * intersectX) - (this.slope() * this.start.getX())) + this.start.getY();
        Point intersectPoint = new Point(intersectX, intersectY);
        if (intersectPoint.getX() <= this.maxPoint().getX() && intersectPoint.getX() >= this.minPoint().getX()
                && intersectPoint.getX() <= other.maxPoint().getX()
                && intersectPoint.getX() >= other.minPoint().getX()) {
            return intersectPoint;
        }
        return null;
    }

    /**
     * checks if two lines are equal.
     * @param other line to check if they're the same
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || ((this.end.equals(other.start)) && (this.start.equals(other.end)));
    }

    /**
     * If this line does not intersect with the rectangle, return null.Otherwise,
     * return the closest intersection point to thestart of the line.
     * @param rect rectangle to check closest intersection with
     * @return Point of closest collsion
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestPoint;
        if (intersectionPoints.size() == 0) {
            return null;
        }
        closestPoint = intersectionPoints.get(0);
        for (int i = 0; i < intersectionPoints.size(); i++) {
            Point point = intersectionPoints.get(i);
            if (point.distance(this.start) < closestPoint.distance(this.start)) {
                closestPoint = point;
            }
        }
        if (closestPoint != null) {
            return closestPoint;
        }
        return null;
    }
}