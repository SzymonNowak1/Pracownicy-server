package pl.szynow.workers.model.object;

import lombok.Data;
import pl.szynow.workers.entity.Role;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.entity.Worker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserWorkerObject {

    private Long userId;
    private Long workerId;
    private String username;
    private List<String> roles;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String address;
    private String phoneNumber;

    public UserWorkerObject(User userEntity, Worker workerEntity) {
        if ( userEntity != null ) {
            this.userId = userEntity.getId();
            this.username = userEntity.getUsername();
            this.roles = userEntity.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            this.email = userEntity.getEmail();
        }
        if ( workerEntity != null ) {
            this.workerId = workerEntity.getId();
            this.firstName = workerEntity.getFirstName();
            this.lastName = workerEntity.getLastName();
            this.birthday = workerEntity.getBirthday().format(DateTimeFormatter.ISO_DATE);
            this.address = workerEntity.getAddress();
            this.phoneNumber = workerEntity.getPhoneNumber();
        }
    }

}
