package goit.com.shorturlproject.v1.auth.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;
    private String password;
}
