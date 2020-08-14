package com.xy.controller;

import com.xy.entity.Score;
import com.xy.entity.Student;
import com.xy.service.IStudentService;
import com.xy.utils.ExcelOutUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class StudentController {

        @Autowired
        private IStudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @RequestMapping("/selstudent")
    public String selstudent(Student student, HttpServletRequest request){

        List<Student> studentList=studentService.selstudent();
        request.setAttribute("studentList",studentList);
        return "forward:show.jsp";
    }
    @RequestMapping("/delstudent/{sid}")
    public String delstudent(@PathVariable("sid") int sid, HttpServletResponse response){

        int a  =  studentService.delstudent(sid);

        return "redirect:/selstudent";
    }
    @RequestMapping("/updatepage/{sid}")
    public String updatepage(@Param("sid")@PathVariable("sid")int sid, HttpServletRequest request){

        Student student1=studentService.queryBysid(sid);
        request.setAttribute("student1",student1);
        return "forward:/updatepage.jsp";
    }
    @RequestMapping("/updatestudent")
    public String updatestudent(Student student){



        studentService.updatestudent(student);

        return "redirect:/selstudent";
    }
    @RequestMapping("/insertstudent")
    public String insertstudent(String sname){

        studentService.insertstudent(sname);
        return "redirect:/selstudent";
    }
    @RequestMapping("/excelout")
    public String excelout(HttpServletRequest request, HttpServletResponse response){
        //数据集合
        List<Student> studentList=studentService.selstudent();
        //excel字段
        String[] title = {"编号","姓名","分数"};
        //文件名
        ExcelOutUtil excel=new ExcelOutUtil();

        String fileName="学生信息表.xls";
        String[][] content = excel.getContent(studentList.size());
        //工作簿名字
        String sheetName="信息";
        //文件头标题
        String head="学生信息";
        //将数据取出放入集合
        for (int i=0;i<studentList.size();i++){
            content[i] = new String[title.length];
            Student student=studentList.get(i);
            content[i][0] = String.valueOf(student.getSid());
            content[i][1] = String.valueOf(student.getSname());
            content[i][2] = String.valueOf(student.getScore());

        }
        //调用excel 工具类中的方法
        HSSFWorkbook hssfWorkbook=ExcelOutUtil.getHSSFWorkbook(sheetName,title,content,null,head);
        //调用发送响应流方法
        setResponseHeader(response,fileName);
        try (OutputStream os = response.getOutputStream()) {
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
        //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            //log.info("导出excel功能,发送响应流方法异常!");
            ex.printStackTrace();
        }
    }
    @RequestMapping("/uploadexcel")
    public String uploadexcel( MultipartFile realpath ) throws  Exception{

        int[] resultCell = new int[]{0,1,2};
        String fileName = realpath.getOriginalFilename();
        List<Student> resultList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")){
            logger.error("上传文件不正确");
        }
        boolean isExcelfile = true;
        if(fileName.matches("^.+\\.(?i)(xlsx)$")){
            isExcelfile=false;
        }
        InputStream is = realpath.getInputStream();
        Workbook wb = null;
        if (isExcelfile){
            wb = new HSSFWorkbook(is);
        }else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        resultList=getSheetVal(sheet,resultCell);
        System.out.println("结果是--->"+resultList);
        studentService.addStudent(resultList);

        return "forward:/success.jsp";
    }

    private List<Student> getSheetVal(Sheet sheet, int[] resultCell) {

        List<Student> studentList=new ArrayList<>();
        int [] resultIndex = new int[resultCell.length];
        Student student;

        for (int r = 2;r <= sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            student =new Student();
            for(int i=0;i<row.getPhysicalNumberOfCells();i++){
                
                String temp = getCellVal(row.getCell(i)).toString().trim();

                    for (int j = 0;j<resultCell.length;j++){
                        if (i==resultCell[j]){
                            switch (i){
                                case 1:
                                    System.out.println(temp);
                                    student.setSname(temp);
                                    break;
                                case 2:
                                    System.out.println(temp);
                                    student.setScore(temp);
                                    break;
                                default:
                                    break;
                            }
                        }else {
                            continue;
                        }
                    }


            }
            studentList.add(student);
    }
        return studentList;
}

    public Object getCellVal(Cell cell){
        Object obj  =  null;
        switch (cell.getCellTypeEnum()){
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }
    @RequestMapping("/addStudents")
    public String addStudents(Student student){

        studentService.addStudents(student);
        return "forward:/success.jsp";
    }
    @RequestMapping("/StudentsEcharsShow")
    @ResponseBody
    public List<Score> StudentsEcharsShow(Model model){
        List<Score> list=new ArrayList<>();
        int you=studentService.selstudentYou();
        int liang=studentService.selstudentLiang();
        int jige=studentService.selstudentJige();
        int cha=studentService.selstudentCha();
        list.add(new Score("优秀",you));
        list.add(new Score("良好",liang));
        list.add(new Score("及格",jige));
        list.add(new Score("不及格",cha));
        return  list;
    }




    }
