public class Program {
    public static void main(String[] args) {
        User user1 = new User(0, "Roman", 100);
        User user2 = new User(1, "Hellen", 232);
        Transaction transaction = new Transaction(user1, user2, Transaction.TransactionType.DEBIT, 32);
        Transaction transaction1 = new Transaction(user1, user2, Transaction.TransactionType.CREDIT, 201);
        System.out.println(transaction);
        System.out.println(transaction1);
    }
}
