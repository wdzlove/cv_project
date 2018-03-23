package com.jrfcv.util;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *	分页接口
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
public interface Paginable {

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public int getTotalPage();

	/**
	 * 每页记录数
	 * 
	 * @return
	 */
	public int getPageSize();

	/**
	 * 当前页号
	 * 
	 * @return
	 */
	public int getPageNo();

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage();

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage();

	/**
	 * 返回下页的页号
	 */
	public int getNextPage();

	/**
	 * 返回上页的页号
	 */
	public int getPrePage();

	/**
	 * 当前页之前的页号数组
	 * 
	 * @return
	 */
	public int[] getPrePageArr();

	/**
	 * 当前页之后的页号数组
	 * 
	 * @return
	 */
	public int[] getNextPageArr();

	/**
	 * 是否前省略
	 * 
	 * @return
	 */
	public boolean isBeforEllipsis();

	/**
	 * 是否后省略
	 * 
	 * @return
	 */
	public boolean isAfterEllipsis();
}
