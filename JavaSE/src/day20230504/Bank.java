package day20230504;

public class Bank {
    public boolean getMoney(int money) {
        //1.检查账户余额
        int account = getAccount();
        //2.判断余额
        if (account >= money) {
            account = account - money;//从余额中扣除取出的钱
            saveAccount(account);//重新记账
            return true;
        }
        return false;
    }
    private void saveAccount(int account) {
    }

    private int getAccount() {
        return 200;
    }
}
