package servlets.product.command;


import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import servlets.product.domain.ProductsDTO;
import servlets.product.persistence.ProductsDAO;
import servlets.product.service.ListService;
import servlets.product.service.ViewService;

public class View implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">View.process ?");
		int tag = Integer.parseInt(request.getParameter("tag"));	
		ViewService viewService = ViewService.getInstance();		
//		ProductsDTO dto = viewService.view(tag);
		List<ProductsDTO> list = viewService.view(tag);
		// 1. 포워딩 전에 데이터 저장
//		request.setAttribute("dto", dto);
		request.setAttribute("list", list);

		return "/WEB-INF/views/product/View.jsp";				
		
	}

}
