package model;

public class Education {
    private String graduationYear; // 졸업연도
    private String schoolName; // 학교명
    private String major; // 전공
    private String graduationStatus; // 졸업여부

    public Education() {};

    public Education(String educationInfo) {
        String[] educationArr = educationInfo.split("\\s+");
        this.graduationYear = educationArr[0];
        this.schoolName = educationArr[1];
        this.major = educationArr[2];
        this.graduationStatus = educationArr[3];
    }

    @Override
    public String toString() {
        return "Education{" +
                "graduationYear='" + graduationYear + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", graduationStatus='" + graduationStatus + '\'' +
                '}';
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getMajor() {
        return major;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }
}
