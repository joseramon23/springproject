package com.example.eligibility_micro.common;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageEligibleEvent {
    private Long id;
    private Integer sender;
    private String receiver;
    private String origin;
    private String destination;
    private boolean isEligible;
}
