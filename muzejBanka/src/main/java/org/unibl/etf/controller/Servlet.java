package org.unibl.etf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.beans.KarticaBean;
import org.unibl.etf.beans.TransakcijaBean;
import org.unibl.etf.dto.Kartica;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adresa="WEB-INF/pages/Login.jsp";
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		session.setAttribute("obavjestenje", "");
		if(action!=null && action.equals("login")) {
			String ime=request.getParameter("ime");
			String prezime=request.getParameter("prezime");
			String broj=request.getParameter("broj");
			String pin=request.getParameter("pin");
			KarticaBean kartica=new KarticaBean();
			
			if(kartica.getkartica(broj) && kartica.provjera(ime, prezime, pin)) {
				adresa="WEB-INF/pages/MainPage.jsp";
				session.setAttribute("kartica", kartica);
				TransakcijaBean transakcija=new TransakcijaBean();
				
				session.setAttribute("transakcija", transakcija);
			}else {
				session.setAttribute("obavjestenje", "Niste unijeli dobre podatke");
			}
		}else if(action!=null && action.equals("odjava")) {
			session.setAttribute("transakcija", null);
			session.setAttribute("kartica", null);
		}else if(action!=null && action.equals("placanje")) {
			String placanje=request.getParameter("flexRadioDefault");
			KarticaBean kartica=(KarticaBean) session.getAttribute("kartica");
			int i=1;
			if(placanje.equals("da")) {
				i=1;
				kartica.getKartica().setOmogucenoPlacanje(true);
			}else if(placanje.equals("ne")) {
				i=0;
				kartica.getKartica().setOmogucenoPlacanje(false);
			}
			
			
			kartica.update(i);
			adresa="WEB-INF/pages/MainPage.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(adresa);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
