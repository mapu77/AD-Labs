/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adlabs.restflight;

import edu.adlabs.database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    /**
     * Dados un identificador de vuelo y una fecha, retorna el número de plazas que están libres.
     * 
     * Retorna -1 si no hay información para el avión y/o la fecha en cuestión
     *
     * @param id_vuelo representa el identificador de vuelo
     * @param fecha representa la fecha en la que se realiza el vuelo en formato aaaammdd
     * @return el número de plazas libres en el vuelo
     */
     @Path("consulta")
     @GET
     @Consumes("application/x-www-form-urlencoded")
     @Produces("text/html")
    public String consulta_libres(@QueryParam("id_vuelo") final int id_vuelo, @QueryParam("fecha") final int fecha) {
        Statement statement = MyDB.getStatement();
        String sql = "SELECT num_plazas_max - num_plazas_ocupadas as plazas_libres FROM vuelo_fecha WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
        int plazasLibres = -1;
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            plazasLibres = rs.getInt("plazas_libres");
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(plazasLibres);
    }
    
   
    
    /**
     * Dados un identificador de vuelo y una fecha, reserva una plaza si quedan plazas libres (incrementa el número de
     * plazas ocupadas en un vuelo en una fecha.
     *
     * Si es posible realizar la reserva, esta operación retorna el número de plazas ocupadas que hay en el vuelo.
     *
     * Si no es posible realizar la reserva, esta operación retorna -1.
     *
     * @param id_vuelo representa el identificador de vuelo
     * @param fecha representa la fecha en la que se realiza el vuelo en formato aaaammdd
     * @return el número de plazas ocupadas en el vuelo
     */
    
    @Path("reserva")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String reserva_plaza(@FormParam("id_vuelo") final int id_vuelo, @FormParam("fecha") final int fecha) {
        String plazasLibres = this.consulta_libres(id_vuelo, fecha);
        int plazasOcupadas = -1;
        if (Integer.parseInt(plazasLibres) > 0) {
            try {
                Statement statement = MyDB.getStatement();
                String sql = "UPDATE vuelo_fecha SET num_plazas_ocupadas = num_plazas_ocupadas + 1 WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
                statement.execute(sql);
                sql = "SELECT num_plazas_ocupadas FROM vuelo_fecha WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                plazasOcupadas = rs.getInt("num_plazas_ocupadas");
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return String.valueOf(plazasOcupadas);
    }
    

    /**
     * Retrieves representation of an instance of
     * edu.adlabs.restflight.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html><head/><body><h1>Hello World!</h1></body></html>";
    }

    
     
   
}
