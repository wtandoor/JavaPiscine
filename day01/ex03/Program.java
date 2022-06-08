public class Program {
    public static void main(String[] args) {
        TransactionsLinkedList linkedList = new TransactionsLinkedList();
        User user1 = new User("Roma", 200);
        User user2 = new User("Hellen", 100);
        String[] arrOfIds = new String[10];
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.size());
        for (int i = 0; i < 10; i++) {
            linkedList.add(new Transaction(user2, user1, Transaction.TransactionType.DEBIT, i + 1));
        }
        Transaction[] toArr = linkedList.toArray();
        for (int i = 0; i < toArr.length; i++){
            try {
                toArr[i].doTransaction();
            } catch (TransactionIllegalException e){
                System.out.println(e.getMessage());
            }
            arrOfIds[i] = toArr[i].getIdentifier();
            System.out.println(i + " " + toArr[i].toString());
        }
        linkedList.remove(arrOfIds[5]);
        Transaction [] newArr = linkedList.toArray();
        for (int i = 0; i < newArr.length; i++){
            System.out.println(i + " " + newArr[i]);
        }
        System.out.println(linkedList.size());
        System.out.println(linkedList.isEmpty());
    }
}