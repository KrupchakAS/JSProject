package app.service.impl;

import app.dao.api.UserDao;
import app.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO userDTO = modelMapper.map(userDao.getByLogin(login),UserDTO.class);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(userDTO.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(userDTO.getLogin(), userDTO.getPassword(), grantedAuthorities);
    }
}
