import box.Box;
import finance.*;
import hotel.Hotel;
import hotel.InsufficientNumberOfRoomsException;
import hotel.RoomIsRentedException;

public class Main {

    public static void main(String[] args) {
        // Task 1 Box
        {
            Box box = new Box(2, 3, 4);
            System.out.println(box);
            double increasePercentage = 300;
            try {
                box.increaseDimensions(increasePercentage);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            System.out.println(box);
            double volumeNumber = 100;
            box.optionalIncreaseDimensions(increasePercentage, volumeNumber);
        }

        // Task 2 Rent Hotel Rooms
        {
            Hotel hotel = new Hotel(5);
            try {
                hotel.rentRoom(2);
                hotel.rentRoom(1);
                hotel.rentRoom(3);
                hotel.rentRoom(4);
                hotel.rentRoom(5);
                hotel.rentRoom(6);
            } catch (RoomIsRentedException | InsufficientNumberOfRoomsException e) {
                e.printStackTrace();
            }
        }

        // Task 3 Bank Accounts
        {
            Bank unicreditBulbank = new Bank("UnicreditBulbank", 800, 5000);
            Customer maria = new Customer("Maria");
            Account mariasAccount = new Account(maria, "UNCR10BG1234567890");
            unicreditBulbank.addAccount(mariasAccount);
            //unicreditBulbank.deposit(mariasAccount, -1000);
            unicreditBulbank.deposit(mariasAccount, 1000);
            unicreditBulbank.displayAccounts();

            try {
                unicreditBulbank.withdrawATM(mariasAccount, 500);
                unicreditBulbank.withdrawATM(mariasAccount, 600);
                unicreditBulbank.withdrawCashDesk(mariasAccount, 6000);
            } catch (LimitExceededException e) {
                e.printStackTrace();
            } catch (NotEnoughFundsException e) {
                e.printStackTrace();
            }
        }
    }
}
