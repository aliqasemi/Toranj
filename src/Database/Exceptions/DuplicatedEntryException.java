package Database.Exceptions;

public class DuplicatedEntryException extends DatabaseException {

    public DuplicatedEntryException(String message) {
        super(message);
    }
}
