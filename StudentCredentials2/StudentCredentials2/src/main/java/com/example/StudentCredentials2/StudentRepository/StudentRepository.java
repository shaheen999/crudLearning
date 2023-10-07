package com.example.StudentCredentials2.StudentRepository;

import com.example.StudentCredentials2.StudentCredentials.StudentCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends MongoRepository<StudentCredentials,String> {
      List<StudentCredentials> findBySid(String Sid);
}
