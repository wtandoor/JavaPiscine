import java.util.UUID;

public class Transaction {
    static enum TransactionType{
        DEBIT,
        CREDIT
    }

    private String identifier = UUID.randomUUID().toString();
    private User repicient;
    private User sender;
    private Transaction.TransactionType transferCategory;
    private Integer transferAmount;

    public Transaction(User repicient, User sender, Transaction.TransactionType transferCategory, Integer transferAmount) {
        this.repicient = repicient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

    public void doTransaction() throws TransactionIllegalException {
        if (sender.getBalance() >= transferAmount){
            sender.setBalance(transferAmount * -1);
            repicient.setBalance(transferAmount);
        } else {
            throw new TransactionIllegalException(this.identifier);
        }
    }

    public User getRepicient() {
        return repicient;
    }

    public User getSender() {
        return sender;
    }

    public Transaction.TransactionType getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String toString() {
        return "Transaction{" +
                "identifier=" + identifier +
                ", repicient=" + repicient +
                ", sender=" + sender +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}

class TransactionIllegalException extends Exception{
    TransactionIllegalException(String id){
        System.out.println("Transaction with id - " + id + "cannot be done");
    }
}
