public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        this.identifier = identifier;
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(Integer balance) {
        if (balance > 0)
            this.balance += balance;
        else {
            if (this.balance + balance < 0)
                System.out.println("can't place this count of money for user with id:" + identifier);
            else
                this.balance += balance;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
