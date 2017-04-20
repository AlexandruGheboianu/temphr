package com.ghb.temphr.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by agheboianu on 20.03.2017.
 */
public class ModelAuditor implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName(); //get logged in username
        } else {
            return "Anonymous";
        }
    }
}