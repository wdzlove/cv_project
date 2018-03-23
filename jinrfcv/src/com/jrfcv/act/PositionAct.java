package com.jrfcv.act;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jrfcv.bean.Position;
import com.jrfcv.service.PositionMng;
import com.jrfcv.util.Pagination;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *职位管理
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
@RequestMapping(value="post/",produces = "text/json;charset=UTF-8")
public class PositionAct {

	@Autowired
	private PositionMng positionMng;
	/**
	* @Title: findPage
	* @Description: 分页数据
	* @author wdz
	* @date 2018年3月14日 下午4:14:39
	* @param model
	* @param pageNo
	* @param pageSize
	* @param name
	* @return
	 */
	@RequestMapping(value="find_list.do")
	public String findPage(ModelMap model,Integer pageNo,Integer pageSize,String name){
		Pagination<Position> page = positionMng.findPage(pageNo,pageSize,name);
		model.put("page", page);
		model.put("name", name);
		return "position/list";
	}
	/**
	* @Title: findPost
	* @Description: 获取企业对应的岗位
	* @author wdz
	* @date 2018年3月16日 下午1:33:24
	* @param model
	* @param cusId
	* @return
	 */
	@RequestMapping(value="find_post.do")
	@ResponseBody
	public String findPost(ModelMap model,Integer cusId){
		JSONObject data = new JSONObject();
		List<Position> list = positionMng.findListByCusId(cusId);
		JSONArray array = new JSONArray();
		if(list != null && list.size() > 0){
			for (Position position : list) {
				JSONObject obj = new JSONObject();
				obj.put("pid", position.getId());
				obj.put("name", position.getName());
				obj.put("numbers", position.getNumber());
				obj.put("duty", position.getDuty());
				array.add(obj);
			}
		}
		data.put("datas", array);
		return data.toString();
	}
	/**
	* @Title: save
	* @Description: 保存
	* @author wdz
	* @date 2018年3月14日 下午2:47:31
	* @param p
	* @return
	 */
	@RequestMapping(value="save.do")
	@ResponseBody
	public String save(Position p){
		JSONObject obj = new JSONObject();
		String code = "0";
		Integer count = positionMng.countName(null,p.getName(),p.getCustomerId());
		Integer count2 = positionMng.countNumber(null,p.getNumber());
		if(count == 0 && count2 == 0){
			positionMng.save(p);
			code = "1";
		}else{
			if(count > 0){
				code = "2";
			}else if(count2 > 0){
				code = "3";
			}
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: update
	* @Description: 修改
	* @author wdz
	* @date 2018年3月14日 下午2:47:21
	* @param p
	* @return
	 */
	@RequestMapping(value="update.do")
	@ResponseBody
	public String update(Position p){
		JSONObject obj = new JSONObject();
		String code = "0";
		Integer count = positionMng.countName(p.getId(),p.getName(),p.getCustomerId());
		Integer count2 = positionMng.countNumber(p.getId(), p.getNumber());
		if(count == 0 && count2 == 0){
			positionMng.update(p);
			code = "1";
		}else{
			if(count != 0){
				code = "2";
			}else if(count2 != 0){
				code = "3";
			}
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: delete
	* @Description:删除
	* @author wdz
	* @date 2018年3月14日 下午2:47:09
	* @param id
	* @return
	 */
	@RequestMapping(value="delete.do")
	@ResponseBody
	public String delete(Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		Integer count = positionMng.countUse(id);
		if(count == 0){
			positionMng.delete(id);
			code = "1";
		}else{
			code = "2";
		}
		obj.put("code", code);
		return obj.toString();
	}
}
