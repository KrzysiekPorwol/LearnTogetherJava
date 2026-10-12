package pd5.service;

import pd5.model.Sendable;

public class NotificationSender {

  public void sendAll(Sendable[] notifications) {
    for (Sendable notification : notifications) {
      notification.send();
    }
  }
}
