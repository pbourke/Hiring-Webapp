package com.pb.servertest;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public void hello(final HttpServletResponse response) throws IOException {
		final List<TestBean> testBeans = sessionFactory.getCurrentSession().createCriteria(TestBean.class).list();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body><table><tr><th>ID</th><th>Name</th><th>Date</th><th>Gross</th></tr>");
		for ( TestBean tb : testBeans ) {
			sb.append( String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%.2f</td></tr>",tb.getId(), tb.getName(), tb.getEventDate(), tb.getGross()) );
		}
		sb.append("</table></body></html>");
		response.getWriter().write(sb.toString());
	}
}
