package finance;

public class LimitExceededException extends Exception {

    private final double limit;
    private final double fundsRequired;

    public LimitExceededException(double limit, double fundsRequired) {
        this.limit = limit;
        this.fundsRequired = fundsRequired;
    }

    public double getLimit() {
        return limit;
    }

    public double getFundsRequired() {
        return fundsRequired;
    }

    @Override
    public String toString() {
        return "LimitExceededException{" + "limit=" + limit + ", fundsRequired=" + fundsRequired + '}';
    }

}
