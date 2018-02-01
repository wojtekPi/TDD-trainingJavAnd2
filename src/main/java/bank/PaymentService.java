package bank;

public class PaymentService {
    void transferMoney(Account from, Account to, int howMuch) {
        from.setBalance(from.getBalance() - howMuch);
        to.setBalance(to.getBalance() + howMuch);
    }
}
