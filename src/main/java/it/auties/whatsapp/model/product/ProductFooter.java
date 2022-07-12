package it.auties.whatsapp.model.product;

import it.auties.protobuf.api.model.ProtobufProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import static it.auties.protobuf.api.model.ProtobufProperty.Type.STRING;

/**
 * A model class that represents the footer of a product
 */
@AllArgsConstructor(staticName = "of")
@Data
@Builder(access = AccessLevel.PROTECTED)
@Jacksonized
@Accessors(fluent = true)
public class ProductFooter {
    /**
     * The footer of this product
     */
    @ProtobufProperty(index = 1, type = STRING)
    private String content;
}
