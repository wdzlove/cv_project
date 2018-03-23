package com.jrfcv.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *	分页类
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　pj　2016-1-18　<br/>
 * <p>
 *
 * @author pj
 *
 * @version 1.0, 2016-1-18
 *
 */
public class SimplePage implements Paginable {
	public static final int DEF_COUNT = 20;

	/**
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}

	public SimplePage() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public SimplePage(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * 调整页码，使不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	/**
	 * 获得页码
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 每页几条数据
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 总共几条数据
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 总共几页
	 */
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * 是否第一页
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * 下一页页码
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 上一页页码
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}
	
	/**
	* @Title: getPageArr
	* @Description: 获取分页数组
	* @author pj
	* @date 2017年1月10日 下午6:49:02
	* @return 
	*/
	public int[] getPageArr(){
		int[] array;
		int totlePage = getTotalPage(),currentPage = getPageNo();
		if(totlePage>1){
			List<Integer> num = new ArrayList<Integer>();
			for(int j=0;j<totlePage;j++){
				if(j>=(currentPage-3) && j<(currentPage+3)){
					num.add(j+1);
				}
			}
			if(num.size()>0){
				array = new int[num.size()];
				for(int i=0;i<num.size();i++){
					array[i]=num.get(i);
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		return array;
	}

	/**
	 * 当前页之后的页号数组
	 */
	public int[] getNextPageArr() {
		int[] array;
		int len = getTotalPage() - pageNo;
		int i = pageNo;
		if (pageNo == 1) {
			len -= 1;
			i = pageNo + 1;
		}
		if (len >= 4) {
			len = 4;
		}
		if(len>0){
			array = new int[len];
			for (int j = 0; j < len; i++, j++) {
				array[j] = i;
			}
		}else{
			array = null;
		}
		return array;
	}

	/**
	 * 当前页之前的页号数组
	 */
	public int[] getPrePageArr() {
		int[] array;
		int len = pageNo - 1;
		int i = pageNo - 1;
		if (len >= 4) {
			len = 3;
		}
		if(len>0){
			array = new int[len];
			for (int j = 1; j <= len; i--, j++) {
				if (i > 1) {
					array[len - j] = i;
				}
			}
		}else{
			array = null;
		}
		return array;
	}

	/**
	 * 是否后省略
	 */
	public boolean isAfterEllipsis() {
		if (getTotalPage() - pageNo > 6) {
			return true;
		}
		return false;
	}

	/**
	 * 是否前省略
	 */
	public boolean isBeforEllipsis() {
		if (pageNo - 1 > 4) {
			return true;
		}
		return false;
	}

	protected int totalCount = 0;
	protected int pageSize = 20;
	protected int pageNo = 1;

	/**
	 * if totalCount<0 then totalCount=0
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	/**
	 * if pageSize< 1 then pageSize=DEF_COUNT
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * if pageNo < 1 then pageNo=1
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
}
