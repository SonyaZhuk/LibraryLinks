package org.library.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.library.enums.ContentStatus;
import org.library.enums.ContentType;
import org.library.enums.Priority;

import javax.persistence.*;
import java.time.Instant;

/**
 * Entity that represents Content.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_content", schema = "library")
public class UserContent {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id", unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "content_tag_id", nullable = false, referencedColumnName = "id", unique = true)
    private ContentTag contentTag;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @Column(name = "rating")
    private Integer rating;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Instant updatedDate;
}
