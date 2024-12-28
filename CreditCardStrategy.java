
public class CreditCardStrategy extends PaymentStrategy {
  private int cardNumber;

  public CreditCardStrategy(int ccNum) {
    this.cardNumber = ccNum;
  }

  public void pay(int amt) {
    System.out.println(amt + " paid with credit card");
  }

}
