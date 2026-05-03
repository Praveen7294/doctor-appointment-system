package com.praveen.doctor_appointment_system.security;

import com.praveen.doctor_appointment_system.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User getCurrentUserFromSecurityContextHolder() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
