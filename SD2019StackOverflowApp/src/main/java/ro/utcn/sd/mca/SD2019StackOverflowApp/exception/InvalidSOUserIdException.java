package ro.utcn.sd.mca.SD2019StackOverflowApp.exception;

public class InvalidSOUserIdException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid sOUserId!";
    }
}
