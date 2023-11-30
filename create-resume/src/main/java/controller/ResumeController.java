package controller;

import model.Career;
import model.Education;
import model.Person;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.ResumeView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResumeController {
    private XSSFWorkbook workbook;

    public ResumeController() {
        workbook = new XSSFWorkbook();
    }

    public static void main(String[] args) {
        ResumeController controller = new ResumeController();
        // 데이터 입력
        Object[] inputData = controller.inputData();
        // 엑셀 파일 추출
        controller.createExcel(inputData);
    }

    private Object[] inputData() {
        ResumeView resumeView = new ResumeView();

        Person person = resumeView.inputPersonInfo();
        List<Career> careers = resumeView.inputCareerList();
        List<Education> educations = resumeView.inputEducationList();
        String coverLetter = resumeView.inputCoverLetter();

        return new Object[] {person, careers, educations, coverLetter};
    }

    public void createExcel(Object[] objects) {
        System.out.println("엑셀 파일 생성 중 입니다.");

        Person person = (Person) objects[0];
        List<Career> careers = (List<Career>) objects[1];
        List<Education> educations = (List<Education>) objects[2];
        String coverLetter = (String) objects[3];

        // 이력서 시트 작성
        createResumeSheet(person, careers, educations);
        // 자기소개서 시트 작성
        createCoverLetterSheet(coverLetter);
        // 엑셀 파일 생성
        createFile();
    }

    private void createFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String fileName = "excel/" + "이력서_" + LocalDateTime.now().format(formatter) + ".xlsx";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            workbook.close();

            System.out.println("엑셀 파일이 생성되었습니다.");
        } catch (IOException e) {
            System.out.println("엑셀 파일 생성 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    private void createResumeSheet(Person person, List<Career> careers, List<Education> educations) {
        int startRow = 0;

        Sheet sheet = workbook.createSheet("이력서");
        sheet.setColumnWidth(0, 7000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 10000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);

        // 개인이력 헤더 생성
        Row personHeaderRow = sheet.createRow(startRow);
        personHeaderRow.createCell(0).setCellValue("사진");
        personHeaderRow.createCell(1).setCellValue("이름");
        personHeaderRow.createCell(2).setCellValue("이메일");
        personHeaderRow.createCell(3).setCellValue("주소");
        personHeaderRow.createCell(4).setCellValue("전화번호");
        personHeaderRow.createCell(5).setCellValue("생년월일");
        setHeaderCellStyle(personHeaderRow, 6);
        startRow++;

        // 개인이력 바디 생성
        Row personDataRow = sheet.createRow(startRow);

        try {
            String imagePath = "image/"+person.getPhoto();
            InputStream inputStream = new FileInputStream(imagePath);
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // 증명사진 크기로 이미지를 조절
            int width = (int) (45 * 2.83465);
            int height = (int) (45 * 2.83465);
            Image resizeImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizeBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2d = resizeBufferedImage.createGraphics();
            g2d.drawImage(resizeImage, 0, 0, null);
            g2d.dispose();

            // 조절된 이미지를 바이트 배열로 변환
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String formatName = person.getFileExtension();
            if ("jpeg".equals(formatName)) formatName = "jpg";
            ImageIO.write(resizeBufferedImage, formatName, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            int format = 0;
            switch (formatName) {
                case "png":
                    format = Workbook.PICTURE_TYPE_PNG;
                    break;
                case "jpg":
                    format = Workbook.PICTURE_TYPE_JPEG;
                    break;
                case "jpeg":
                    format = Workbook.PICTURE_TYPE_JPEG;
                    break;
            }
            int imageIndex = workbook.addPicture(imageBytes, format);

            // Drawing 객체를 생성하고 이미지를 삽입
            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 1, 1, 2);
            drawing.createPicture(anchor, imageIndex);

            personDataRow.setHeightInPoints(height*72/96);

            int columnWidth = (int) Math.floor(((float) width / (float) 8) * 256);
            sheet.setColumnWidth(0, columnWidth);

            inputStream.close();
        } catch (IOException e) {
            System.out.println("image 폴더에 해당 이미지가 없습니다.");
            e.printStackTrace();
        }

        personDataRow.createCell(0).setCellValue(person.getPhoto());
        personDataRow.createCell(1).setCellValue(person.getName());
        personDataRow.createCell(2).setCellValue(person.getEmail());
        personDataRow.createCell(3).setCellValue(person.getAddress());
        personDataRow.createCell(4).setCellValue(person.getPhoneNumber());
        personDataRow.createCell(5).setCellValue(person.getBirthDate());
        setBodyCellStyle(personDataRow, 6);
        startRow++;

        // 학력사항 헤더 생성
        Row educationHeaderRow = sheet.createRow(startRow);
        educationHeaderRow.createCell(0).setCellValue("졸업연도");
        educationHeaderRow.createCell(1).setCellValue("학교명");
        educationHeaderRow.createCell(2).setCellValue("전공");
        educationHeaderRow.createCell(3).setCellValue("졸업여부");
        setHeaderCellStyle(educationHeaderRow, 4);
        startRow++;

        // 학력사항 바디 생성
        for (int i = 0; i< educations.size(); i++) {
            Row educationDataRow = educationDataRow = sheet.createRow(i + startRow);
            educationDataRow.createCell(0).setCellValue(educations.get(i).getGraduationYear());
            educationDataRow.createCell(1).setCellValue(educations.get(i).getGraduationYear());
            educationDataRow.createCell(2).setCellValue(educations.get(i).getGraduationYear());
            educationDataRow.createCell(3).setCellValue(educations.get(i).getGraduationYear());
            setBodyCellStyle(educationDataRow, 4);
        }

        if (0 == educations.size()) {
            Row educationDataRow = educationDataRow = sheet.createRow(startRow);
            educationDataRow.createCell(0).setCellValue("");
            educationDataRow.createCell(1).setCellValue("");
            educationDataRow.createCell(2).setCellValue("");
            educationDataRow.createCell(3).setCellValue("");
            setBodyCellStyle(educationDataRow, 4);
            startRow++;
        } else {
            startRow = startRow + educations.size();
        }

        // 경력사항 헤더 생성
        Row careerHeaderRow = sheet.createRow(startRow);
        careerHeaderRow.createCell(0).setCellValue("근무기간");
        careerHeaderRow.createCell(1).setCellValue("근무처");
        careerHeaderRow.createCell(2).setCellValue("담당업무");
        careerHeaderRow.createCell(3).setCellValue("근속연수");
        setHeaderCellStyle(careerHeaderRow, 4);
        startRow++;

        // 경력사항 바디 생성
        for (int i = 0; i< careers.size(); i++) {
            Row careerDataRow = sheet.createRow(i + startRow);
            careerDataRow.createCell(0).setCellValue(careers.get(i).getWorkPeriod());
            careerDataRow.createCell(1).setCellValue(careers.get(i).getCompanyName());
            careerDataRow.createCell(2).setCellValue(careers.get(i).getJobTitle());
            careerDataRow.createCell(3).setCellValue(careers.get(i).getEmploymentYears());
            setBodyCellStyle(careerDataRow, 4);
        }

        if (0 == careers.size()) {
            Row careerDataRow = sheet.createRow(startRow);
            careerDataRow.createCell(0).setCellValue("");
            careerDataRow.createCell(1).setCellValue("");
            careerDataRow.createCell(2).setCellValue("");
            careerDataRow.createCell(3).setCellValue("");
            setBodyCellStyle(careerDataRow, 4);
            startRow++;
        } else {
            startRow = startRow + careers.size();
        }
    }

    private void createCoverLetterSheet(String coverLetter) {
        Sheet sheet2 = workbook.createSheet("자기소개서");
        sheet2.setColumnWidth(0, 25000);

        Row coverLetterDataRow = sheet2.createRow(0);
        coverLetterDataRow.createCell(0).setCellValue(coverLetter);
        setCoverLetterCellStyle(coverLetterDataRow, 1);
    }

    private void setCoverLetterCellStyle(Row bodyRow, int rowSize) {
        CellStyle coverLetterCellStyle = workbook.createCellStyle();
        // 자기소개서 셀스타일 설정
        coverLetterCellStyle.setWrapText(true);

        // 자기소개서 폰트 설정
        Font font = workbook.createFont();
        font.setFontName("굴림");
        coverLetterCellStyle.setFont(font);

        // 자기소개서 셀스타일 적용
        for (int i=0; i<rowSize; i++) {
            Cell cell = bodyRow.getCell(i);
            cell.setCellStyle(coverLetterCellStyle);
        }
    }

    private void setBodyCellStyle(Row bodyRow, int rowSize) {
        if (rowSize < 1) return;
        CellStyle bodyCellStyle = workbook.createCellStyle();
        // 바디 셀스타일 설정
        bodyCellStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 바디 폰트 설정
        Font font = workbook.createFont();
        font.setFontName("굴림");
        bodyCellStyle.setFont(font);

        // 바디 셀스타일 적용
        for (int i=0; i<rowSize; i++) {
            Cell cell = bodyRow.getCell(i);
            cell.setCellStyle(bodyCellStyle);
        }
    }

    private void setHeaderCellStyle(Row headerRow, int rowSize) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        // 헤더 셀스타일 설정
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 헤더 폰트 설정
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("굴림");
        headerCellStyle.setFont(font);

        // 헤더 셀스타일 적용
        for (int i=0; i<rowSize; i++) {
            Cell cell = headerRow.getCell(i);
            cell.setCellStyle(headerCellStyle);
        }
    }

}
