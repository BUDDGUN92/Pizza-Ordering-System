import java.time.LocalDate;
import java.util.*;

class MainMenu {

  List<Customer> customerList;
  List<Pizza> pizzaList;
  List<Order> orderList;

  public MainMenu() {
    customerList = new ArrayList<Customer>();
    pizzaList = new ArrayList<Pizza>();
    orderList = new ArrayList<Order>();

  }

  public void seedPizzas() {
    Pizza t1 = new Pizza(1, "Neapolitan Pizza",
        "Neapolitan is the original pizza. This delicious pie dates all the way back to the 18th century in Naples, Italy.",
        3200);
    pizzaList.add(t1);
    Pizza t2 = new Pizza(2, "Chicago Style Pizza",
        "Chicago pizza, also called deep-dish pizza, gets its name from the city it was invented in. During the early 1900s",
        3600);
    pizzaList.add(t2);
    Pizza t3 = new Pizza(3, "New York Style Pizza",
        "With its characteristic large, foldable slices and crispy outer crust, New York pizza is one of America’s most famous regional pizza types.",
        4120);
    pizzaList.add(t3);
    System.out.println("*** Pizzas were seeded successfully ***");
  }

  public void seedCustomer(){
    Customer o1 = new Customer("1234", "John Doe", "123 Main St", "1234567890", "jd@email.com");
    customerList.add(o1);
  }

  public void seedOrder(){
    LocalDate today = LocalDate.now();
    Order o1 = new Order(1, 1, "Dummy Order", "1234", 3200, today);
    o1.setSpecialName("dummy1");
    orderList.add(o1);
  }

  public Pizza getPizzaByIndex(int x) {
    try {
      return pizzaList.get(x - 1);
    } catch (Exception e) {
      System.out.println("Invalid Pizza ID: " + e.getMessage());
      return null;
    }
  }

  public void registerCustomer() {
    // this function adds a new customer to the system
    Scanner obj = new Scanner(System.in);// make scanner object for data input
    System.out.print("Enter Customer ID [NIC/PassportNo] ");
    String id = obj.nextLine();
    System.out.print("Enter Name ");
    String na = obj.nextLine();
    System.out.print("Enter Address ");
    String add = obj.nextLine();
    System.out.print("Enter Contact No ");
    String contact = obj.nextLine();
    System.out.print("Enter email  ");
    String email = obj.nextLine();
    Customer c = new Customer(id, na, add, contact, email);
    customerList.add(c); // add customer to list
    System.out.println("*** Customer Registered ***");
  }

  public void displayCustomers() {
    System.out.println("*** List of Customers ***");
    System.out.printf("%-15s %-25s %-40s %-15s %-20s %-15s%n",
        "Customer ID", "Name", "Address", "Contact No", "Email", "Loyalty Points");

    // Printing the table header separator
    for (int i = 0; i < 130; i++) {
      System.out.print("=");
    }
    System.out.println();

    for (Customer c : customerList) {
      c.showDetails();
      for (int i = 0; i < 130; i++) {
        System.out.print("-"); // Printing the table row separator
      }
      System.out.println();
    }
    System.out.println("*** End of Customers ***");
  } // end display customers

  public void displayPizzas() {
    System.out.println("*** List of Pizzas ***");
    for (Pizza t : pizzaList) {
      t.displayPizza();
    }
    System.out.println("*** End of Pizzas ***");
  }

  public void displayOrders() // display orders line by line
  {
    System.out.println("*** List of Orders ***");
    System.out.printf("%-15s %-15s %-15s %-25s %-15s %-15s%n",
        "Order ID", "Pizza ID", "Customer ID", "Order Name", "Order Status", "Order Date");

    // Printing the table header separator
    for (int i = 0; i < 100; i++) {
      System.out.print("=");
    }
    System.out.println();

    for (Order b : orderList) {
      b.display();
    }

    for (int i = 0; i < 100; i++) {
      System.out.print("=");
    }
    System.out.println();
    System.out.println("*** End of Orders ***");
  }

  public Customer searchCustomer(String searchkey) {
    // Search for a Customer by ID

    for (Customer c : customerList) {
      if (searchkey.equals(c.getCustID()))
        return c;
    }
    return null;
  }

  public Order searchOrderID(int searchkey) {
    for (Order b : orderList) {
      if (searchkey == b.getOrderId())
        return b;
    }
    return null;
  }

  private Order searchOrderBySpecialName(String searchkey) {
    for (Order b : orderList) {
      if (searchkey.equals(b.getSpecialName()))
        return b;
    }
    return null;
  }

  public void placeOrder() {
    LocalDate today = LocalDate.now();
    System.out.println("Order Date: " + today);
    Scanner obj = new Scanner(System.in);// make scanner object for data input
    System.out.print("Enter Order ID  ");
    int oid = obj.nextInt();

    System.out.print("Enter Customer ID [NIC/PassportNo] ");
    obj = new Scanner(System.in);
    String cid = obj.nextLine();
    Customer c = searchCustomer(cid);
    if (c == null)
      System.out.println("Customer does not exist");
    else {
      System.out.println("Customer Details");
      System.out.println("---------------");
      c.showDetails(); // show customer details

      System.out.println("Select a Pizza");
      displayPizzas();
      System.out.print("Enter Pizza ID [1|2|3]");
      int pizzaId = obj.nextInt();
      Pizza p = getPizzaByIndex(pizzaId);
      p.displayDetails();
      Order order = new Order(oid, pizzaId, p.getName(), cid, p.getCost(), today);
      System.out.println("--- Extra Features ---");
      System.out.print("Sausage Crust [Y/N] :");
      String answer;
      obj = new Scanner(System.in);
      answer = obj.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        Feature f1 = new Feature("Sausage Crust", 2500);
        order.addFeature(f1);
      }
      System.out.print("Extra Pepparoni  [Y/N] :");
      answer = obj.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        Feature f2 = new Feature("Extra Pepparoni", 1500);
        order.addFeature(f2);
      }
      System.out.print("Extra Cheese [Y/N] :");
      answer = obj.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        Feature f3 = new Feature("Extra Cheese", 3500);
        order.addFeature(f3);
      }
      System.out.print("Store Pickup? [Y/N] :");
      answer = obj.nextLine();
      if (answer.equals("N") || answer.equals("n")) {
        order.setDeliveryCost(250);
      }

      System.out.print("You want to name this pizza for future reference [Y/N]:");
      answer = obj.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        System.out.print("Enter Special name for the pizza :");
        String sn = obj.nextLine();
        order.setSpecialName(sn);
      }

      System.out.println("*** Review of the Complete Pizza ***");
      order.reviewOrder();

      int finalamt = order.getFinalAmount();
      System.out.println("Total Payment Due " + finalamt);
      System.out.println("Payment Method");
      System.out.println("1 - Paypal");
      System.out.println("2 - Credit Card");
      System.out.print("Enter Choice :");
      int opt = obj.nextInt();
      if (opt == 1) {
        System.out.print("Enter Customer Email     :");
        obj = new Scanner(System.in);
        String email = obj.nextLine();
        System.out.print("Enter Customer Password  :");
        String pw = obj.nextLine();
        PaypalStrategy strategy = new PaypalStrategy(email, pw);
        strategy.pay(finalamt);
      } else if (opt == 2) {
        System.out.print("Enter Credit Card No      :");
        int ccno = obj.nextInt();
        CreditCardStrategy strategy = new CreditCardStrategy(ccno);
        strategy.pay(finalamt);
      }

      System.out.print("Confirm Order [Y/N] :");
      obj = new Scanner(System.in);
      answer = obj.nextLine();
      if (answer.equals("Y") || answer.equals("y")) {
        orderList.add(order);

        // add loyalty points to customer
        final int price = order.getFinalAmount();
        int loyalPoints = 0;
        if (price >= 10000) {
          loyalPoints = 5;
        } else if (price >= 5000) {
          loyalPoints = 3;
        } else if (price >= 3000) {
          loyalPoints = 1;
        }

        c.addPoints(loyalPoints);
        System.out.println(loyalPoints + " Loyalty Points Added");
        System.out.println("Order Created !");
        System.out.println("*********************");
      }
    }
  }

  public void placeOrder(boolean isReorder, Order order) {
    LocalDate today = LocalDate.now();
    Scanner obj = new Scanner(System.in);
    Order newOrder = new Order(orderList.size()+1, order.getPizzaId(), order.getName(), order.getCustomerId(),
        order.getFinalAmount(), today);

    System.out.println("*** Review of the Complete Pizza ***");
    newOrder.reviewOrder();

    int finalamt = newOrder.getFinalAmount();
    System.out.println("Total Payment Due " + finalamt);
    System.out.println("Payment Method");
    System.out.println("1 - Paypal");
    System.out.println("2 - Credit Card");
    System.out.print("Enter Choice :");
    int opt = obj.nextInt();
    if (opt == 1) {
      System.out.print("Enter Customer Email     :");
      // obj = new Scanner(System.in);
      String email = obj.nextLine();
      System.out.print("Enter Customer Password  :");
      String pw = obj.nextLine();
      PaypalStrategy strategy = new PaypalStrategy(email, pw);
      strategy.pay(finalamt);
    } else if (opt == 2) {
      System.out.print("Enter Credit Card No      :");
      int ccno = obj.nextInt();
      CreditCardStrategy strategy = new CreditCardStrategy(ccno);
      strategy.pay(finalamt);
    }

    System.out.print("Confirm Order [Y/N] :");
    // obj = new Scanner(System.in);
    String answer;
    answer = obj.nextLine();
    if (answer.equals("Y") || answer.equals("y")) {
      orderList.add(newOrder);

      // add loyalty points to customer
      final int price = newOrder.getFinalAmount();
      int loyalPoints = 0;
      if (price >= 10000) {
        loyalPoints = 5;
      } else if (price >= 5000) {
        loyalPoints = 3;
      } else if (price >= 3000) {
        loyalPoints = 1;
      }

      // Customer customer = order.getCustomerId();
      // Cu.addPoints(loyalPoints);
      System.out.println(loyalPoints + " Loyalty Points Added");
      System.out.println("Order Created !");
      System.out.println("*********************");
    }
  }

  public void updateStatus() {
    System.out.print("Enter Order ID  "); // note: this is like order ID
    Scanner obj = new Scanner(System.in);
    int bid = obj.nextInt();
    Order tb1 = searchOrderID(bid);
    if (tb1 == null)
      System.out.println("Order does not exist");
    else {
      System.out.println("Order Details");
      System.out.println("---------------");
      tb1.updateState();
      tb1.reviewOrder(); // show order details
    }
  }

  public void reorderPastOrder() {
    System.out.print("Enter Order's Special Name:  ");
    Scanner obj = new Scanner(System.in);
    String orderName = obj.nextLine();
    Order order = searchOrderBySpecialName(orderName);
    if (order == null)
      System.out.println("Order does not exist");
    else {
      System.out.println("Order Details");
      System.out.println("---------------");
      order.reviewOrder(); // show order details
      System.out.println("Re-Ordering the Pizza");
      placeOrder(true, order);
    }
  }

  public static void main(String arg[]) {

    MainMenu ts = new MainMenu();
    ts.seedPizzas();
    ts.seedCustomer();
    ts.seedOrder();

    while (true) {
      System.out.println("Pizza Ordering System");
      // System.out.println("1 - Add Pizza");
      System.out.println("1 - Register Customers");
      System.out.println("2 - Display Customers");
      System.out.println("3 - Order Pizza");
      System.out.println("4 - Display Orders");
      System.out.println("5 - Update Order Status");
      System.out.println("6 - Re-Order Past Pizza");
      System.out.println("7 - Exit");
      Scanner obj = new Scanner(System.in);
      System.out.print("Enter Choice :");
      int choice = obj.nextInt();
      if (choice == 1)
        ts.registerCustomer();
      else if (choice == 2)
        ts.displayCustomers();
      else if (choice == 3)
        ts.placeOrder();
      else if (choice == 4)
        ts.displayOrders();
      else if (choice == 5)
        ts.updateStatus();
      else if (choice == 6)
        ts.reorderPastOrder();
      else if (choice == 7)
        break;

    }

  }

}