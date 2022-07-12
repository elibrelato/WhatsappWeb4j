package it.auties.whatsapp.model.message.model;

import it.auties.protobuf.api.model.ProtobufProperty;
import it.auties.whatsapp.model.info.ContextInfo;
import it.auties.whatsapp.model.message.business.*;
import it.auties.whatsapp.model.message.standard.*;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import static it.auties.protobuf.api.model.ProtobufProperty.Type.MESSAGE;

/**
 * A model interface that represents a message sent by a contact that provides a context.
 * Classes that implement this interface must provide an accessor named contextInfo to access said property.
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Accessors(fluent = true)
public sealed abstract class ContextualMessage implements Message
        permits ButtonsMessage, ButtonsResponseMessage, ListMessage, TemplateReplyMessage, TemplateMessage,
        MediaMessage, ContactMessage, ContactsArrayMessage, GroupInviteMessage, LiveLocationMessage, LocationMessage,
        ProductMessage, TextMessage, InteractiveMessage {
    /**
     * The context info of this message
     */
    @ProtobufProperty(index = 17, type = MESSAGE, concreteType = ContextInfo.class)
    @NonNull
    @Default
    private ContextInfo contextInfo = new ContextInfo();
}
