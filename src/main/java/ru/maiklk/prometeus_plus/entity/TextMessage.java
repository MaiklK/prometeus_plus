package ru.maiklk.prometeus_plus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private long chatId;
    @Column
    private String text;
}
