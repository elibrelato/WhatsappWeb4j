package it.auties.whatsapp4j.configuration;

import jakarta.websocket.ClientEndpointConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class WebSocketConfiguration extends ClientEndpointConfig.Configurator{
  @Override
  public void beforeRequest(@NotNull Map<String, List<String>> headers) {
    headers.put("Host", List.of("web.whatsapp.com"));
    headers.put("Origin",  List.of("https://web.whatsapp.com"));
  }
}