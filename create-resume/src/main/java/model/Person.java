package model;

public class Person {
    private String name; // 이름
    private String email; // 이메일
    private String address; // 주소
    private String phoneNumber; // 전화번호
    private String birthDate; // 생년월일
    private String photo; // 사진 링크
    private String fileExtension; // 사진파일 확장자

    public Person() {}

    public Person(String name, String email, String address, String phoneNumber, String birthDate, String photo) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.photo = photo;
        this.fileExtension =photo.substring(photo.lastIndexOf(".")+1).toLowerCase();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", photo='" + photo + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
