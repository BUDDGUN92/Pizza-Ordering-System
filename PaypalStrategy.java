
public class PaypalStrategy extends PaymentStrategy {
	private String emailId;
	private String password;

	public PaypalStrategy(String email, String pwd) {
		this.emailId = email;
		this.password = pwd;
	}

	public void pay(int amt) {
		System.out.println(amt + " paid using Paypal");
	}

}