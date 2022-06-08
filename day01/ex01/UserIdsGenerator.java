public class UserIdsGenerator {
    private static int id;

    private UserIdsGenerator(){
        this.id = 0;
    }

    private static class UserIdsGeneratorHolder{
        public static final UserIdsGenerator HOLDER_INSTANCE = new UserIdsGenerator();
    }

    public static UserIdsGenerator getInstance(){
        return UserIdsGeneratorHolder.HOLDER_INSTANCE;
    }

    public Integer generateId(){
        id += 1;
        return id;
    }

}
