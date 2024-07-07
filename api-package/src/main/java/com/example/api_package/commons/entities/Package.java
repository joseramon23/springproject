package com.example.api_package.commons.entities;

import com.example.api_package.commons.enums.PackageStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer sender;
    private String receiver;
    private String origin;
    private String destination;
    @Enumerated(EnumType.STRING)
    private PackageStatusEnum status;
}
