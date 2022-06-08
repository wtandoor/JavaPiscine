package edu.school21.chat.models;

public class MessageIdsGenerator {
    private int id;

    private MessageIdsGenerator() {
        this.id = 0;
    }

    private static class MessageIdsGeneratorHolder {
        public static final MessageIdsGenerator HOLDER_INSTANCE = new MessageIdsGenerator();
    }

    public static MessageIdsGenerator getInstance() {
        return MessageIdsGeneratorHolder.HOLDER_INSTANCE;
    }

    public Integer generateId() {
        id += 1;
        return id;
    }
}
