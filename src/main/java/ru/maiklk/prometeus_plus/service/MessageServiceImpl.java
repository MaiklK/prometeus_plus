package ru.maiklk.prometeus_plus.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maiklk.prometeus_plus.entity.TextMessage;
import ru.maiklk.prometeus_plus.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    @Transactional
    /*Используется для измерения времени выполнения метода
     "saveTextMessage". Это означает, что каждый раз, когда вызывается этот метод, будет записываться
      время его выполнения. */
    @Timed("saveMessageTextTime")
    /*Используется для подсчета количества вызовов метода
     "saveTextMessage". Это означает, что каждый раз, когда вызывается этот метод, будет увеличиваться
      счетчик вызовов. */
    @Counted("saveMessageTextCount")
    public void saveTextMessage(TextMessage textMessage) {
        messageRepository.save(textMessage);
    }
}
