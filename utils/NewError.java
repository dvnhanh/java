package utils;

public class NewError {
    private String message;
    
    public NewError(String message) {
       setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
