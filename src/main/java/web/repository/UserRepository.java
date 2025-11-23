package web.repository;

import web.model.User;

import java.util.List;

public interface UserRepository {
    User findById(Long id);
    void add(User user);
    void update(User user);
    void delete(Long id);
    List<User> findAll(int limit);
}
