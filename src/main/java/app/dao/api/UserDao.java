package app.dao.api;

import app.dao.GenericDao;
import app.dto.UserDTO;
import app.entity.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

public interface UserDao extends GenericDao<User>{
    User findUserByLogin(String login);
    User findUserByEmail(String email);
}
