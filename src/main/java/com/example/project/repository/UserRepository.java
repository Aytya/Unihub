package com.example.project.repository;

import com.example.project.domain.model.User;
import com.example.project.domain.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Modifying
    @Query(value = """
            INSERT INTO tasks_images (task_id, image)
            VALUES (:id, :fileName)
            """, nativeQuery = true)
    void addImage(@Param("id") Long id, @Param("fileName") String fileName);

}
