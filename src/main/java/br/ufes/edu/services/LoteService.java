package br.ufes.edu.services;

import br.ufes.edu.dao.LoteDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Lote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoteService
{
    LoteDao loteDao;

    public LoteService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            loteDao = new LoteDao(conn);
        } catch (SQLException | PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public Response registrarLote(Lote lote) throws Exception {

        Lote loteExistente = loteDao.readId(lote.getCodigoLote());
        try {
            if (loteExistente != null) {
                throw new Exception("Lote já cadastrada!");
            }
            loteDao.save(lote);
            return Response.seeOther(URI.create("http://localhost:8080/redirect?forward=areaAdmin.jsp")).build();

        } catch (Exception e) {
            return Response.seeOther(URI.create("http://localhost:8080/redirect?forward=errorRegistroLote.jsp")).build();
        }
    }

    public List<Lote> listarLotes() throws Exception {
        return loteDao.readAll();
    }

    public List<String> listarLotesString() throws Exception {
        List<Lote> lotes = loteDao.readAll();
        List<String> lotesString = new ArrayList<>();
        for (Lote lote : lotes) {
            lotesString.add(lote.getCodigoLote() + " - " + lote.getNomeFabricante());
        }
        return lotesString;
    }

    public void registrarLoteForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registrarLote.jsp").forward(request, response);
    }

}
