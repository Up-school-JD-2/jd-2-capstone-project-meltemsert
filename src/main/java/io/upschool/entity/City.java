package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , unique = true, nullable = false , length = 15)
    private String name;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;


}
