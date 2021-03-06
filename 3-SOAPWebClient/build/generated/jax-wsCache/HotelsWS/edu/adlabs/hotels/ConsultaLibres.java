
package edu.adlabs.hotels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consulta_libres complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consulta_libres"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id_hotel" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consulta_libres", propOrder = {
    "idHotel",
    "fecha"
})
public class ConsultaLibres {

    @XmlElement(name = "id_hotel")
    protected int idHotel;
    protected int fecha;

    /**
     * Obtiene el valor de la propiedad idHotel.
     * 
     */
    public int getIdHotel() {
        return idHotel;
    }

    /**
     * Define el valor de la propiedad idHotel.
     * 
     */
    public void setIdHotel(int value) {
        this.idHotel = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     */
    public int getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     */
    public void setFecha(int value) {
        this.fecha = value;
    }

}
