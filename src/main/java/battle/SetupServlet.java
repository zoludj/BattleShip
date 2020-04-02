package battle;

import model.CellState;
import model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "StartUpServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterValues("cells");
        String[] selected = request.getParameterValues("cells");

        var player = (Player) request.getSession().getAttribute("player");
        player.getOwnField().clear();


        if (selected == null) {
            System.out.println(Arrays.toString(selected));
            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }
        if (selected == null || selected.length != 20) {
            request.setAttribute("message", "wrong message");
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
    }
}
