package exceptionhandling.classroom.reflectserialization;

public class ArtsMarks {

    private int socialSciMarks;

    public int getSocialSciMarks() {
        return socialSciMarks;
    }

    public void setSocialSciMarks(int socialSciMarks) {
        this.socialSciMarks = socialSciMarks;
    }

    public int getLiteratureMarks() {
        return literatureMarks;
    }

    public void setLiteratureMarks(int literatureMarks) {
        this.literatureMarks = literatureMarks;
    }

    public double getArtsPercent() {
        return artsPercent;
    }

    public void setArtsPercent(double artsPercent) {
        this.artsPercent = artsPercent;
    }

    private int literatureMarks;
    private double artsPercent;

    public ArtsMarks(int socialSciMarks, int literatureMarks, double artsPercent) {
        this.socialSciMarks = socialSciMarks;
        this.literatureMarks = literatureMarks;
        this.artsPercent = artsPercent;
    }

    public ArtsMarks() {}

}
