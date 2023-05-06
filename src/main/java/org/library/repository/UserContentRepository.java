package org.library.repository;

import org.library.model.UserContent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContentRepository extends JpaRepository<UserContent, Long> {
    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<UserContent> findById(Long id);
}
