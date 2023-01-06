package com.gerasimchuk.entitygraphexample.domain.entity;

import com.gerasimchuk.entitygraphexample.common.enumeration.ReactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "reactions", schema = "public", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ReactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @ToString.Exclude
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reaction reaction = (Reaction) o;
        return id != null && Objects.equals(id, reaction.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
