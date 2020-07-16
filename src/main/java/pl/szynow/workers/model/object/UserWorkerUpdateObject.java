package pl.szynow.workers.model.object;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserWorkerUpdateObject {

    @NotNull
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private String role;
    @NotNull
    private String email;

    private Long workerId;

}
