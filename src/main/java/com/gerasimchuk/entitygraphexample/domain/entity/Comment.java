package com.gerasimchuk.entitygraphexample.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Entity
@Table(name = "comments", schema = "public", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ToString.Exclude
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Reaction> reactions = new HashSet<>();

    public void addReaction(Reaction reaction){
        if (reactions == null){
            reactions = new HashSet<>();
        }
        reactions.add(reaction);
        reaction.setComment(this);
    }

    public void removeReaction(Reaction reaction){
        if (reactions == null){
            return;
        }
        reactions.removeIf(r -> r.equals(reaction));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
