package exceptionhandling.classroom.reflectserialization;

public class ReportCard {

    private int id;
    private ScienceMarks scienceMarks;
    private ArtsMarks artsMarks;

    public void setId(int id) {
        this.id = id;
    }

    public ScienceMarks getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(ScienceMarks scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public ArtsMarks getArtsMarks() {
        return artsMarks;
    }

    public void setArtsMarks(ArtsMarks artsMarks) {
        this.artsMarks = artsMarks;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(double totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    private double totalPercentage;

    public ReportCard(int id, ScienceMarks scienceMarks, ArtsMarks artsMarks, double totalPercentage) {
        this.id = id;
        this.scienceMarks = scienceMarks;
        this.artsMarks = artsMarks;
        this.totalPercentage = totalPercentage;
    }

    public ReportCard() {}

    public int getId() {
        return id;
    }
}
