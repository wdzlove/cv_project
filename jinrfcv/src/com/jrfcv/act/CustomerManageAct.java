package com.jrfcv.act;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jrfcv.bean.Customer;
import com.jrfcv.bean.Position;
import com.jrfcv.service.CustomerMng;
import com.jrfcv.service.PositionMng;
import com.jrfcv.util.Pagination;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *客户管理控制器
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月14日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月14日
 *
 */
@Controller
@RequestMapping(value="cus/",produces = "text/json;charset=UTF-8")
public class CustomerManageAct {

	@Autowired
	private CustomerMng customerMng;
	@Autowired
	private PositionMng positionMng;
	/***
	* @Title: findList
	* @Description: 客户列表
	* @author wdz
	* @date 2018年3月14日 上午10:36:06
	* @param model
	* @param pageNo
	* @param pageSize
	* @param name
	* @return
	 */
	@RequestMapping(value="find_list.do")
	public String findList(ModelMap model,Integer pageNo,Integer pageSize,String name){
		Pagination<Customer> page = customerMng.findPage(pageNo,pageSize,name);
		model.put("page", page);
		model.put("name", name);
		return "customer/list";
	}
	/**
	* @Title: findJson
	* @Description: 获取企业的json格式全部数据
	* @author wdz
	* @date 2018年3月16日 上午11:27:32
	* @return
	 */
	@RequestMapping(value="layer_page.do")
	public String findJson(ModelMap model,Integer id){
		List<Customer> findList = customerMng.findList();
		model.put("findList", findList);
		if(id != null && id > 0){
			Position p = positionMng.getPosition(id);
			model.put("p", p);
		}
		return "/position/upade_page";
	}
	/**
	* @Title: save
	* @Description: 保存岗位
	* @author wdz
	* @date 2018年3月14日 上午10:39:08
	* @param cus
	* @return
	 */
	@RequestMapping(value="save.do")
	@ResponseBody
	public String save(Customer cus){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			//统计名称
			Integer count = customerMng.getCount(null,cus.getName());
			//统计编号
			Integer count2 = customerMng.countNumber(cus.getNumber(),null);
			if(count == 0){
				if(count2 == 0){
					customerMng.save(cus);
					code = "1";
				}else{
					code = "3";
				}
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: update
	* @Description: 修改
	* @author wdz
	* @date 2018年3月14日 上午10:40:49
	* @param cus
	* @return
	 */
	@RequestMapping(value="update.do")
	@ResponseBody
	public String update(Customer cus){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			//统计名称
			Integer count = customerMng.getCount(cus.getId(),cus.getName());
			//统计编号
			Integer count2 = customerMng.countNumber(cus.getNumber(),cus.getId());
			if(count == 0 ){
				if(count2 == 0){
					customerMng.update(cus);
					code = "1";
				}else{
					code = "3";
				}
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delete
	* @Description:删除
	* @author wdz
	* @date 2018年3月14日 上午10:41:12
	* @param id
	* @return
	 */
	@RequestMapping(value="delete.do")
	@ResponseBody
	public String delete(Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			Integer count = customerMng.countUse(id);
			if(count == 0){
				customerMng.delete(id);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
}
