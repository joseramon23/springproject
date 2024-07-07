package com.example.eligibility_micro.common;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LetterEligibleEvent {
    private Long id;
    private Long sender;
    private String receiver;
    private String content;
    private String destination;
    private String date;
    private boolean isEligible;
}
