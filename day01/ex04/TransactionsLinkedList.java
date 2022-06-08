public class TransactionsLinkedList implements TransactionsList {
    private Integer size = 0;
    private final TransactionNode first = new TransactionNode(null, null, null);
    private final TransactionNode last = new TransactionNode(null, null, null);

    public TransactionsLinkedList() {
        first.setNext(last);
        last.setPrev(first);
    }

    public void add(Transaction object) {
        TransactionNode temp = new TransactionNode(last,last.getPrev(),object);
        last.getPrev().setNext(temp);
        last.setPrev(temp);
        this.size++;
    }

    public void remove(String name) {
        TransactionNode temp = last.getPrev();
        while (temp != first && temp != null){
            if (temp.getData().getIdentifier().equals(name)){
                temp.getPrev().setNext(temp.getNext());
                temp.getNext().setPrev(temp.getPrev());
                temp.setNext(null);
                temp.setPrev(null);
                temp.setData(null);
                this.size--;
                return;
            }
            temp = temp.getPrev();
        }
        throw new TransactionNotFoundException();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public Integer size() {
        return size;
    }

    public Transaction[] toArray() {
        Transaction[] newArr = new Transaction[size];
        TransactionNode temp = last.getPrev();
        for( int i = 0; temp != first; i++){
            newArr[i] = temp.getData();
            temp = temp.getPrev();
        }
        return newArr;
    }
}

class LinkedListException extends RuntimeException{
    public LinkedListException(){
        System.err.println("empty element in linked list");
    }
}

class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        System.err.println("not found this transaction");
    }
}


class TransactionNode{
    private TransactionNode next;
    private TransactionNode prev;
    private Transaction data;

    public TransactionNode(TransactionNode next, TransactionNode prev, Transaction data) {
        this.next = next;
        this.prev = prev;
        this.data = data;
    }

    public TransactionNode getNext() {
        return next;
    }

    public void setNext(TransactionNode next) {
        this.next = next;
    }

    public TransactionNode getPrev() {
        return prev;
    }

    public void setPrev(TransactionNode prev) {
        this.prev = prev;
    }

    public Transaction getData() {
        return data;
    }

    public void setData(Transaction data) {
        this.data = data;
    }
}