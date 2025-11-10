package pkg_default_method;

public interface Shape {
    void draw(); // phương thức trừu tượng

    default void setColor(String color) { // default method
        System.out.println("Vẽ hình với màu: " + color);
    }
}
