package com.py.shijijingying.utils;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页参数
 */
public class PageUtil {

	// 指定的或是页面参数
	private int currentPage=1; // 当前页
	private int pageSize=10; // 每页显示多少条
	private Map<String, Object> searchMap=new HashMap<String,Object>();
	// 查询数据库获取
	private int recordCount; // 总记录数

	// 计算获取
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）
	
	public PageUtil() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage 当前页
	 * @param pageSize 每页显示多少条
	 * @param recordCount 总记录数
	 * @param recordList 本页的数据列表
	 */
	public PageUtil(int currentPage, int pageSize, int recordCount) {
		
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		
		//计算总页码
		pageCount = (recordCount + pageSize  -1) / pageSize ;
		if(this.currentPage > pageCount){
			this.currentPage = pageCount ;
		}
		if(this.currentPage < 1){
			this.currentPage =1;
		}
		//计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于7页，则全部显示
		if(pageCount <= 7){
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于7页，则显示当前页附近的5个页码
		else{
			//当前页附近的共7个页码(前3个 +当前页+ 后3个)
			beginPageIndex = currentPage - 3;
			endPageIndex = currentPage + 3;
			
			//当前面的页码不足3个时，则显示前3个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 7;
			}
			//当后面的页码不足2个时，则显示后5个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 7 + 1;
			}
		}
	}
	// 返回下一页
	public	int	getNext(){
		return (currentPage + 1 > pageCount) ? pageCount : (currentPage + 1);
	}
	// 返回上一页
	public	int	getPrev(){
		return (currentPage <= 1) ? 1 : (currentPage - 1);
	}
	public Map<String, Object> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, Object> searchMap) {
		this.searchMap = searchMap;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}
