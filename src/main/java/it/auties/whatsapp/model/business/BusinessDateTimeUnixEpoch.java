package it.auties.whatsapp.model.business;

import it.auties.protobuf.api.model.ProtobufMessage;
import it.auties.protobuf.api.model.ProtobufProperty;
import it.auties.whatsapp.util.Clock;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.Optional;

import static it.auties.protobuf.api.model.ProtobufProperty.Type.INT64;

/**
 * A model class that represents a time as a unix epoch
 */
@AllArgsConstructor(staticName = "of")
@Data
@Builder(access = AccessLevel.PROTECTED)
@Jacksonized
@Accessors(fluent = true)
public class BusinessDateTimeUnixEpoch implements ProtobufMessage {
    /**
     * The timestamp of the date
     */
    @ProtobufProperty(index = 1, type = INT64)
    private long timestamp;

    /**
     * Returns the timestamp as a zoned date time
     *
     * @return an optional
     */
    public Optional<ZonedDateTime> time() {
        return Clock.parse(timestamp);
    }
}
