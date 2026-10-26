package pd7_1.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransportType {
  CAR("Car"),
  TRAIN("Train"),
  PLANE("Plane");

  private final String description;
}