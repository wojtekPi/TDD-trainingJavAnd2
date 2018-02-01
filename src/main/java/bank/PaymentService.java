package bank;

public class PaymentService {
    public void transferMoney(Account firstAccount, Account secondAccount, int amount){

        firstAccount.setBalance(firstAccount.getBalance() - amount);
        secondAccount.setBalance(secondAccount.getBalance() + amount);
    }

}
