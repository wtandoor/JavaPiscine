public class Program {
    public static void main(String[] args) {
        UsersArrayList usersArrayList = new UsersArrayList();
        User [] users = new User[10];
        for(int i = 0; i < 10; i++){
            users[i] = new User("Romas", i);
            usersArrayList.add(users[i]);
        }
        System.out.println(usersArrayList.getById(5));
        User newUser = usersArrayList.getByIndex(2);
        System.out.println("User with index 2:" + newUser.getName() + ". His balance: " + newUser.getBalance());
        int numberOfUsers = usersArrayList.getNumberOfUsers();
        System.out.println("Count of users in arrray: " + numberOfUsers);
    }
}
