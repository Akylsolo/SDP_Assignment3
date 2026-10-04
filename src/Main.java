import renderer.AsciiRenderer;
import renderer.RasterRenderer;
import renderer.Renderer;
import renderer.VectorRenderer;
import shape.Circle;
import shape.Square;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equals(args[0])) {
            runDemo();
        } else {
            runDemo();
        }
    }

    public static void runDemo() {
        int passed = 0;
        int total = 7;

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Renderer ascii = new AsciiRenderer();

        Circle circleVector = new Circle("shape-1", 2.0, vector);
        String actual1 = circleVector.execute();
        String expected1 = "VECTOR circle radius=2";
        if (checkTest("T1", "Circle + VectorRenderer", expected1, actual1)) {
            passed++;
        }

        Circle circleRaster = new Circle("shape-1", 2.0, raster);
        String actual2 = circleRaster.execute();
        String expected2 = "RASTER circle radius=2";
        if (checkTest("T2", "Circle + RasterRenderer", expected2, actual2)) {
            passed++;
        }

        Square squareVector = new Square("shape-2", 3.0, vector);
        String actual3 = squareVector.execute();
        String expected3 = "VECTOR square side=3";
        if (checkTest("T3", "Square + VectorRenderer", expected3, actual3)) {
            passed++;
        }

        Square squareRaster = new Square("shape-2", 3.0, raster);
        String actual4 = squareRaster.execute();
        String expected4 = "RASTER square side=3";
        if (checkTest("T4", "Square + RasterRenderer", expected4, actual4)) {
            passed++;
        }

        Circle t5Shape = new Circle("shape-switch", 2.0, vector);
        Circle refBefore = t5Shape;
        String idBefore = t5Shape.getId();
        double radiusBefore = t5Shape.getRadius();
        String beforeResult = t5Shape.execute();

        t5Shape.setImplementation(raster);
        Circle refAfter = t5Shape;
        String idAfter = t5Shape.getId();
        double radiusAfter = t5Shape.getRadius();
        String afterResult = t5Shape.execute();

        boolean sameObject = (refBefore == refAfter);
        boolean stateUnchanged = idBefore.equals(idAfter) && radiusBefore == radiusAfter;
        boolean outputsCorrect = "VECTOR circle radius=2".equals(beforeResult) && "RASTER circle radius=2".equals(afterResult);

        if (sameObject && stateUnchanged && outputsCorrect) {
            passed++;
            System.out.println("T5 PASS | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
            System.out.println(" before=" + beforeResult + " | after=" + afterResult);
        } else {
            System.out.println("T5 FAIL | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
            System.out.println(" before=" + beforeResult + " | after=" + afterResult);
        }

        Circle circleAscii = new Circle("shape-3", 2.0, ascii);
        String actual6 = circleAscii.execute();
        String expected6 = "ASCII circle radius=2";
        if (checkTest("T6", "Circle + AsciiRenderer", expected6, actual6)) {
            passed++;
        }

        Square squareAscii = new Square("shape-4", 3.0, ascii);
        String actual7 = squareAscii.execute();
        String expected7 = "ASCII square side=3";
        if (checkTest("T7", "Square + AsciiRenderer", expected7, actual7)) {
            passed++;
        }

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static boolean checkTest(String testId, String target, String expected, String actual) {
        boolean matches = expected.equals(actual);
        if (matches) {
            System.out.println(testId + " PASS | " + target + " | result=" + actual);
        } else {
            System.out.println(testId + " FAIL | " + target + " | expected=" + expected + " | actual=" + actual);
        }
        return matches;
    }
}
