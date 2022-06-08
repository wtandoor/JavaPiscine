package edu.school21.chat.models;

public class ChatRoomsIdsGenerator {
    private int id;

    private ChatRoomsIdsGenerator() {
        this.id = 0;
    }

    private static class ChatRoomsIdsGeneratorHolder {
        public static final ChatRoomsIdsGenerator HOLDER_INSTANCE = new ChatRoomsIdsGenerator();
    }

    public static ChatRoomsIdsGenerator getInstance() {
        return ChatRoomsIdsGeneratorHolder.HOLDER_INSTANCE;
    }

    public Integer generateId() {
        id += 1;
        return id;
    }
}
