import java.util.UUID;

public class Transaction {
    static enum TransactionType{
        DEBIT,
        CREDIT
    }

    private String identifier = UUID.randomUUID().toString();
    private User repicient;
    private User sender;
    private TransactionType transferCategory;
    private Integer transferAmount;

    public Transaction(User repicient, User sender, TransactionType transferCategory, Integer transferAmount) {
        this.repicient = repicient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

    public Transaction(User repicient, User sender, TransactionType transferCategory, Integer transferAmount, String transactionId) {
        this.repicient = repicient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
        this.identifier = transactionId;
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

    public TransactionType getTransferCategory() {
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
                ", repicient=" + repicient.getName() +
                ", sender=" + sender.getName() +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}

class TransactionIllegalException extends Exception{
    TransactionIllegalException(String id){
        System.out.println("Transaction with id - " + id + " cannot be done");
    }
}