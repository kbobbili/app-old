package com.kalbob.app.es;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HistMessage {
  private String tenantId;
  private String application;
  private String channel;
  private String channelUserId;
  private String sessionId;
  private Map<String, Object> message;
  private String messageDirection;
  private ApplicationMeta applicationMeta;
  private ZonedDateTime sentAt;
  private String test;
  private Map<String, Object> tags = new HashMap<>();
}
