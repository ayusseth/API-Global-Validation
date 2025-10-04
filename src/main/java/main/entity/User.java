package main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "email must be a valid Gmail address")
    @Column(unique = true, nullable = false)
    private String mail;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;
}
