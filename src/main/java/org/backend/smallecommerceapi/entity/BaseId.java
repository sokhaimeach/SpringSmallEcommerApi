package org.backend.smallecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
