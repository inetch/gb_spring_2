package ru.gb.inetch.shoppee.services.mq;

public interface QueueCartService {
    void sendMessage(String message);
    void receiveMessage();
    String getLastMessage();
}
