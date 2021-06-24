
public class Grade {
    private Student std;
    private Subject sbj;
    private double lab, PT, FE;
    protected double average(){
        return 0.3 * lab + 0.2 * PT + 0.5 * FE;
    }

    public Grade() {
    }

    public Grade(Student std, Subject sbj, double lab, double PT, double FE) {
        this.std = std;
        this.sbj = sbj;
        this.lab = lab;
        this.PT = PT;
        this.FE = FE;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public Subject getSbj() {
        return sbj;
    }

    public void setSbj(Subject sbj) {
        this.sbj = sbj;
    }

    public double getLab() {
        return lab;
    }

    public void setLab(double lab) {
        this.lab = lab;
    }

    public double getPT() {
        return PT;
    }

    public void setPE(double PT) {
        this.PT = PT;
    }

    public double getFE() {
        return FE;
    }

    public void setFE(double FE) {
        this.FE = FE;
    }
    
}
