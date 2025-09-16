package com.USJ.UniConnect_Backend.dto;


import com.USJ.UniConnect_Backend.entities.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotBlank(message = "{user.name.absent}")
    private String name;
    @NotBlank(message = "{user.email.absent}")
    @Email(message = "{user.email.invalid}")
    private String email;
    private String address;
    @NotBlank(message = "{user.password.absent}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,15}$",message = "{user.password.invalid}")
    private String password;


    private UserRole role;
    private Long profileId;

    public UserEntity toEntity() {
        return new UserEntity(this.id, this.name, this.email, this.address, this.password, this.role,this.profileId);
    }
}
