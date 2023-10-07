package goit.com.shorturlproject.entity.request;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="confirmationToken")
@Getter
@Setter
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = SignupRequest.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private SignupRequest signupRequest;

    public ConfirmationToken() {
    }

    public ConfirmationToken(SignupRequest signupRequest) {
        this.signupRequest = signupRequest;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public SignupRequest getEntity() {
        return signupRequest;
    }

    public void setUserEntity(SignupRequest signupRequest) {
        this.signupRequest = signupRequest;
    }

}