package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , unique = true, nullable = false , length = 30)
    private String name;

    @Column(name = "code_name" , unique = true, nullable = false, length = 10)
    private String codeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "city_id" , nullable = false)
    private City city;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;
}
