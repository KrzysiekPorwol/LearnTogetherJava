package pd5.model;

final class ContentValidator {

  private ContentValidator() {
  }

  static void requireNotBlank(String contents) {
    if (contents == null || contents.isBlank()) {
      throw new IllegalArgumentException("Tekst nie może być pusty ani składać się z samych białych znaków.");
    }
  }
}
