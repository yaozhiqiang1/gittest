package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.core.store.domain.aggregate.Staff;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffAuthenticationDetails;
import com.fongwell.satchi.crm.core.store.domain.aggregate.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by docker on 5/21/18.
 */
@Component
public class WxStoreUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Staff staff = staffRepository.findOneByMobile(username);
        if (staff == null) {
            throw new UsernameNotFoundException("staff mobile not found: " + username);
        }

        return new StaffAuthenticationDetails(staff.getId(), staff.getStoreId(), username, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_WX_STAFF")));
    }
}
