package battle;

import model.Game;
import model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = "/game")
public class GameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var addr = request.getParameter("cell");
        var game = (Game) request.getSession().getAttribute("game");

        var player = (Player) request.getSession().getAttribute("player");
        if (game.isMyTurn(player)) {
            game.fire(addr);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");

        if (game.isMyTurn(player)) {
            request.getRequestDispatcher("/WEB-INF/fire.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/waitFire.jsp").include(request, response);
        }
    }
}
