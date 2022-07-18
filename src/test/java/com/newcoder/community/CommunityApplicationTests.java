package com.newcoder.community;

import com.newcoder.community.dao.MasterDao;
import com.newcoder.community.service.MasterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
// @Runwith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		System.out.println(applicationContext);

		MasterDao masterDao = applicationContext.getBean(MasterDao.class);
		System.out.println(masterDao.select());

		masterDao = (MasterDao) applicationContext.getBean("masterHibernates");
		System.out.println(masterDao.select());
	}

	@Test
	public void testBean(){
		MasterService masterService = applicationContext.getBean(MasterService.class);
		System.out.println(masterService);
	}

	@Test
	public void testDate(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("masterHibernates")
	private MasterDao masterDao;
	@Autowired
	private MasterService masterService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(masterDao.select());
		System.out.println(masterService);
		System.out.println(simpleDateFormat);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
