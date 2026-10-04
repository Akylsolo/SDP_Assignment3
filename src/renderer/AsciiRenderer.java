package renderer;

public class AsciiRenderer implements Renderer {
    @Override
    public String renderCircle(double radius) {
        return "ASCII circle radius=" + formatDimension(radius);
    }

    @Override
    public String renderSquare(double side) {
        return "ASCII square side=" + formatDimension(side);
    }
}
