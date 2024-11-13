package csu.web.petstore.persistence;

import csu.web.petstore.domain.Sequence;

public interface SequenceDao {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
