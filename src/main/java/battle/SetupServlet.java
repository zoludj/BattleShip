package battle;

import model.CellState;
import model.Game;
import model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "SetupServlet", urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selected = request.getParameterValues("cells");

        var player = (Player) request.getSession().getAttribute("player");

        player.getOwnField().clear();

        if (selected != null) {
            System.out.println(Arrays.toString(selected));
            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

        if (player.getOwnField().isValid()) {
            doGet(request, response);
        } else {
            request.setAttribute("message", "wrong placement!");
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");
        if (!player.getOwnField().isValid()) {
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        } else if (game.getPlayer1().isReadyToPlay() && game.getPlayer2().isReadyToPlay()) {
            response.sendRedirect("/game");
        } else {
            request.getRequestDispatcher("/WEB-INF/waitSetup.jsp").include(request, response);
        }
    }

}