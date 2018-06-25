package com.fongwell.satchi.crm.core.common.crypto.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by docker on 5/2/18.
 */
public class ByPassPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(final CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        return true;
    }
}
