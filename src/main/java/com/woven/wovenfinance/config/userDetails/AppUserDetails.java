package com.woven.wovenfinance.config.userDetails;


import com.woven.wovenfinance.model.Profile;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@Getter
@Setter
public class AppUserDetails implements UserDetails {

    private Profile profile;

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

        return profile.getRole().getGrantedAuthorities();
        }

        @Override
        public String getPassword() {

        return profile.getPassword();
        }

        @Override
        public String getUsername() {

        return profile.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {

        return true;
        }

        @Override
        public boolean isAccountNonLocked() {


        return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {

        return true;
        }

        @Override
        public boolean isEnabled() {
        return true;
        }
    }
