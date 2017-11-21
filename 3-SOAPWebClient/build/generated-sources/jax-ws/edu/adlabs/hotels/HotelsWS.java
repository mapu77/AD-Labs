
package edu.adlabs.hotels;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HotelsWS", targetNamespace = "http://hotels.adlabs.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HotelsWS {


    /**
     * 
     * @param fecha
     * @param idHotel
     * @return
     *     returns int
     */
    @WebMethod(operationName = "consulta_libres")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consulta_libres", targetNamespace = "http://hotels.adlabs.edu/", className = "edu.adlabs.hotels.ConsultaLibres")
    @ResponseWrapper(localName = "consulta_libresResponse", targetNamespace = "http://hotels.adlabs.edu/", className = "edu.adlabs.hotels.ConsultaLibresResponse")
    @Action(input = "http://hotels.adlabs.edu/HotelsWS/consulta_libresRequest", output = "http://hotels.adlabs.edu/HotelsWS/consulta_libresResponse")
    public int consultaLibres(
        @WebParam(name = "id_hotel", targetNamespace = "")
        int idHotel,
        @WebParam(name = "fecha", targetNamespace = "")
        int fecha);

    /**
     * 
     * @param fecha
     * @param idHotel
     * @return
     *     returns int
     */
    @WebMethod(operationName = "reserva_habitacion")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "reserva_habitacion", targetNamespace = "http://hotels.adlabs.edu/", className = "edu.adlabs.hotels.ReservaHabitacion")
    @ResponseWrapper(localName = "reserva_habitacionResponse", targetNamespace = "http://hotels.adlabs.edu/", className = "edu.adlabs.hotels.ReservaHabitacionResponse")
    @Action(input = "http://hotels.adlabs.edu/HotelsWS/reserva_habitacionRequest", output = "http://hotels.adlabs.edu/HotelsWS/reserva_habitacionResponse")
    public int reservaHabitacion(
        @WebParam(name = "id_hotel", targetNamespace = "")
        int idHotel,
        @WebParam(name = "fecha", targetNamespace = "")
        int fecha);

}
