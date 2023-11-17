package ru.maiklk.prometeus_plus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maiklk.prometeus_plus.entity.TextMessage;

public interface MessageRepository extends JpaRepository<TextMessage, Integer> {
}
