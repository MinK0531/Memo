package com.mink.memo.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public final static String FILE_UPLOAD_PATH = "D:\\KMK\\SpringProject\\upload\\memo";


    // 파일을 전달 받아, 정해진 경로에 저장하고,
    // 해당 파일을 클라이언트가 접근할 수 있는  url 경로 리턴
    public static String saveFile(long userId, MultipartFile file){

        if(file == null){
            return null;
        }
        // 원본파일 이름 그대로 저장
        // 디렉토리(폴더)로 구분해서 파일 저장
        // 디렉토리 이름 : 사용자 정보 + 시간 정보 (ex) 3_3540301350430
        // UNIX TIME : 1970년 1월 1일 0시 0분 0초 이후로 흐른시간(millisecond)

        String directoryName = "/" + userId + "_" + System.currentTimeMillis();

        // 디렉토리 만들기
        // 전체 디렉토리 경로

        String directoryPath = FILE_UPLOAD_PATH + directoryName;

        File directory = new File(directoryPath);

        if(!directory.mkdir()){
            //디렉토리 생성 실패
            return null;

        }
        // 파일 저장
        String filePath = directoryPath + "/" + file.getOriginalFilename();


        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get(filePath);
            Files.write(path, bytes);

        }catch (IOException e){
            return null;
        }

        //서버 파일 경로 : D:\\KMK\\SpringProject\\upload\\memo/3_53645345834/test.png

        // url path : /images/3_53645345834/test.png
        return  "/images" + directoryName + "/" + file.getOriginalFilename();

    }

    //파일 삭제 가능
    public static boolean removeFile(String imagePath){

        if(imagePath == null){
            return false;
        }

        // url path : /images/3_53645345834/test.png
        String fullFilePath = FILE_UPLOAD_PATH + imagePath.replace("/images","");

        Path path = Paths.get(fullFilePath);
        Path directoryPath = path.getParent();

        try {
            Files.delete(path);
            Files.delete(directoryPath);
        } catch (IOException e) {
            return false;
        }

        return  true;

    }

}
