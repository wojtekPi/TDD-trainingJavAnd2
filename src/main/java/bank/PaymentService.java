package bank;

public class PaymentService {

    Account from;
    Account to;
    int howMuch;

    void transferMoney(Account from, Account to, int howMuch){

        if(from != null && to != null){
            from.setBalance(from.getBalance() - howMuch);
            to.setBalance(to.getBalance() + howMuch);
        }
    }
}
