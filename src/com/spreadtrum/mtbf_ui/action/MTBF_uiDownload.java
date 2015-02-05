package com.spreadtrum.mtbf_ui.action;

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
import com.spreadtrum.mtbf_ui.service.Mtbf_uiErrorinfo;
import com.spreadtrum.mtbf_ui.service.Mtbf_uiStatisticinfo;
import com.spreadtrum.mtbf_ui.service.Mtbf_uiSummary;
import com.spreadtrum.mtbf_ui.service.Mtbf_uiOverallinfo;
import com.spreadtrum.mtbf_ui.service.Mtbf_uiOverallinfoImpl;
import com.spreadtrum.monkeytest.action.SaveExcel;
import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;

public class MTBF_uiDownload extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String contentType;
	private long contentLenght;
	private String contentDisposition;
	private InputStream inputStream;
	// private String testFormName = null;
	private String currentFormName;
	private List<Mtbf_uiSummary> overallnfoList = new ArrayList<Mtbf_uiSummary>();

	private Mtbf_uiOverallinfo overallInfo = new Mtbf_uiOverallinfoImpl();
	private List<Mtbf_uiStatisticinfo> overallnfoList1 = new ArrayList<Mtbf_uiStatisticinfo>();
	private List<Mtbf_uiErrorinfo> overallnfoList2 = new ArrayList<Mtbf_uiErrorinfo>();

	@Override
	public String execute() throws Exception {
		// 第一个总结表
		System.out.println(currentFormName);
		overallnfoList = overallInfo.receiveOverallTestinfo(currentFormName);
		overallnfoList1 = overallInfo.receiveStatisticinfo(currentFormName);
		overallnfoList2 = overallInfo.receiveMTBF_uiErrorInfo(currentFormName);

		// 生成excel文件
		String[] SummaryInfoHeader = { "工程名", "测试版本", "PAC地址", "初步结论" };
		String[] staticsticInfoHeader = { "设备总数", "执行轮数", "运行总时间(h)", "总故障数",
				"MTBF值" };
		String[] deviceErrorHeader = { "设备编号", "每台故障数" };

		// String[] TimeInfoHeader = { "item", "测试时间", "第一次出错时间"};
		// String[] ErrorTypeHeader = { "模块", "JavaCrash", "NativeCrash","ANR"};
		// String[] DeviceInfoHeader = { "手机编号", "设备有效性",
		// "测试时间","停止原因","首错时间","首错类型","首错模块"};

		try {
			// OutputStream out = new
			// FileOutputStream("/home7/qilongyin/Documents/" + currentFormName
			// + ".xls");
			OutputStream out = new FileOutputStream(
					"/home7/qilongyin/files/mtbf_ui/"
							+ currentFormName + ".xls");
			SaveExcel<Mtbf_uiSummary> seo = new SaveExcel<Mtbf_uiSummary>();
			SaveExcel<Mtbf_uiStatisticinfo> sel = new SaveExcel<Mtbf_uiStatisticinfo>();
			SaveExcel<Mtbf_uiErrorinfo> sek = new SaveExcel<Mtbf_uiErrorinfo>();

			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("mtbf_ui测试信息");
			// HSSFSheet sheet1 = workbook.createSheet("概述");
			// 输出测试汇总信息
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			HSSFRichTextString text = new HSSFRichTextString("一 测试版本");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 14));
			row = sheet.createRow(1);
			seo.saveExcel(workbook, SummaryInfoHeader, overallnfoList, sheet,
					row, 1);
			// 输出统计信息
			int rownum = overallnfoList.size() + 3;
			System.out.println("rownum"+rownum);
			row = sheet.createRow(rownum);
			cell = row.createCell(0);
			text = new HSSFRichTextString("二 统计信息");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(rownum, (short) 0, rownum,
					(short) 10));
			row = sheet.createRow(rownum + 1);

			sel.saveExcel(workbook, staticsticInfoHeader, overallnfoList1,
					sheet, row, rownum + 1);
			// 故障信息
			int rownum1 =  rownum + overallnfoList1.size()  + 3;
			row = sheet.createRow(rownum1);
			cell = row.createCell(0);
			text = new HSSFRichTextString("三 故障信息");
			cell.setCellValue(text);
			System.out.println("rownum1"+overallnfoList2);
			sheet.addMergedRegion(new Region(rownum1, (short) 0, rownum1,
					(short) 10));
			row = sheet.createRow(rownum1 + 1);

			sek.saveExcel(workbook, deviceErrorHeader, overallnfoList2, sheet,
					row, rownum1 + 1);

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

		// 下载服务器上的文件到本地
		setContentType("text/xls");
		setContentDisposition("attachment;filename=" + currentFormName + ".xls");
		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		// String filename = "/home7/qilongyin/Documents/" + currentFormName +
		// ".xls";
		String filename = "/home7/qilongyin/files/mtbf_ui/"
				+ currentFormName + ".xls";
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

	/*
	 * public List<MTBF_UISummary> getMTBFUITestinfo() { return MTBFUITestinfo;
	 * }
	 * 
	 * 
	 * public void setMTBFUITestinfo(List<MTBF_UISummary> mTBFUITestinfo) {
	 * MTBFUITestinfo = mTBFUITestinfo; }
	 */
}