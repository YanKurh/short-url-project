package goit.com.shorturlproject.user;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
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
