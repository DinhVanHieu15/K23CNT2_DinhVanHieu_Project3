package pkg_default_method;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Vẽ hình tròn");
    }

    public static void main(String[] args) {
        Circle c = new Circle();
        c.draw();            // gọi method abstract đã override
        c.setColor("Đỏ");    // gọi default method từ Shape
    }
}
