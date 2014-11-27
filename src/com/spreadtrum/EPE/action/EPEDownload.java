package com.spreadtrum.EPE.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.EPE.dao.EPETestFormDAO;
import com.spreadtrum.EPE.dao.impl.EPETestFormDAOImpl;
import com.spreadtrum.EPE.model.EPETestForm;
import com.spreadtrum.EPE.service.EPEOverallInfo;
import com.spreadtrum.EPE.service.EPEOverallInfoImpl;
import com.spreadtrum.EPE.service.EPESummary;
import com.spreadtrum.monkeytest.action.SaveExcel;

public class EPEDownload extends ActionSupport{
    private static final long serialVersionUID = 1L;
    private String contentType;
    private long contentLenght;
    private String contentDisposition;
    private InputStream inputStream;
    //private String testFormName = null;
    private String currentFormName;
    private List<EPESummary> overallInfoList = new ArrayList<EPESummary>();
    private EPEOverallInfo overallInfo = new  EPEOverallInfoImpl();
      
    @Override
    public String execute() throws Exception {
    	//第一个总结表    
    	overallInfoList = overallInfo.receiveOverallInfo(currentFormName);
    		
	   //生成excel文件
			String[] SummaryInfoHeader = { "Version", "手机数/台", "Pass手机数", "Fail手机数","epe值","锁屏","黑屏","重启","Sysdump","掉电","其他","Conclusion"};
			//String[] TimeInfoHeader = { "item", "测试时间", "第一次出错时间"};
			//String[] ErrorTypeHeader = { "模块", "JavaCrash", "NativeCrash","ANR"};
			//String[] DeviceInfoHeader = { "手机编号", "设备有效性", "测试时间","停止原因","首错时间","首错类型","首错模块"};
			
			try{
				//OutputStream out = new FileOutputStream("/home7/qilongyin/Documents/" + currentFormName + ".xls");
				OutputStream out = new FileOutputStream("/home/likewise-open/SPREADTRUM/senxue.jing/Downloads/" + currentFormName + ".xls");
				SaveExcel<EPESummary> seo = new SaveExcel<EPESummary>();
				
				// 声明一个工作薄
				HSSFWorkbook workbook = new HSSFWorkbook();
				// 生成一个表格
				HSSFSheet sheet = workbook.createSheet("EPE测试信息");
				//HSSFSheet sheet1 = workbook.createSheet("概述");
				// 输出测试汇总信息
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell(0);
				HSSFRichTextString text = new HSSFRichTextString("一 测试结论");
				cell.setCellValue(text);
				sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 14));
				row = sheet.createRow(1);
				seo.saveExcel(workbook, SummaryInfoHeader, overallInfoList, sheet, row, 1);
				
			try {
					workbook.write(out);
			} catch (IOException e) {
							// TODO Auto-generated catch block
					e.printStackTrace();
				}
			out.close();
			}catch (IOException e) {
				e.printStackTrace();
			}    	

    	//下载服务器上的文件到本地
                setContentType("text/xls");
                setContentDisposition("attachment;filename=" + currentFormName + ".xls");
                // ServletContext servletContext =
                // ServletActionContext.getServletContext();
                //String filename = "/home7/qilongyin/Documents/" + currentFormName + ".xls";
                String filename = "/home/likewise-open/SPREADTRUM/senxue.jing/Downloads/" + currentFormName + ".xls";
                inputStream = new FileInputStream(filename);
                setContentLenght(inputStream.available());
                return SUCCESS;
    }   
    
    
    public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getCurrentFormName() {
		return currentFormName;
	}

	public void setCurrentFormName(String currentFormName) {
		this.currentFormName = currentFormName;
	}

	public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public long getContentLenght() {
        return contentLenght;
    }
    public void setContentLenght(long contentLenght) {
        this.contentLenght = contentLenght;
    }
    public String getContentDisposition() {
        return contentDisposition;
    }
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}