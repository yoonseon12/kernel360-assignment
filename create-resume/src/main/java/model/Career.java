package model;

public class Career {
    private String workPeriod;  // 근무기간
    private String companyName; // 근무처
    private String jobTitle; // 담당업무
    private String employmentYears; // 근속연수

    public Career() {}

    public Career(String careerInfo) {
        String[] careerArr = careerInfo.split("\\s+");
        this.workPeriod = careerArr[0];
        this.companyName = careerArr[1];
        this.jobTitle = careerArr[2];
        this.employmentYears = careerArr[3];
    }

    @Override
    public String toString() {
        return "Career{" +
                "workPeriod='" + workPeriod + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", employmentYears='" + employmentYears + '\'' +
                '}';
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getEmploymentYears() {
        return employmentYears;
    }
}
