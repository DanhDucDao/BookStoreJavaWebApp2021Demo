package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.BookDAO;
import dao.book.BookService;
import model.book.Book;

@WebServlet("/book")
public class BookServlet extends HttpServlet{

	private static final long serialVersionUID = -1020390743212802891L;
	private static final int PAGE_SIZE = 5;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookService();
		try {
			String pageIndexStr = request.getParameter("page");
			if(pageIndexStr == null || pageIndexStr.isEmpty())
				pageIndexStr = "0";
			int pageIndex = Integer.parseInt(pageIndexStr);
			List<Book> books = bookDAO.getAvailableBooks(pageIndex, PAGE_SIZE);
			int totalBook = bookDAO.countAllBook();
			
			request.setAttribute("books", books);
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("pageSize", PAGE_SIZE);
			request.setAttribute("totalBook", totalBook);
			
			Map<Integer, String> mapPage = preparedPagination(request);
			request.setAttribute("mapPages", mapPage);
			getServletContext().getRequestDispatcher("/customer/book.jsp").forward(request, response);
		} finally {
			bookDAO.endSession();
		}
	}
	
	private Map<Integer, String> preparedPagination(HttpServletRequest request) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		Enumeration<String> paramEnum = request.getParameterNames();
		List<String> paramNames = new ArrayList<>();
		while(paramEnum.hasMoreElements()) {
			paramNames.add(paramEnum.nextElement());
		}
		
		String base = request.getServletPath();
		int pageSize = (Integer) request.getAttribute("pageSize");
		int totalNum = (Integer) request.getAttribute("totalBook");
		int totalPage = totalNum / pageSize;
		
		String url0 = base +"?";
		for(int i = 0; i < paramNames.size(); i++) {
			if(!paramNames.get(i).equalsIgnoreCase("page")) {
				if(i != (paramNames.size() - 1)) {
					url0 += paramNames.get(i) + "=" + request.getParameter(paramNames.get(i)) + "&";
				} else {
					url0 += paramNames.get(i) + "=" + request.getParameter(paramNames.get(i));
				}
			}
		}
		map.put(0, url0);
		
		for(int i = 1; i <= totalPage ; i++) {
			String url = base + "?";
			for(String pr : paramNames) {
				if(pr.equalsIgnoreCase("page"))
					continue;
				url += pr + "=" + request.getParameter(pr) + "&";
			}
			url+= "page=" + i;
			map.put(i, url);
		}
		
		return map;
	}
	
}
