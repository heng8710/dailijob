package webpage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;


/**
 * @author heng
 *
 */
public class ParseWebPage {

	public static PageDomain parse( ) {
		return parse(url);
	}
	
	public static PageDomain parse(final String url) {
		return parse0(url);
	}
	
	static final String url = "http://proxy.goubanjia.com/"; 
	
	static PageDomain parse0(final String url ){
		final HtmlDivision root = tableDiv(url);
		final String title = title(root);
		final List<String> columnNames = columnNames(root);
		final List<List<Object>> rows = rows(root);
		return new PageDomain(title, columnNames, rows);
	}
	
	/**
	 * 获取页面中包含了代理信息的那一块。
	 * @return
	 */
	static HtmlDivision tableDiv(final String pageUrl){
		final WebClient c = new WebClient(BrowserVersion.CHROME);
	    final HtmlPage p;
		try {
			p = c.getPage(pageUrl);
		} catch (FailingHttpStatusCodeException | IOException e) {
			throw new IllegalStateException("访问代理页面失败，可能是页面有问题了", e);
		}
	    final List<HtmlDivision> lx = (List<HtmlDivision>)p.getByXPath("//div[@class='entry entry-content'][2]");
    	if(lx == null || lx.size() <= 0){
    		throw new IllegalStateException("找不到页面中的代理信息了：【//div[@class='entry entry-content'][2]】");
    	}else{
    		//从这里开始解析数据
    		final HtmlDivision root = lx.get(0);
    		return root;
    	}
	}
	
	/**
	 * 获取标题部分，比如：【最新 20 个可用免费高速HTTP代理IP（2015-07-30 21:18:22 更新）】
	 * @param root
	 * @return
	 */
	static String title(final HtmlDivision root){
		//标题：有时会有信息
		final String title = root.getChildElements().iterator().next().asText();
		return title;
	}
	
	/**
	 * 代理信息部分的列名，比如：【IP	PORT	匿名度	类型	国家	省市	运营商	响应速度	最后验证时间	存活率】
	 * @param root
	 * @return
	 */
	static List<String> columnNames(final HtmlDivision root){
		//IP	PORT	匿名度	类型	国家	省市	运营商	响应速度	最后验证时间	存活率
    	//用于质量校验用的？？
    	final List<String> names = Lists.newArrayList();
    	for(final DomElement headItem: (List<DomElement>)root.getByXPath("//table/thead/tr[1]/th")){
    		names.add(headItem.asText());
    	}
    	return names;
	}
	
	/**
	 * 代理信息部分的所有行，比如：【[[111.1.36.167	80	透明	http	中国	浙江省  杭州市	移动	0.038 秒	7分钟前	新入库],[]]】
	 * @param root
	 */
	static List<List<Object>> rows(final HtmlDivision root) {
    	final List<List<Object>> resultList = Lists.newArrayList();
    	//每一行
    	for(final DomElement trDom: (List<DomElement>)root.getByXPath("//table/tbody/tr")){
    		final List<Object> row = Lists.newArrayList();
    		final Iterator<HtmlElement> it = trDom.getElementsByTagName("td").iterator();
    		
    		//数据的格式不对
    		if(!it.hasNext()){
    			break;
    		}
    		
//    		int nameIndex = 0;
//    		String s = "";
    		
    		//ip
    		final HtmlElement ipElem = it.next();
    		final String ip = CharMatcher.WHITESPACE.removeFrom(ipElem.asText());
//	    		s = String.format("%s: %s",names.get(nameIndex++) , ip);
//    			System.out.println(s);
    		row.add(ip);
    		
			
			//port
			final HtmlElement portElem = it.next();
			final String port = portElem.asText();
//    			s = String.format("%s: %s",names.get(nameIndex++) , port);
//    			System.out.println(s);
			row.add(port);
			
			
//    			map.put(ip, port);
			
			//匿名度
			final HtmlElement 匿名度 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 匿名度.asText());
//    			System.out.println(s);
			row.add(匿名度.asText());
			
			//类型
			final HtmlElement 类型 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 类型.asText());
//    			System.out.println(s);
			row.add(类型.asText());
			
			//国家
			final HtmlElement 国家 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 国家.asText());
//    			System.out.println(s);
			row.add(国家.asText());
			
			
			//省市
			final HtmlElement 省市 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 省市.asText());
//    			System.out.println(s);
			row.add(省市.asText());
			
			
			//运营商
			final HtmlElement 运营商 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 运营商.asText());
//    			System.out.println(s);
			row.add(运营商.asText());
			
			
			//响应速度
			final HtmlElement 响应速度 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 响应速度.asText());
//    			System.out.println(s);
			row.add(响应速度.asText());
			
			
			//最后验证时间
			final HtmlElement 最后验证时间 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 最后验证时间.asText());
//    			System.out.println(s);
			row.add(最后验证时间.asText());
			
			
			
			//存活率
			final HtmlElement 存活率 = it.next();
//    			s = String.format("%s: %s",names.get(nameIndex++) , 存活率.asText());
//    			System.out.println(s);
			row.add(存活率.asText());
			
			resultList.add(row);
    	}
    	return resultList;
	}
}
