package ru.gb.inetch.shoppee.services.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@PropertySource("classpath:connection.properties")
public class QueueCartServiceImpl implements QueueCartService{
    private ConnectionFactory mqFactory;
    static private String lastMessage;

    @Value("${mq.cart.queue.name}")
    private String QUEUE_NAME;

    @Autowired
    void setMqFactory(ConnectionFactory factory){
        this.mqFactory = factory;
    }

    @Override
    public void sendMessage(String message){
        try (Connection connection = mqFactory.newConnection();
            Channel channel = connection.createChannel()
        ){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void setLastMessage(String lastMessage) {
        QueueCartServiceImpl.lastMessage = lastMessage;
    }

    @Override
    public String getLastMessage() {
        return lastMessage;
    }

    @Override
    public void receiveMessage(){
        /*try (Connection connection = mqFactory.newConnection();
             Channel channel = connection.createChannel())*/
        try
        {
            Connection connection = mqFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("wait message");

            final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), "UTF-8");
                QueueCartServiceImpl.setLastMessage(msg);
                System.out.println("Receiver " + msg);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
