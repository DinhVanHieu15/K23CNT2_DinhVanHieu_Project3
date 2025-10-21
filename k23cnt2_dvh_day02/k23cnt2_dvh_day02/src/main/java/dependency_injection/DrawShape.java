package dependency_injection;

public class DrawShape {

    private Shape shape;

    // Constructor injection: truyền dependency từ bên ngoài vào
    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    // Hành động vẽ — gọi phương thức của đối tượng shape đã được inject
    public void Draw() {
        shape.draw();
    }

    public static void main(String[] args) {
        // Inject dependency: truyền CircleShape vào DrawShape
        DrawShape drawShape = new DrawShape(new CircleShape());
        drawShape.Draw();

        // Inject dependency khác: truyền RectangleShape vào
        drawShape = new DrawShape(new RectangleShape());
        drawShape.Draw();
    }
}
