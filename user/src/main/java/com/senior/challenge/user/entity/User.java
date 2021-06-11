package com.senior.challenge.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.persistence.Creatable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "`user`")
public class User extends Creatable {

    @NotNull(message = "Nome é obrigatorio")
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull(message = "Senha é obrigatória")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Apelido é obrigatório")
    @Column(nullable = false)
    private String alias;

    @NotNull(message = "Tentativas de Login é obrigatório")
    @Column(nullable = false)
    private Integer tries;

    @NotNull(message = "E-mail é obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Telefone é obrigatório")
    @Column(unique = true, nullable = false)
    private String phone;

    @NotNull(message = "Documento é obrigatório")
    @Column(unique = true, nullable = false)
    private String document;

    @NotNull(message = "Status é obrigatório")
    @Column(nullable = false)
    private String status;

    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

    public static User create(UserDTO userDto) {
        return new ModelMapper().map(userDto, User.class);
    }
}
