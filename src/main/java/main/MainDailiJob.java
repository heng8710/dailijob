package main;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webpage.PageDomain;
import webpage.ParseWebPage;
import dao.DailiDao;

public class MainDailiJob {

	private static final Logger logger = LoggerFactory.getLogger(MainDailiJob.class); 
	
	public static void main(String[] args) {
		do{
			doOnce();
			try {
				TimeUnit.MINUTES.sleep(new Random().nextInt(22));
			} catch (Exception e) {
				logger.info("stopping parse daili web page");
				break;
			}
		}while(true);
		
	}
	
	
	static void doOnce(){
		try {
			final PageDomain pd = ParseWebPage.parse();
			DailiDao.insert(pd);
		} catch (Exception e) {
			logger.error("error when trying to parse daili web page", e);
		}
	}

}
