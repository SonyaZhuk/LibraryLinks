package org.library.repository;

import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserContentRepository extends PagingAndSortingRepository<UserContent, Long> {

    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<UserContent> findById(Long id);

    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<List<UserContent>> findByContentTag(ContentTag contentTag);

    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<UserContent> findAll(Pageable pageable);
}
