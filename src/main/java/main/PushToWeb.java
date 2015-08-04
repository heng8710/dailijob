package main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.sun.xml.internal.txw2.IllegalSignatureException;

import dao.DailiDao;
import domain.DailiDomain;

public class PushToWeb {

	private static final Logger logger = LoggerFactory.getLogger(PushToWeb.class); 
	
	public static void main(final String[] args) {
		final Optional<DailiDomain> op = DailiDao.getLastOne();
		if(!op.isPresent()){
			throw new IllegalStateException("没有数据，找不到最后一条daili信息。");
		}
		final DailiDomain dd = op.get();
		if(args == null || args.length < 1){
			throw new IllegalStateException("必须要有一个执行参数[所推送过去的dailiweb的http地址]");
		}
		final String targetUrl = args[0];
		try {
			post(targetUrl, dd);
		} catch (Exception e) {
			logger.error("推送代理信息失败", e);
			throw new IllegalSignatureException("推送代理信息失败", e);
		}
	}
	
	static void post(final String targetUrl, final DailiDomain dd){
		final Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://localhost:9998").path("resource");
//		Entity e = new Entity();
		final Response resp = client.target(targetUrl)
//	            .register(FilterForExampleCom.class)
//	            .path("")
//	            .queryParam("greeting", "Hi World!")
	            .request(MediaType.APPLICATION_JSON + " ;charset=utf-8")
//	            .header("some-header", "true")
	            .post(Entity.json(dd));
		
		if(resp.getStatus() >= 200 && resp.getStatus() < 300){
			//正确
			return ;
		}else{
			//异常
			throw new IllegalStateException(String.format("访问dailiweb接口（post）有异常，请求数据是：[%s], 返回的结果是：status=[%s], body=[%s]", dd, resp.getStatus(), resp.getEntity()));
		}
	}

}
