package com.woven.wovenfinance.config.userDetails;


import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class PasswordService implements UserDetailsPasswordService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails updatePassword(UserDetails user, String password) {
        Profile profile = profileRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
        profile.setPassword(password);

        return new AppUserDetails(profile);
    }
}
