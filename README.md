# Assignment 3: Bridge Pattern

- **Student:** Muratbek Akyl
- **Group:** SE-2523
- **Topic:** Option A (Drawing)
- **Repository URL:** https://github.com/muratbek-akyl/SDP_Assignment3
- **Base Commit Hash:** c178fa274853e3a96ddf2202e09df605497ec72b

---

## Role Mapping

| Role | Name | Source Path |
| --- | --- | --- |
| Abstraction | `Shape` | `src/shape/Shape.java` |
| Refined Abstraction 1 (A1) | `Circle` | `src/shape/Circle.java` |
| Refined Abstraction 2 (A2) | `Square` | `src/shape/Square.java` |
| Implementor | `Renderer` | `src/renderer/Renderer.java` |
| Concrete Implementor 1 (I1) | `VectorRenderer` | `src/renderer/VectorRenderer.java` |
| Concrete Implementor 2 (I2) | `RasterRenderer` | `src/renderer/RasterRenderer.java` |
| Concrete Implementor 3 (I3) | `AsciiRenderer` | `src/renderer/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

---

## Key Design Elements

- **Bridge Field:** `protected Renderer renderer;` in `src/shape/Shape.java`. The base class retains an interface-typed reference initialized via its constructor.
- **`execute()` Operation:** Declared abstract as `public abstract String execute();` in `src/shape/Shape.java` and implemented in `src/shape/Circle.java` (calling `renderer.renderCircle(radius)`) and `src/shape/Square.java` (calling `renderer.renderSquare(side)`).
- **`setImplementation(...)` Method:** Defined in `src/shape/Shape.java` as `public void setImplementation(Renderer renderer)` to allow runtime replacement of the implementor.
- **T5 Runtime Switch Check:** Located in `src/Main.java`, verifying object identity (`==`), domain data preservation (`id` and `radius`), and immediate behavior change across implementors.

---

## Standard Build and Run Commands

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

---

## Expected Outcomes (T1–T7)

```
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | sameObject=true | stateUnchanged=true
 before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
SUMMARY: 7/7 PASS
```
