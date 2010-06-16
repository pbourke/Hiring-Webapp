package com.pb.servertest;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.servertest.model.TestBean;

@Controller
public class TestController {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(final SessionFactory sf) {
		sessionFactory = sf;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public String hello(final ModelMap model) throws IOException {
		final List<TestBean> testBeans = sessionFactory.getCurrentSession().createCriteria(TestBean.class).list();
		model.addAttribute("testBeans", testBeans);
		return "test";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/form")
	public String form(final TestBean testBean) {
		testBean.setName("new Name");
		return "form";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/form")
	public String postForm(final TestBean testBean) {
		return "form_submitted";
	}
}
