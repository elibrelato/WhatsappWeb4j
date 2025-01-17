package it.auties.whatsapp.model.signal.session;

import it.auties.whatsapp.model.signal.keypair.SignalKeyPair;
import lombok.*;
import lombok.Builder.Default;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Builder
@Jacksonized
@Accessors(fluent = true)
public class SessionState {
    @Getter
    private final int version;

    @Getter
    private final int registrationId;

    @Getter
    private final byte @NonNull [] baseKey;

    @Getter
    private final byte @NonNull [] remoteIdentityKey;

    @NonNull
    @Default
    private final ConcurrentHashMap<String, SessionChain> chains = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private byte @NonNull [] rootKey;

    @Getter
    @Setter
    private SessionPreKey pendingPreKey;

    @NonNull
    @Getter
    @Setter
    private SignalKeyPair ephemeralKeyPair;

    @Getter
    @Setter
    private byte @NonNull [] lastRemoteEphemeralKey;

    @Getter
    @Setter
    private int previousCounter;

    @Getter
    @Setter
    private boolean closed;

    public boolean hasChain(byte[] senderEphemeral) {
        return chains.containsKey(HexFormat.of().formatHex(senderEphemeral));
    }

    public Optional<SessionChain> findChain(byte[] senderEphemeral) {
        return Optional.ofNullable(chains.get(HexFormat.of().formatHex(senderEphemeral)));
    }

    public SessionState addChain(byte[] senderEphemeral, SessionChain chain) {
        chains.put(HexFormat.of().formatHex(senderEphemeral), chain);
        return this;
    }

    public void removeChain(byte[] senderEphemeral) {
        Objects.requireNonNull(chains.remove(HexFormat.of().formatHex(senderEphemeral)), "Cannot remove chain");
    }

    public boolean hasPreKey() {
        return pendingPreKey != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version(), Arrays.hashCode(baseKey()));
    }

    public boolean equals(Object other) {
        return other instanceof SessionState that && contentEquals(that.version(), that.baseKey());
    }

    public boolean contentEquals(int version, byte[] baseKey) {
        return version() == version && Arrays.equals(baseKey(), baseKey);
    }
}
