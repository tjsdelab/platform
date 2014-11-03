package com.spreadtrum.monkeytest.action;

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
import com.spreadtrum.monkeytest.service.MonkeyDetail;
import com.spreadtrum.monkeytest.service.impl.MonkeyDetailImpl;
import com.spreadtrum.monkeytest.vo.LowerTestInfo;
import com.spreadtrum.monkeytest.vo.OverallTimeInfo;
import com.spreadtrum.monkeytest.vo.UperTestInfo;

@SuppressWarnings("deprecation")
public class ProjectDownloadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String contentType;
	private long contentLenght;
	private String contentDisposition;
	private InputStream inputStream;
	private MonkeyDetail monkeyDetail=new MonkeyDetailImpl();
	private List<UperTestInfo> uperTestInfoList=new ArrayList<UperTestInfo>();
	private List<LowerTestInfo> lowerTestInfoList=new ArrayList<LowerTestInfo>();
	private List<OverallTimeInfo> overallTimeInfoList=new ArrayList<OverallTimeInfo>();
	private String testFormName = null; 
	
	public String getTestFormName() {
		return testFormName;
	}

	public void setTestFormName(String testFormName) {
		this.testFormName = testFormName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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

	
	@Override
	public String execute() throws Exception {

		String[] lTestInfoHeader = { "设备", "BUG ID", "运行时长", "最终状态", "现象描述",
				"初步分析" };
		// 上层信息标题
		String[] uTestInfoHeader = { "设备", "BUG ID", "首错时间", "首错类型",
				"首次发生JavaCrash的时间", "JavaCrash数目", "模块列表",
				"首次发生NativeCrash的时间", "NativeCrash数目", "模块列表", "首次发生ANR的时间",
				"ANR数目", "模块列表" };
		// 时间趋势标题
		String[] timeTendencyHeader = { "测试版本", "停止时间均值", "停止时间中值", "首错时间均值",
				"首错时间中值", "JavaCrash均值", "NativeCrash均值", "ANR均值" };
		try {
			// 工程的“底层测试信息”
			
			lowerTestInfoList = monkeyDetail.receiveLowerTestInfo(testFormName);

			// 工程的“上层测试信息”
			uperTestInfoList = monkeyDetail.receiveUperTestInfo(testFormName);

			// 时间趋势信息
			overallTimeInfoList = monkeyDetail
					.receiveOverallTimeInfo_List(testFormName);

			OutputStream out = new FileOutputStream(
					"/home7/qilongyin/files/monkeyDaily/"
							+ testFormName + ".xls");

			SaveExcel<LowerTestInfo> sel = new SaveExcel<LowerTestInfo>();
			SaveExcel<UperTestInfo> seu = new SaveExcel<UperTestInfo>();
			SaveExcel<OverallTimeInfo> seo = new SaveExcel<OverallTimeInfo>();

			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			String project = monkeyDetail.receiveProject(testFormName);
			HSSFSheet sheet = workbook.createSheet(project);

			// 输出下层测试信息
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			HSSFRichTextString text = new HSSFRichTextString("1 Test Details");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 10));
			row = sheet.createRow(1);

			sel.saveExcel(workbook, lTestInfoHeader, lowerTestInfoList, sheet,
					row, 1);

			// 输出上层测试信息
			int rownum = lowerTestInfoList.size() + 2;
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			text = new HSSFRichTextString(
					"2 Test Details：ANR、JavaCrash、NativeCrash");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(rownum, (short) 0, rownum,
					(short) 10));
			row = sheet.createRow(rownum + 1);
			seu.saveExcel(workbook, uTestInfoHeader, uperTestInfoList, sheet,
					row, rownum + 1);

			// 时间趋势信息
			rownum = lowerTestInfoList.size() + uperTestInfoList.size() + 4;
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			text = new HSSFRichTextString("3 Test Time tendency");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(rownum, (short) 0, rownum,
					(short) 10));
			row = sheet.createRow(rownum + 1);
			seo.saveExcel(workbook, timeTendencyHeader, overallTimeInfoList,
					sheet, row, rownum + 1);

			try {
				workbook.write(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		contentType = "text/xls";
		contentDisposition = "attachment;filename=" + testFormName + ".xls";

		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		String filename = "/home7/qilongyin/files/monkeyDaily/"
				+ testFormName + ".xls";

		inputStream = new FileInputStream(filename);
		contentLenght = inputStream.available();
		return SUCCESS;
	}
}