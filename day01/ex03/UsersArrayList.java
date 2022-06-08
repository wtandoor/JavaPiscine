public class UsersArrayList implements UsersList {
    private User array[];

    UsersArrayList(){
        this.array = new User[10];
    }

    private void resize_array(User object, int index){
        User[] array_copy = new User[(int)(this.array.length * 1.5)];
        for (int i = 0; i < array.length; i++){
            array_copy[i] = array[i];
        }
        this.array = array_copy;
        this.array[index] = object;
    }

    public void add(User object) {
        int l = 0;
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] == null) {
                array[i] = object;
                return;
            }
            l = i;
        }
        resize_array(object, l);
    }

    public User getById(int id) throws UserNotFoundException {
        for (int i = 0; i < array.length; i++){
            if (array[i] != null && array[i].getId() == id)
                return array[i];
        }
        throw new UserNotFoundException(id);
    }

    public User getByIndex(int index) {
        return array[index];
    }

    public int getNumberOfUsers() {
        int i = 0;
        if (array[i] == null)
            return i;
        while (array[i] != null)
            i++;
        i++;
        return (i);
    }
}

class UserNotFoundException extends Exception {
    UserNotFoundException(int id){
        System.out.println("User with Id:" + id + " doesn't exist");
    }
}
