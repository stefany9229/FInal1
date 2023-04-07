package com.dh.movieservice.rabbitmq.queue;

import com.dh.movieservice.domain.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieSender {
    private final RabbitTemplate rabbitTemplate;

    private final Queue movieQueue;

    public void send(Movie persona) {
        this.rabbitTemplate.convertAndSend(this.movieQueue.getName(), persona);
    }

}
