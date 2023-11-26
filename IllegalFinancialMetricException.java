package exceptionhandling.classroom;

public class IllegalFinancialMetricException extends Exception{

    private String message;


    public IllegalFinancialMetricException(String message) {
        super(message);
        this.message = message;
    }
    public IllegalFinancialMetricException() {
    }

    public IllegalFinancialMetricException(Throwable e) {
        super(e);
    }

    public IllegalFinancialMetricException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

}
