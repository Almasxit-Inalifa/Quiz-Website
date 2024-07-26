package quiz_web.Servlets;

import quiz_web.Database.AdminStatisticDbManager;
import quiz_web.Database.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/adminStatistics")
public class adminStatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DbConnection connection = (DbConnection) getServletContext().getAttribute("database_connection");
        AdminStatisticDbManager statisticDbBase = new AdminStatisticDbManager(connection.getConnection(), false);

        String fromDateStr = httpServletRequest.getParameter("fromDate");
        String toDateStr = httpServletRequest.getParameter("toDate");

        Timestamp fromDate = null;
        Timestamp toDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (fromDateStr != null && !fromDateStr.isEmpty()) {
                fromDate = new Timestamp(dateFormat.parse(fromDateStr).getTime());
            }
            if (toDateStr != null && !toDateStr.isEmpty()) {
                toDate = new Timestamp(dateFormat.parse(toDateStr).getTime());
            }


            httpServletRequest.setAttribute("fromDate", fromDate);
            httpServletRequest.setAttribute("toDate", toDate);

            httpServletRequest.getRequestDispatcher("/homePage/adminStatistic.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (ParseException e) {
            throw new ServletException("Error processing date range and fetching statistics", e);
        }
    }
}
