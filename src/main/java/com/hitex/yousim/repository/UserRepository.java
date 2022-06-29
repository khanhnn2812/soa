package com.hitex.yousim.repository;

import com.hitex.yousim.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    @Query(value = "select u from User u where u.userName like %?1% and u.statusUser = 1")
//    User findByUsername(String userName);

    @Query(value = "select u from User u where u.userName = ?1 and u.statusUser = 1")
    User findByUsername(String userName);

    @Query(value = "select u from User u where u.userName = ?1  and u.password = ?2 and u.statusUser = 1")
    User findUser(String userName, String password);

    User findUserByUserId(int userId);

    @Query(value = "select u from User u where u.email = ?1 and u.statusUser = 1")
    User findByEmail(String email);

    @Query(value = "select u from User u where u.phone = ?1 and u.statusUser = 1")
    User findByPhone(String phone);

    @Query(value = "select u from User u where (:username IS NULL OR lower(u.userName) LIKE " +
            "lower(concat('%', concat(:username, '%')))) and " +
            " (-1 in :status or u.statusUser IN :status) " +
            "ORDER BY u.createTime DESC")
    List<User> getListUser(@Param("username") String username,
                           @Param("status") int status,
                           Pageable page);

    @Query(value = "select count(u) from User u where (:username IS NULL OR lower(u.userName) LIKE " +
            "lower(concat('%', concat(:username, '%')))) and " +
            " (-1 in :status or u.statusUser IN :status) " +
            "ORDER BY u.createTime DESC")
    int countUserByUserNameAndStatusUser(@Param("username") String username,@Param("status") int status);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.session = :session where u.userName = :username")
    void updateSession(@Param("session") String session,
                       @Param("username") String username);

    @Query(value = "select u from User u where u.token =?1")
    User findUserBySessionAngToken(String token);

    @Query(value = "select u from User u where u.userId = ?1 and u.statusUser = 1")
    User findByUserId(int userId);

//    @Query(value = "Select u.email from User u where u.statusUser = 1 and (u.role = 1 or u.role = 3)")
//    List<String> getMailsFromUser();
}
