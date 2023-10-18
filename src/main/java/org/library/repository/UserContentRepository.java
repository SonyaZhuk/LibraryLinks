package org.library.repository;

import lombok.NonNull;
import org.library.model.ContentTag;
import org.library.model.UserContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContentRepository extends PagingAndSortingRepository<UserContent, Long> {

    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<UserContent> findById(@NonNull Long id);
    boolean existsUserContentByLink(@NonNull String link);

    @EntityGraph(attributePaths = {"user","contentTag"}, type = EntityGraph.EntityGraphType.FETCH)
    Page<UserContent> findByContentTag(@NonNull ContentTag contentTag, @NonNull Pageable pageable);
}
