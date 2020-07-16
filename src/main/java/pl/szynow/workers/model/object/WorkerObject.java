package pl.szynow.workers.model.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szynow.workers.entity.Position;
import pl.szynow.workers.entity.Worker;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class WorkerObject {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String address;
    private String phoneNumber;

    private List<Long> positionIds = new ArrayList<>();

    public WorkerObject(Worker entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.birthday = DateTimeFormatter.ISO_DATE.format( entity.getBirthday() );
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.positionIds = entity.getPositions().stream().map(Position::getId).collect(Collectors.toList());
    }

}
