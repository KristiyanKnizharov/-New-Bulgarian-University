package finance;

public class NotEnoughFundsException extends Exception {

    private final double accountBalance;
    private final double fundsRequired;

    public NotEnoughFundsException(double accountBalance, double fundsRequired) {
        this.accountBalance = accountBalance;
        this.fundsRequired = fundsRequired;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public double getFundsRequired() {
        return fundsRequired;
    }

    @Override
    public String toString() {
        return "notEnoughFunds{" + "accountBalance="
                + accountBalance + ", fundsRequired="
                + fundsRequired + '}';
    }

}
