package com.woven.wovenfinance.config.userDetails;


import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile dbUser = profileRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Not Found"));
        return new AppUserDetails(dbUser);
    }
}
