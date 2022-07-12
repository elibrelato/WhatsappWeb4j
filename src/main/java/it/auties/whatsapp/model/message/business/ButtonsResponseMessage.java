package it.auties.whatsapp.model.message.business;

import it.auties.protobuf.api.model.ProtobufProperty;
import it.auties.whatsapp.model.button.Button;
import it.auties.whatsapp.model.info.ContextInfo;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.model.BusinessMessage;
import it.auties.whatsapp.model.message.model.ContextualMessage;
import it.auties.whatsapp.util.Validate;
import lombok.*;
import lombok.Builder.Default;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import static it.auties.protobuf.api.model.ProtobufProperty.Type.MESSAGE;
import static it.auties.protobuf.api.model.ProtobufProperty.Type.STRING;

/**
 * A model class that represents a message that contains a response to a previous {@link ButtonsMessage}
 */
@AllArgsConstructor(staticName = "newButtonsResponseMessage")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@Builder(builderMethodName = "newRawButtonsResponseMessageBuilder")
@Accessors(fluent = true)
public final class ButtonsResponseMessage extends ContextualMessage implements BusinessMessage {
    /**
     * The id of the button that was selected
     */
    @ProtobufProperty(index = 1, type = STRING)
    private String buttonId;

    /**
     * The display text of the button that was selected
     */
    @ProtobufProperty(index = 2, type = STRING)
    private String buttonText;

    /**
     * The context info of this message
     */
    @ProtobufProperty(index = 3, type = MESSAGE, concreteType = ContextInfo.class)
    @Default
    private ContextInfo contextInfo = new ContextInfo();  // Overrides ContextualMessage's context info

    /**
     * Constructs a response message from a buttons message and a selected button
     *
     * @param quoted the non-null message info wrapping a {@link ButtonsMessage}
     * @param button the non-null button to select
     * @return a non-null buttons response message
     */
    public static ButtonsResponseMessage of(@NonNull MessageInfo quoted, @NonNull Button button) {
        Validate.isTrue(quoted.message()
                        .content() instanceof ButtonsMessage,
                "Cannot select buttons message, erroneous type: %s" + quoted.message()
                        .content());
        return ButtonsResponseMessage.newRawButtonsResponseMessageBuilder()
                .buttonId(button.id())
                .buttonText(button.text()
                        .content())
                .contextInfo(ContextInfo.of(quoted))
                .build();
    }
}
