package org.library.repository;

import org.library.model.ContentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {

    Optional<ContentTag> findByTag(@NotNull String tag);
}
