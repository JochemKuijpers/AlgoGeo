package nl.tue.algogeo.gen;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 a) {
        this.x = a.x;
        this.y = a.y;
    }

    public Vector2 add(Vector2 a) {
        Vector2 r = new Vector2(this);
        r.x += a.x;
        r.y += a.y;
        return r;
    }

    public Vector2 subtract(Vector2 a) {
        Vector2 r = new Vector2(this);
        r.x -= a.x;
        r.y -= a.y;
        return r;
    }

    public Vector2 addMultiple(Vector2 a, double s) {
        Vector2 r = new Vector2(this);
        r.x += a.x * s;
        r.y += a.y * s;
        return r;
    }

    public double dot(Vector2 a) {
        return this.x * a.x + this.y * a.y;
    }

    public double lengthSqr() {
        return this.x * this.x + this.y * this.y;
    }

    public double length() {
        return Math.sqrt(this.lengthSqr());
    }

    public Vector2 normalized() {
        double length = this.length();
        return this.addMultiple(this, -length + 1);
    }
}
