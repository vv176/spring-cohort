package exceptionhandling.classroom.reflectserialization;

public class ScienceMarks {

    private int phyMarks;
    private int chemMarks;
    private int mathsMarks;

    public int getPhyMarks() {
        return phyMarks;
    }

    public void setPhyMarks(int phyMarks) {
        this.phyMarks = phyMarks;
    }

    public int getChemMarks() {
        return chemMarks;
    }

    public void setChemMarks(int chemMarks) {
        this.chemMarks = chemMarks;
    }

    public int getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(int mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public double getSciPercent() {
        return sciPercent;
    }

    public void setSciPercent(double sciPercent) {
        this.sciPercent = sciPercent;
    }

    private double sciPercent;

    public ScienceMarks(int phyMarks, int chemMarks, int mathsMarks, double sciPercent) {
        this.phyMarks = phyMarks;
        this.chemMarks = chemMarks;
        this.mathsMarks = mathsMarks;
        this.sciPercent = sciPercent;
    }

    public ScienceMarks() {}

}
