package view;

import model.Career;
import model.Education;
import model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResumeView {
    Scanner scanner;
    public ResumeView() {
        scanner = new Scanner(System.in);
    }
    public Person inputPersonInfo() {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        String fileExtension = fileExtensions.stream().collect(Collectors.joining(", "));
        System.out.print("확장자를 포함한 사진 파일명을 입력하세요("+fileExtension+" 파일만 가능) : ");
        String photo = validatePhotoName(scanner, fileExtensions);
        System.out.print("이름을 입력하세요 : ");
        String name = scanner.nextLine();
        System.out.print("이메일을 입력하세요 : ");
        String email = scanner.nextLine();
        System.out.print("주소를 입력하세요 : ");
        String address = scanner.nextLine();
        System.out.print("전화번호를 입력하세요 : ");
        String phoneNumber = scanner.nextLine();
        System.out.print("생년월일을 입력하세요(예: 1990-01-01) : ");
        String birthDate = scanner.nextLine();

        return new Person(name, email, address, phoneNumber, birthDate, photo);
    }

    public List<Career> inputCareerList() {
        List<Career> careers = new ArrayList<>();
        while (true) {
            System.out.println("경력정보를 입력하세요 (종료는 q) : ");
            System.out.println("근무기간 근무처 담당업무 근속연수");
            String careerInfo = scanner.nextLine();
            if ("q".equals(careerInfo)) break;

            if (Career.class.getDeclaredFields().length != careerInfo.split("\\s+").length) {
                System.out.println("잘못된 형식의 경력사항을 입력했습니다. 다시 입력해주세요.");
                continue;
            }
            careers.add(new Career(careerInfo));
        }

        return careers;
    }

    public List<Education> inputEducationList() {
        List<Education> educations = new ArrayList<>();
        while (true) {
            System.out.println("학력정보를 입력하세요 (종료는 q) : ");
            System.out.println("졸업년도 학교명 전공 졸업여부");
            String educationInfo = scanner.nextLine();
            if ("q".equals(educationInfo)) break;

            if (4 != educationInfo.split("\\s+").length) {
                System.out.println("잘못된 형식의 학력사항을 입력했습니다. 다시 입력해주세요.");
                continue;
            }
            educations.add(new Education(educationInfo));
        }
        return educations;
    }

    public String inputCoverLetter() {
        System.out.println("자기소개서를 입력하세요. 연속으로 빈 줄을 3번 입력하면 입력이 종료됩니다.");
        int newLineCount = 0;
        StringBuilder sb = new StringBuilder();
        while (newLineCount<3) {
            String line = scanner.nextLine();
            if (line.isEmpty()) newLineCount++;
            else {
                newLineCount = 0;
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private String validatePhotoName(Scanner scanner, List<String> fileExtensions) {
        String photo = "";
        while (true) {
            photo = scanner.nextLine();
            if (!photo.contains(".")) {
                System.out.print("확장자가 포함되지 않았습니다. 파일명을 다시 입력해주세요 : ");
            } else if (Pattern.compile("[^0-9a-zA-Z가-힣.]").matcher(photo.substring(0, photo.lastIndexOf("."))).find()) {
                System.out.print("파일명은 숫자와 문자로만 구성되어야합니다. 파일명을 다시 입력해주세요 : ");
            } else if (!fileExtensions.contains(photo.substring(photo.lastIndexOf(".")+1).toLowerCase())) {
                System.out.print("지원하지 않는 확장자 입니다. 파일명을 다시 입력해주세요 : ");
            } else {
                break;
            }
        }
        return photo;
    }
}
