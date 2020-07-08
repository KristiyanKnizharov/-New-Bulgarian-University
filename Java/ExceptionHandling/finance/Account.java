package finance;

public class Account {

    private Customer accountHolder;
    private final String IBAN;
    private double funds;

    public Account(Customer accountHolder, String IBAN) {
        this.accountHolder = accountHolder;
        this.IBAN = IBAN;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getFunds() {
        return funds;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setFunds(double funds) throws IllegalArgumentException {
        if (funds < 0) {
            throw new IllegalArgumentException(
                    "Funds must be positive number, but " + funds + " is passed.");
        } else {
            this.funds = funds;
        }
    }

    @Override
    public String toString() {

        return "Account{" + "accountHolder=" + accountHolder + ", funds=" + funds + '}';
    }

}
