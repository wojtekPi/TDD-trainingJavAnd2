package bank;

public class PaymentService {

    Account from;
    Account to;
    int howMuch;

    void transferMoney(Account from, Account to, int howMuch){

        if(howMuch > 0 && from != null && to != null){
            from.setBalance(from.getBalance() - howMuch);
            to.setBalance(to.getBalance() + howMuch);
        }
    }
}
