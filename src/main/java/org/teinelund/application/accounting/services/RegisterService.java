package org.teinelund.application.accounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teinelund.application.accounting.entities.AccRoleEntity;
import org.teinelund.application.accounting.entities.AccUserEntity;
import org.teinelund.application.accounting.entities.UserRoleEntity;
import org.teinelund.application.accounting.model.User;
import org.teinelund.application.accounting.repository.AccRoleRepository;
import org.teinelund.application.accounting.repository.AccUserRepository;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class RegisterService {

    @Autowired
    private AccUserRepository accUserRepository;

    @Autowired
    private AccRoleRepository accRoleRepository;

    public boolean registerUser(User user) {
        AccUserEntity accUser = new AccUserEntity();
        accUser.setUserName(user.getUsername());
        accUser.setPassword(user.getPassword());
        Collection<UserRoleEntity> userRoles = new LinkedList<>();
        AccRoleEntity role = accRoleRepository.getOne(1L);
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setAccRoleByRoleId(role);
        userRole.setAccUserByUserId(accUser);
        userRoles.add(userRole);
        accUser.setUserRolesByUserId(userRoles);
        accUserRepository.save(accUser);
        return true;
    }
}
