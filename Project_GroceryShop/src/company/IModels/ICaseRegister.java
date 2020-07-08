package company.IModels;

import company.Models.*;

public interface ICaseRegister {

    void currClientOnCase(Shop shop, Client client);

    Receipt getReceipt();
}
