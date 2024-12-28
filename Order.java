import java.time.LocalDate;
import java.util.*;

class Order {
   private int pizzaId;
   private int orderId;
   private String name;
   private String customer_id;
   private int basic_cost;
   private LocalDate orderDate;
   private OrderState status;
   private String specialName; // customer may give a name to identify the order. This is used for re-ordering
                               // a pizza
   private int deliveryFee;
   private int finalamount;
   private List<Feature> featurelist;

   public Order(int pid, int tid, String tn, String cid, int cost, LocalDate d) {
      pizzaId = pid;
      orderId = tid;
      name = tn;
      customer_id = cid;
      basic_cost = cost;
      orderDate = d;
      status = new PlacedState();
      deliveryFee = 0;
      specialName = null;
      featurelist = new ArrayList<Feature>();
   }

   public void addFeature(Feature f) {
      featurelist.add(f);

   }

   public void setStatus(OrderState state) {
      status = state;
      notifyCustomer(state);
   }

   public void updateState() {
      if (status != null) {
          status.handle(this); // Execute current state logic
      } else {
          System.out.println("Order is already completed.");
      }
  }

   public void setDeliveryCost(int dc) {
      deliveryFee = dc;
   }

   public void setSpecialName(String sn) {
      specialName = sn;
   }

   public String getName() {
      return name;
   }

   public String getSpecialName() {
      return specialName;
   }

   public String getCustomerId(){
      return customer_id;
   }

   // public void display()
   // {
   // System.out.println("Pizza ID :"+pizzaId);
   // System.out.println("Order ID :"+orderId);
   // System.out.println("Customer ID [NIC/Passport]:"+customer_id);
   // System.out.println("Order Name :"+name);
   // System.out.println("Order Status :"+status);
   // System.out.println("Order Date :"+orderDate);
   // System.out.println("------------------");
   // }

   public void display() {
      System.out.printf("%-15s %-15s %-15s %-25s %-15s %-15s %-15s%n",
            pizzaId, orderId, customer_id, name, status.getState(), specialName, orderDate);
   }

   public int getOrderId() {
      return orderId;
   }

   public int getPizzaId() {
      return pizzaId;
   }

   public void reviewOrder() {
      System.out.println("Order ID :" + orderId);
      System.out.println("Customer ID [NIC/Passport]:" + customer_id);
      System.out.println("Order ID :" + orderId);
      System.out.println("Order Name :" + name);
      System.out.println("Basic Cost :" + basic_cost);
      System.out.println("Delivery Cost :" + deliveryFee);
      System.out.println("Order Date :" + orderDate);
      System.out.println("Specail Name of Pizza Order :" + specialName);
      System.out.println("Order Status  :" + status);
      int tot = 0;
      for (Feature f : featurelist) {
         f.displayFeature();
         tot = tot + f.getPrice();
      }
      finalamount = basic_cost + deliveryFee + tot;
      System.out.println("Total cost of the order " + finalamount);
   }

   public int getFinalAmount() {
      return finalamount;
   }

   private void notifyCustomer(OrderState orderState) {
      System.out.println("Customer " + customer_id + " has been notified about the order " + orderId + "'s status: " + orderState.getState());
   }
}
