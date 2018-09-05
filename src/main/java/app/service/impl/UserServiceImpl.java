package app.service.impl;

import app.dao.api.UserDao;
import app.dto.UserDTO;
import app.entity.User;
import app.entity.enums.Role;
import app.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional(propagation = Propagation.REQUIRED)
    public void create(UserDTO userDto) {
        if (userDto != null)
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setRole(Role.ROLE_USER.toString());//ROLE_USER or ROLE_ADMIN
        userDao.create(modelMapper.map(userDto, User.class));
        logger.debug(String.format("Successfully saved user %s", userDto.getLogin()));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(UserDTO userDTO) {
        User user = userDao.getById(userDTO.getId());
        if (user != null)
            userDao.update(modelMapper.map(userDTO,User.class));
        logger.debug(String.format("Successfully update user %s", userDTO.getLogin()));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(UserDTO userDTO) {
        if (userDTO != null)
            userDao.delete(modelMapper.map(userDTO, User.class));
        logger.debug(String.format("Successfully delete user %s", userDTO.getLogin()));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getById(Integer id) {
        User user = userDao.getById(id);
        if (user != null) {
            logger.debug(String.format("Successfully got user with id %d" + user.getId()));
            return modelMapper.map(user, UserDTO.class);

        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getByLogin(String login) {
        User user = userDao.getByLogin(login);
        if (user != null) {
            logger.debug(String.format("Successfully got user with login %s" + user.getLogin()));
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getByEmail(String email) {
        User user = userDao.getByEmail(email);
        if (user != null) {
            logger.debug(String.format("Successfully got user with email %s" + user.getEmail()));
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAll() {
        List<User> list = userDao.getAll();
        if (list != null) {
            return list.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}