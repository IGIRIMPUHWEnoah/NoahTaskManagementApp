package noah.com.noahtaskapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDtos {

    private String username;
    private  String email;


    public UserDtos(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
