package com.spreadtrum.sanity_smoke.action;
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
import com.spreadtrum.monkeytest.action.SaveExcel;
import com.spreadtrum.sanity_smoke.dao.SanityTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SanityTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SanityTestInfo;


public class SanityFileDownload extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String contentType;
	private long contentLenght;
	private String contentDisposition;
	private InputStream inputStream;
	//private String testFormName = null; 
	private String currentFormName;
	private List<SanityTestInfo> allCaseList = new ArrayList<SanityTestInfo>();
	private SanityTestFormDAO sanityFormDAO = new SanityTestFormDAOImpl();

	@Override
	public String execute() throws Exception {
		
		//获取所有case的信息
		allCaseList = sanityFormDAO.getSanityTestInfoByTableName(currentFormName);
		
		//生成excel文件
		String[] TestInfoHeader = { "ID", "Case序号", "模块", "功能", "测试", "重要性", "操作顺序", "显示", "结果","comments", "标识位"};
		try{
		//OutputStream out = new FileOutputStream("/home7/qilongyin/Documents/" + currentFormName + ".xls");
		OutputStream out = new FileOutputStream("/home7/qilongyin/files/sanity/" + currentFormName + ".xls");			
		SaveExcel<SanityTestInfo> sel = new SaveExcel<SanityTestInfo>();
		
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sanity_case");
		//HSSFSheet sheet1 = workbook.createSheet("概述");
		// 输出测试信息
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		HSSFRichTextString text = new HSSFRichTextString("Case Details");
		cell.setCellValue(text);
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));
		row = sheet.createRow(1);

		sel.saveExcel(workbook, TestInfoHeader, allCaseList, sheet, row, 1);
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
		contentType = "text/xls";
		contentDisposition = "attachment;filename=" + currentFormName + ".xls";

		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		//String filename = "/home7/qilongyin/Documents/" + currentFormName + ".xls";
		String filename = "/home7/qilongyin/files/sanity/" + currentFormName + ".xls";		

		inputStream = new FileInputStream(filename);
		contentLenght = inputStream.available();
		return SUCCESS;
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
	public List<SanityTestInfo> getAllCaseList() {
		return allCaseList;
	}

	public void setAllCaseList(List<SanityTestInfo> allCaseList) {
		this.allCaseList = allCaseList;
	}
	
}