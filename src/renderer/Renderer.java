package renderer;

public interface Renderer {
    String renderCircle(double radius);
    String renderSquare(double side);

    default String formatDimension(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
