package goit.com.shorturlproject.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private String userName;
    private String email;
    private String login;
    private String password;

    public static UserDTO fromUser(User user){

       return UserDTO.builder()
               .userName(user.getUserName())
               .email(user.getEmail())
               .login(user.getLogin())
               .password(user.getPassword())
               .build();
    }
    public static User fromUserDto(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());

        return user;
    }

}
