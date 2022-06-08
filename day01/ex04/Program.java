public class Program {
    public static void main(String[] args) {
        TransactionService service = new TransactionService();
        User user = new User("Roman", 10000);
        User user1 = new User("Hellen", 5000);
        UsersArrayList list = new UsersArrayList();
        list.add(user);
        list.add(user1);
        System.out.println(list.getNumberOfUsers());
        service.addUser(user);
        service.addUser(user1);

        try {
            service.transfer(user.getId(), user1.getId(), 250);
        } catch (TransactionIllegalException | UserNotFoundException e){
            System.out.println(e.getMessage());
        }

        Transaction [] arrs = service.getUserTransactionList(user.getId());
        int i = 0;

        while (i < arrs.length){
            System.out.println(arrs[i]);
            i++;
        }

        String transactionIdForRemove = arrs[i - 1].getIdentifier();

        service.removeUserTransactions(transactionIdForRemove, user.getId());

        System.out.println("Roman balance - " + user.getBalance());
        System.out.println("Hellen balance - " + user1.getBalance());

        try {
            service.transfer(user.getId(), user1.getId(), 11000);
        } catch (UserNotFoundException | TransactionIllegalException e) {
            System.out.println(e.getMessage());
        }

        Transaction [] unpairedTransaction = service.getUnpairedTransactionList();
        for (int r = 0; r < unpairedTransaction.length; r++){
            System.out.println(unpairedTransaction[r]);
        }
    }
}