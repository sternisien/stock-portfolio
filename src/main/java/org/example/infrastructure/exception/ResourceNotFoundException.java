package org.example.infrastructure.exception;

import java.time.LocalDateTime;

/** Exception pour les resources non trouvées */
public class ResourceNotFoundException extends RuntimeException {

  private final String resourceType;
  private final LocalDateTime localDateTime;

  public ResourceNotFoundException(String message, String resourceType) {
    super(message);
    this.resourceType = resourceType;
    this.localDateTime = LocalDateTime.now();
  }

  public String getResourceType() {
    return resourceType;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }
}
