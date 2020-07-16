package pl.szynow.workers.service;

import pl.szynow.workers.entity.Role;
import pl.szynow.workers.exception.RoleAlreadyDefinedException;
import pl.szynow.workers.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role addRole(String name) throws RoleAlreadyDefinedException;

}
