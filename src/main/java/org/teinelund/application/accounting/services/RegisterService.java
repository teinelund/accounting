package org.teinelund.application.accounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.teinelund.application.accounting.entities.AccRoleEntity;
import org.teinelund.application.accounting.entities.AccUserEntity;
import org.teinelund.application.accounting.entities.UserRoleEntity;
import org.teinelund.application.accounting.model.User;
import org.teinelund.application.accounting.repository.AccRoleRepository;
import org.teinelund.application.accounting.repository.AccUserRepository;
import org.teinelund.application.accounting.repository.UserRoleRepository;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class RegisterService {

    private final long ROLE_USER = 2L;

    @Autowired
    private AccUserRepository accUserRepository;

    @Autowired
    private AccRoleRepository accRoleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public boolean registerUser(User user) {
        AccUserEntity accUser = accUserRepository.getAccUserEntityForUserName(user.getUsername());
        if (accUser != null) {
            return false;
        }
        accUser = new AccUserEntity();
        accUser.setUserName(user.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        accUser.setPassword(hashedPassword);
        accUser.setEnabled(1);
        Collection<UserRoleEntity> userRoles = new LinkedList<>();
        AccRoleEntity accRole = accRoleRepository.getOne(ROLE_USER);
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setAccRoleByRoleId(accRole);
        userRole.setAccUserByUserId(accUser);
        userRoles.add(userRole);
        accUser.setUserRolesByUserId(userRoles);
        accUserRepository.save(accUser);
        userRoleRepository.save(userRole);
        return true;
    }
}
