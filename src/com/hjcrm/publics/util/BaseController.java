package com.hjcrm.publics.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.jpush.pool.PushInfoThreadPool;
import com.hjcrm.publics.websocket.entity.WebSocketNeedBean;
import com.hjcrm.resource.entity.Transferrecord;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IMenuService;

import sun.misc.BASE64Decoder;



/**
 * controller基类
 * @author likang
 * @date 2016-10-13 上午10:57:03
 */
public abstract class BaseController implements InitializingBean {
	
	protected static final Integer GLOBAL_PAGESIZE = 20;
	
	public static final String URL_SUFFIX = ".do";
	
	public void afterPropertiesSet() throws Exception {

	}
	
	/**
	 * 返回到指定普通视图，没有任何属性
	 * @param view
	 * @return
	 */
	public String view(String view) {
		return view;
	}
	/**
	 * 返回一个包含对象o的视图
	 * @param view
	 * @param model
	 * @return
	 */
	public ModelAndView view(String view, Map<String, Object> model) {
		return new ModelAndView(view, "o", model);
	}

	/**
	 * 用于datagrid或treegrid
	 * 
	 * @param data
	 * @return
	 */
	public String json(Object data) {
		if (data != null) {
			PageBean pageBean = pageBeanStore.get();
			return new StringBuffer().append("{\"total\":\"")
					.append(pageBean != null ? pageBean.getCountResult() : 0)
					.append("\",\"rows\":").append(JsonUtil.object2json(data))
					.append("}").toString();
		}
		return null;
	}
	
	/**
	 * 将页数返回前端
	 * @author likang	
	 * @param data
	 * @return
	 * 2016年10月13日10:59:22
	 */
	public String jsonToPage(Object data) {
		if (data != null) {
			PageBean pageBean = pageBeanStore.get();
			return new StringBuffer().append("{\"total\":\"")
					.append(pageBean != null ? pageBean.getCountResult() : 0)
					.append("\",\"currentPage\":\"")
					.append(pageBean != null ? pageBean.getCurrentPage() : 0)
					.append("\",\"rows\":").append(JsonUtil.object2json(data))
					.append("}").toString(); 
		}
		return null;
	}

	/**
	 * 用于Map形式的参数
	 * 
	 * @param data
	 * @return
	 */
	public String jsonForMap(Object data) {
		if (data != null) {
			return json(JsonUtil.object2json(data));
		}
		return json(null);
	}

	public String jsonString(Object data) {
		if (data != null) {
			String dataString = JackSonUtils.writeObj2Str(data);
			return dataString;
		}
		return json(null);
	}

	protected ThreadLocal<PageBean> pageBeanStore = new ThreadLocal<PageBean>();

	protected PageBean processPageBean(Integer pageSize, Integer currentPage) {
		PageBean pageBean = new PageBean();
		pageBeanStore.set(pageBean);
		if (pageSize == null && currentPage == null)
			return null;
		if (pageBean == null)
			pageBean = new PageBean();
		// 如果没有设置pageSize则使用全局页数。
		if (pageSize == null) {
			pageBean.setPageSize(GLOBAL_PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		if (currentPage != null) {
			pageBean.setCurrentPage(currentPage);
		}
		// pageBean.accountFirstResult();
		return pageBean;
	}
	
	
	/**
	 * 解决请求跨域问题
	 * @author likang	
	 * @param request
	 * @param response
	 * 2016年10月13日11:00:28
	 */
	public void ajaxSolve(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
	    response.setCharacterEncoding("utf-8");
	    response.addHeader("Access-Control-Allow-Origin","*");//'*'表示允许所有域名访问，可以设置为指定域名访问，多个域名中间用','隔开
	  //如果IE浏览器则设置头信息如下
	    if("IE".equals(this.getRequest().getParameter("type"))){
	    	 response.addHeader("XDomainRequestAllowed","1");
	    }
	}
	
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	
	protected PageBean processPageBean() {
		return processPageBean(null);
	}

	/**
	 * 对PageBean进行处理并返回.
	 * @param pageSize
	 *            分页的大小,可以传null,默认为20.
	 * @return 返回后可以传给Service层。
	 */
	protected PageBean processPageBean(Integer pageSize) {
		PageBean pageBean = pageBeanStore.get();
		if (pageBean == null)
			pageBean = new PageBean();
		// 如果没有设置pageSize则使用全局页数。
		if (pageSize == null) {
			pageBean.setPageSize(GLOBAL_PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		pageBean.switchOperation();
		return pageBean;
	}
	/**
	 * 重定向
	 * @param view
	 * @return
	 */
	public String redirect(String view) {
		return parseRedirectView(view);
	}
	
	private String parseRedirectView(String view) {
		if (!StringUtils.hasText(view))
			throw new NullPointerException("view page can not be not null");
		String paramString = "";
		String tempView = "";
		if (view.indexOf("?") != -1) {
			tempView = view.substring(0, view.indexOf("?"));
			paramString = view.substring(view.indexOf("?"));
		} else
			tempView = view;
		if (!tempView.startsWith("/") && !tempView.endsWith(URL_SUFFIX))
			tempView = "/" + tempView + URL_SUFFIX;
		else if (tempView.startsWith("/") && !tempView.endsWith(URL_SUFFIX))
			tempView = tempView + URL_SUFFIX;
		else if (!tempView.startsWith("/") && tempView.endsWith(URL_SUFFIX))
			tempView = "/" + tempView;
		return "redirect:" + tempView + paramString;
	}
	
	public String httpRequestMsg(Integer status,Object data) {
		if (data != null) {
			RequestMsg msg = new RequestMsg();
			msg.setStatus(status);
			msg.setContent(data);
			String dataString = JackSonUtils.writeObj2Str(msg);
			return dataString;
		}
		return json(null);
	}
	
	/**
	 *  base64字符串转化成图片
	 * @author likang	
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 * 2016年5月25日 下午3:34:46
	 */
	public static boolean GenerateImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 组装转移记录
	 * @param resourceId
	 * @param phone
	 * @param tel
	 * @param sourceId
	 * @param sourceName
	 * @param belongId
	 * @param belongName
	 * @param state
	 * @param source
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-21 下午2:32:47
	 */
	public Transferrecord groupTransferRecord(String resourceLevelBefore,Long resourceId,String phone,String tel,Long sourceId,String sourceName,Long belongId,String belongName,Integer state,Integer source,Long deptid){
		Transferrecord tr = new Transferrecord();
		tr.setResourceId(resourceId);
		tr.setPhone(phone);
		tr.setTel(tel);
		tr.setSourceId(sourceId);
		tr.setSourceName(sourceName);
		tr.setBelongId(belongId);
		tr.setBelongName(belongName);
		tr.setCreate_time(new Timestamp(System.currentTimeMillis()));
		tr.setState(state);
		tr.setSource(source);
		tr.setDeptid(deptid);
		tr.setResourceLevelBefore(resourceLevelBefore);
		return tr;
	}
	
	/**
	 * 推送消息
	 * @param objType
	 * @param objId
	 * @param dataIds
	 * @param dataType
	 * @param content
	 * @author likang 
	 * @date 2016-12-1 上午10:39:47
	 */
	public void sendmessage(Integer objType,String objId,String deptid,String dataIds,String dataType,String content) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("objType", objType);// 对象类型
		paramMap.put("objId", objId);//对象用户ID
		paramMap.put("deptid", deptid);//用户部门ID
		paramMap.put("dataId", dataIds);//数据ID
		paramMap.put("dataType", dataType);//数据类型
		paramMap.put("content",content);//提示内容
		PushInfoThreadPool.executePushInfoTask(paramMap);
	}
	   
	@Autowired
	private IMenuService menuService;
	
	
	public synchronized boolean isSholdOpenMenu(Long roleid,String menuurl){
		if (roleid != null && menuurl != null && !"".equals(menuurl.trim())) {
			List<Menu> listmenu = menuService.queryMenuByRoleid(roleid);
			if (listmenu != null && listmenu.size() > 0) {
				for (int i = 0; i < listmenu.size(); i++) {
					String listmenuurl = listmenu.get(i).getMenuurl();
					if (menuurl.equals(listmenuurl)) {
						return true;//可以打开
					}
				}
				return false;//用户无权限操作并打开，如果需要此权限，请联系运营部
			} 
		}
		return false;
	}
	
	
}
