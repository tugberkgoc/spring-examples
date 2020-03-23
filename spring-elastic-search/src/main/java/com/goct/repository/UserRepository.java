package com.goct.repository;

import com.goct.entity.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Do NOT need to write this.
public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Query("{\"bool\":{\"must\": [{\"match\": {\"firstName\": \"?0\"}}]}}")
    List<User> getByCustomQuery(String search);

    List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
}
