public interface OrderState {
    String getState();
    void handle(Order order);
}

class PlacedState implements OrderState {
    @Override
    public void handle(Order order) {
        System.out.println("Order is now placed.");
        order.setStatus(new PreparingState());
    }

    @Override
    public String getState() {
        return "PLACED";
    }
}

class PreparingState implements OrderState {
    @Override
    public void handle(Order order) {
        System.out.println("Order is being prepared.");
        order.setStatus(new PreparedState());
    }

    @Override
    public String getState() {
        return "PREPARING";
    }
}

class PreparedState implements OrderState {
    @Override
    public void handle(Order order) {
        System.out.println("Order is being prepared.");
        order.setStatus(new DeliveredState());
    }

    @Override
    public String getState() {
        return "PREPARED";
    }
}

class DeliveredState implements OrderState {
    @Override
    public void handle(Order order) {
        System.out.println("Order has been delivered.");
        order.setStatus(new CompletedState());
    }

    @Override
    public String getState() {
        return "DELIVERED";
    }
}

class CompletedState implements OrderState {
    @Override
    public void handle(Order order) {
        System.out.println("Order has been completed.");
        order.setStatus(null);
    }

    @Override
    public String getState() {
        return "COMPLETED";
    }
}