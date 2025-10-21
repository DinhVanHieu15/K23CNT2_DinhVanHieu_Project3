package ioc;

class IoCService {
    public void serve() {
        System.out.println("Service is serving");
    }
}

class IoCClient {
    private IoCService iocService;

    // Dùng DI (constructor injection) để truyền dependency vào
    public IoCClient(IoCService service) {
        this.iocService = service;
    }

    public void doSomething() {
        iocService.serve();
    }
}

public class IoCWithDI {
    public static void main(String[] args) {
        // Tạo service và inject vào client
        IoCService service = new IoCService();
        IoCClient client = new IoCClient(service);
        client.doSomething();
    }
}
