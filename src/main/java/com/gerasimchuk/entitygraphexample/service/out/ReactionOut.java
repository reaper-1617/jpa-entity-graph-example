package com.gerasimchuk.entitygraphexample.service.out;

import com.gerasimchuk.entitygraphexample.common.enumeration.ReactionType;
import com.gerasimchuk.entitygraphexample.service.api.out.Reaction;
import lombok.Data;

@Data
public class ReactionOut implements Reaction {
    private final ReactionType type;
}
