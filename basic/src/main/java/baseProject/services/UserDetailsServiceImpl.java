package baseProject.services;

import baseProject.DTOs.UserDetailsImpl;
import baseProject.DTOs.UserRegistrationDTO;
import baseProject.data.UserRepository;
import baseProject.data.UserRoleRepository;
import baseProject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    private List<String> getUserRoles(User user) {
        List<String> userRoles = userRoleRepository.findRoleByUserName(user.getEmail());
        return userRoles;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private boolean ValidateUserDto(UserRegistrationDTO userDto) {
        if (  userDto == null  ){
            return false;
        }

        if (emailExist(userDto.getEmail())){
            return false;
        }

        if (userDto.getPassword()!= userDto.getConfirmPassword()){
            return false;
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("could not find user by this email address : " + username);
        }
        List<String> roles = userRoleRepository.findRoleByUserName(username);
        return new UserDetailsImpl(user, roles);
    }
}
