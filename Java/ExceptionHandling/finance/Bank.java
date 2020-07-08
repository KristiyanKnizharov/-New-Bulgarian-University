package finance;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Account> accounts;
    private double limitATM;
    private double limitCashDesk;

    public Bank() {
        accounts = new ArrayList();
    }

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList();
    }

    public Bank(String name, double limitATM, double limitCashDesk) {
        this.name = name;
        this.limitATM = limitATM;
        this.limitCashDesk = limitCashDesk;
        accounts = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        if (!this.accounts.contains(account)) {
            this.accounts.add(account);
        }
    }

    public void deposit(Account account, double funds) throws IllegalArgumentException {
        if (funds <= 0) {
            throw new IllegalArgumentException("Funds must be positive number, but " + funds + " is passed.");
        } else {
            account.setFunds(account.getFunds() + funds);
        }
    }

    public void withdraw(Account account, double funds) throws IllegalArgumentException {
        if (funds < 0) {
            throw new IllegalArgumentException("Funds must be positive number but " + funds + " is passed.");
        } else {
            account.setFunds(account.getFunds() - funds);
        }
    }

    public void withdrawWithLimit(Account account, double funds, double limit) throws LimitExceededException, NotEnoughFundsException {
        if (funds > limit) {
            throw new LimitExceededException(limit, funds);
        } else if (account.getFunds() < funds) {
            throw new NotEnoughFundsException(account.getFunds(), funds);
        } else {
            withdraw(account, funds);
        }
    }

    public void withdrawATM(Account account, double funds) throws LimitExceededException, NotEnoughFundsException {
        withdrawWithLimit(account, funds, limitATM);
    }

    public void withdrawCashDesk(Account account, double funds) throws LimitExceededException, NotEnoughFundsException {
        withdrawWithLimit(account, funds, limitCashDesk);
    }

    public void displayAccounts() {
        for (Account account : this.accounts) {
            System.out.println(account);
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", limitATM=" + limitATM +
                ", limitCashDesk=" + limitCashDesk +
                '}';
    }
}
