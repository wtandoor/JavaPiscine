public interface TransactionsList {
    void add(Transaction object);
    void remove(String name);
    boolean isEmpty();
    Integer size();
    Transaction[] toArray();
}
