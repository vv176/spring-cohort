package exceptionhandling.classroom.controllers;

public class ExamResult {
    public int getPhy() {
        return phy;
    }

    public int getChem() {
        return chem;
    }

    public int getMaths() {
        return maths;
    }

    private int phy, chem, maths;

    public ExamResult(int phy, int chem, int maths) {
        this.phy = phy;
        this.chem = chem;
        this.maths = maths;

    }
}

// ServletContainer(Tomcat) creates a servlet => Dispatcher Servlet => Interceptors => Controller
// Front Controller

// Interceptors
// cross cutting concerns