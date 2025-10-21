package ioc;

class Service {
    public void serve() {
        System.out.println("Service is serving");
    }
}

class Client {
    private Service service;

    // Client tự khởi tạo Service -> phụ thuộc chặt
    public Client() {
        service = new Service();
    }

    public void doSomething() {
        service.serve();
    }
}

public class NonIoC {
    public static void main(String[] args) {
        Client client = new Client();
        client.doSomething();
    }
}

