import java.util.UUID;

public class TransactionService {
    private UsersList usersList;
    private TransactionsLinkedList successTransactionList;
    private TransactionsLinkedList unpairedTransactionList;

    public TransactionService(){
        this.usersList = new UsersArrayList();
        this.successTransactionList = new TransactionsLinkedList();
        this.unpairedTransactionList = new TransactionsLinkedList();
    }

    public void addUser(User user){
        usersList.add(user);
    }

    public int getUserBalance(int id) throws UserNotFoundException{
        return usersList.getById(id).getBalance();
    }

    public void transfer(int senderId, int recipientId, int amount) throws UserNotFoundException, TransactionIllegalException {
        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(usersList.getById(senderId), usersList.getById(recipientId), Transaction.TransactionType.DEBIT, amount, transactionId);
        try{
            transaction.doTransaction();
            successTransactionList.add(transaction);
        } catch (TransactionIllegalException e) {
            unpairedTransactionList.add(transaction);
        }
    }

    public Transaction [] getUserTransactionList(int id){
        Transaction [] success = successTransactionList.toArray();
        Transaction [] unpaired = unpairedTransactionList.toArray();
        TransactionsLinkedList linkedList = new TransactionsLinkedList();
        for (int i = 0; i < success.length; i++){
            if (success[i].getSender().getId() == id || success[i].getRepicient().getId() == id){
                linkedList.add(success[i]);
            }
        }

        for (int i = 0; i < unpaired.length; i++){
            if (unpaired[i].getSender().getId() == id || unpaired[i].getRepicient().getId() == id)
                linkedList.add(unpaired[i]);
        }
        return linkedList.toArray();
    }

    public void removeUserTransactions(String transactionId, int userId){
        Transaction [] success = successTransactionList.toArray();
        Transaction [] failure = unpairedTransactionList.toArray();

        for (int i = 0; i < success.length; i++){
            if ((success[i].getSender().getId() == userId || success[i].getRepicient().getId() == userId) && success[i].getIdentifier().equals(transactionId))
                successTransactionList.remove(success[i].getIdentifier());
        }

        for (int i = 0; i < failure.length; i++){
            if ((failure[i].getSender().getId() == userId || failure[i].getRepicient().getId() == userId) && failure[i].getIdentifier().equals(transactionId))
                unpairedTransactionList.remove(failure[i].getIdentifier());
        }

    }

    public Transaction[] getUnpairedTransactionList(){
        return unpairedTransactionList.toArray();
    }
}
