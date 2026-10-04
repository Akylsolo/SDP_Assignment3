package renderer;

public class RasterRenderer implements Renderer {
    @Override
    public String renderCircle(double radius) {
        return "RASTER circle radius=" + formatDimension(radius);
    }

    @Override
    public String renderSquare(double side) {
        return "RASTER square side=" + formatDimension(side);
    }
}
