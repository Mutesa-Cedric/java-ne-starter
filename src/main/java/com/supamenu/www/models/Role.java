package com.supamenu.www.models;

import jakarta.persistence.*;
import lombok.*;
import com.supamenu.www.enumerations.user.EUserRole;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Base {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", unique = true, nullable = false)
    private EUserRole name;
}
