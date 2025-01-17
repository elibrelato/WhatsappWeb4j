package it.auties.whatsapp.model.action;

import it.auties.protobuf.base.ProtobufProperty;
import it.auties.whatsapp.binary.BinaryPatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import static it.auties.protobuf.base.ProtobufType.BOOL;

/**
 * A model clas that represents a new star status for a message
 */
@AllArgsConstructor
@Data
@Builder
@Jacksonized
@Accessors(fluent = true)
public final class StarAction implements Action {
    /**
     * Whether this action set the message as starred
     */
    @ProtobufProperty(index = 1, type = BOOL)
    private boolean starred;

    /**
     * The name of this action
     *
     * @return a non-null string
     */
    @Override
    public String indexName() {
        return "star";
    }

    /**
     * The version of this action
     *
     * @return a non-null string
     */
    @Override
    public int actionVersion() {
        return 2;
    }

    /**
     * The type of this action
     *
     * @return a non-null string
     */
    @Override
    public BinaryPatchType actionType() {
        return null;
    }
}
