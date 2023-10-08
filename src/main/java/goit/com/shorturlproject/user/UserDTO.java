package goit.com.shorturlproject.user;

import lombok.*;

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


}
