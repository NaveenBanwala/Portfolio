package com.naveen.demo.domain.entities;


import com.naveen.demo.domain.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id" ,nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" ,nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
            name="posts_tags",
            joinColumns = @JoinColumn(name="post_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false)
    private Integer readingTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(content, post.content) && status == post.status && Objects.equals(readingTime, post.readingTime) && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status, readingTime, createdAt, updatedAt);
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now(); // set createdAt when saving
        this.updatedAt = LocalDateTime.now();

    }
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
