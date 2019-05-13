package ro.utcn.sd.mca.SD2019StackOverflowApp.exception;

public class InvalidAnswerIdException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid answerId!";
    }
}
