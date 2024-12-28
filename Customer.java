
class Customer {
  private String custid;
  private String name, address, contact, email;
  private int points;

  public Customer(String cid, String na, String add, String con, String em) {
    custid = cid;
    name = na;
    address = add;
    contact = con;
    email = em;
    points = 0;
  }

  public String getCustID() {
    return custid;
  }

  public void showDetails() {
    System.out.printf("%-15s %-25s %-40s %-15s %-20s %-15s%n",
        custid, name, address, contact, email, points);
  }

  // public void showDetails() {
  // System.out.printf("%-15s: %s%n", "Customer ID", custid);
  // System.out.printf("%-15s: %s%n", "Name", name);
  // System.out.printf("%-15s: %s%n", "Address", address);
  // System.out.printf("%-15s: %s%n", "Contact No", contact);
  // System.out.printf("%-15s: %s%n", "Email", email);
  // System.out.printf("%-15s: %d%n", "Loyalty Points", points);
  // }

  public void addPoints(int p) {
    points = points + p;
  }
}
