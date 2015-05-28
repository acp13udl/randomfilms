package com.udl.softarch.randomfilms.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import java.util.Collection;

/**
 * Created by adrian on 27/5/15.
 */
@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
    }
}
