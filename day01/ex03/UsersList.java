public interface UsersList {
    void add(User object);
    User getById(int id) throws UserNotFoundException;
    User getByIndex(int index);
    int getNumberOfUsers();
}