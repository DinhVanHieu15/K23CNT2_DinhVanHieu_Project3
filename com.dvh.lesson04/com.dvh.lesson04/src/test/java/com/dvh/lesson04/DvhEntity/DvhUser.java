package com.dvh.lesson04.DvhEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DvhUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long DvhId;

    String DvhUsername;
    String DvhPassword;
    String DvhFullName;

    LocalDate DvhBirthDay;

    String DvhEmail;
    String DvhPhone;

    int DvhAge;
    Boolean DvhStatus;
}
