package org.example.exceptions;

public class DoesNotExistException extends Throwable {
    private static final long serialVersionUID = 1L;

    public DoesNotExistException(final Entity entity) {
        super(entity.getEntity() + " does not exist");
    }
}
