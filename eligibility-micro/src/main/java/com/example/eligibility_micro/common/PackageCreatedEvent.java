package com.example.eligibility_micro.common;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageCreatedEvent {
    private Long id;
    private Integer sender;
    private String receiver;
    private String origin;
    private String destination;
}
