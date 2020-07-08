package company.Exception;

public class InvalidDDOperationException extends Exception{

    //private String notEnoughMoney = "Not enough money for this product ";

    public InvalidDDOperationException(String mess){super(mess);}
    public String getMessage(){return super.getMessage();}
}
