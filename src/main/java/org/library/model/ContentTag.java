package org.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Entity that represents Tag.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content_tag", schema = "library")
public class ContentTag {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Instant updatedDate;
    @PrePersist
    void onCreate() {
        this.setCreatedDate(Instant.now());
        this.setUpdatedDate(Instant.now());
    }
    @PreUpdate
    void onUpdate() {
        this.setUpdatedDate(Instant.now());
    }
}
