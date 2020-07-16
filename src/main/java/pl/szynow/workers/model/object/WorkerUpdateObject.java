package pl.szynow.workers.model.object;

import lombok.Data;

import java.util.List;

@Data
public class WorkerUpdateObject {

    private String firstName;
    private String lastName;
    private String birthday;
    private String address;
    private String phoneNumber;

    private List<Long> positionIds;

}
