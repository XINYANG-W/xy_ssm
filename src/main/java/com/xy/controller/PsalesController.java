package com.xy.controller;

import com.xy.entity.Psales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xy.service.IPsalesService;
import com.xy.utils.ExcelOutUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PsalesController {

    @Autowired
    private IPsalesService psalesService;
    private final Logger logger = LoggerFactory.getLogger(PsalesController.class);
    @RequestMapping("/selSales")
    @ResponseBody
    public List<Psales> selSales(Model model){
        List<Psales> psalesList= psalesService.selSales();

        return psalesList;
    }
    @RequestMapping("/exceloutPsales")
    public String excelout(HttpServletRequest request, HttpServletResponse response){
        //数据集合
        List<Psales> studentList=psalesService.selSales();
        //excel字段
        String[] title = {"编号","月份","业绩","组名"};
        //文件名
        ExcelOutUtil excel=new ExcelOutUtil();

        String fileName="业绩表.xls";
        String[][] content = excel.getContent(studentList.size());
        //工作簿名字
        String sheetName="信息";
        //文件头标题
        String head="学生信息";
        //将数据取出放入集合
        for (int i=0;i<studentList.size();i++){
            content[i] = new String[title.length];
            Psales psales=studentList.get(i);
            content[i][0] = String.valueOf(psales.getPid());
            content[i][1] = String.valueOf(psales.getPmonth());
            content[i][2] = String.valueOf(psales.getPnum());
            content[i][3] = String.valueOf(psales.getTid());
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



    @RequestMapping("/uploadexcelpsales")
    public String uploadexcel( MultipartFile realpath ) throws  Exception{

        int[] resultCell = new int[]{0,1,2,3};
        String fileName = realpath.getOriginalFilename();
        List<Psales> resultList = new ArrayList<>();
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
        psalesService.addPsales(resultList);

        return "forward:/success.jsp";
    }

    private List<Psales> getSheetVal(Sheet sheet, int[] resultCell) {

        List<Psales> studentList=new ArrayList<>();
        int [] resultIndex = new int[resultCell.length];
        Psales student;

        for (int r = 2;r <= sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            student =new Psales();
            for(int i=0;i<row.getPhysicalNumberOfCells();i++){

                String temp = getCellVal(row.getCell(i)).toString().trim();

                for (int j = 0;j<resultCell.length;j++){
                    if (i==resultCell[j]){
                        switch (i){
                            case 1:
                                System.out.println(temp);
                                student.setPmonth(temp);
                                break;
                            case 2:
                                System.out.println(temp);
                                student.setPnum(temp);
                                break;
                            case 3:
                                System.out.println(temp);
                                int temptid=(int)Double.parseDouble(temp);
                                student.setTid(temptid);
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
    @RequestMapping("/PsalesCurrentMonthecharts")
    @ResponseBody
    public List<Map<String, Object>> PsalesCurrentMonthecharts(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        String substring = format.substring(0, 7);
        List<Map<String,Object>> list=psalesService.PsalesCurrentMonth(substring);
        System.out.println(456);
        return list;
    }

    @RequestMapping("/PsalesCurrentMonth")

    public String PsalesCurrentMonth(HttpServletRequest request){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        String substring = format.substring(0, 7);
        List<Map<String,Object>> psaleslist=psalesService.PsalesCurrentMonth(substring);
        request.setAttribute("psaleslist",psaleslist);

        return "forward:/jsp/adminMonth.jsp";
    }
    @ResponseBody
    @RequestMapping("queryTeamMember")
    public List<Map<String, Object>> queryTeamMember(HttpServletRequest request){
        String num = request.getParameter("num");
        int i = Integer.parseInt(num);

        List<Map<String,Object>> list_member=psalesService.queryTeamMember_one(i);

        return list_member;
    }
    @RequestMapping("/queryTeamMember_one")
    public String queryTeamMember_one(HttpServletRequest request){
        String num = request.getParameter("tid");
        int i = Integer.parseInt(num);
        System.out.println(num);
        List<Map<String,Object>> list_member=psalesService.queryTeamMember_one(i);
        request.setAttribute("list_member",list_member);
        return "forward:/jsp/adminTeamMember.jsp";
    }

    @RequestMapping("/RemoveMemeber")
    public String RemoveMemeber(int gid){
        psalesService.RemoveMemeber(gid);

        return "redirect:/queryTeamMember_one?tid=1";
    }
    @RequestMapping("/queryNoTeam")
    public String queryNoTeam(HttpServletRequest request){
        int i = 0;
        List<Map<String,Object>> list_NoTeame=psalesService.queryTeamMember_one(i);
        request.setAttribute("list_NoTeame",list_NoTeame);
        return "forward:/jsp/adminNoTeamMember.jsp";
    }
    @RequestMapping("/queryAllTeam")
    @ResponseBody
    public List<Map<String, Object>> queryAllTeam(){

        List<Map<String,Object>> list=psalesService.queryAllTeam();

        return list;
    }
    @RequestMapping("/UpdateTeam")
    public String UpdateTeam(HttpServletRequest request){
        String gidStr = request.getParameter("gid");
        String team = request.getParameter("team");
        int tid = Integer.parseInt(team);
        int gid = Integer.parseInt(gidStr);

        psalesService.UpdateTeam(gid,tid);

        return "redirect:/queryNoTeam";
    }
    @RequestMapping("/delGid")
    public String delGid(HttpServletRequest request){
        String gidStr = request.getParameter("gid");
        int gid = Integer.parseInt(gidStr);
        psalesService.delGid(gid);
        return "redirect:/queryNoTeam";
    }
}
