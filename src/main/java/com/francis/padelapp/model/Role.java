package com.francis.padelapp.model;

import com.francis.padelapp.enums.RoleName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<User> users = new LinkedHashSet<>();
    private List<User> users;

}
