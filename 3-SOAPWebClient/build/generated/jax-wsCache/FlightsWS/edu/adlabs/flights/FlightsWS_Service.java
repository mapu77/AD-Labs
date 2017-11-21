
package edu.adlabs.flights;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "FlightsWS", targetNamespace = "http://flights.adlabs.edu/", wsdlLocation = "http://localhost:8080/FlightsWS/FlightsWS?wsdl")
public class FlightsWS_Service
    extends Service
{

    private final static URL FLIGHTSWS_WSDL_LOCATION;
    private final static WebServiceException FLIGHTSWS_EXCEPTION;
    private final static QName FLIGHTSWS_QNAME = new QName("http://flights.adlabs.edu/", "FlightsWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/FlightsWS/FlightsWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FLIGHTSWS_WSDL_LOCATION = url;
        FLIGHTSWS_EXCEPTION = e;
    }

    public FlightsWS_Service() {
        super(__getWsdlLocation(), FLIGHTSWS_QNAME);
    }

    public FlightsWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), FLIGHTSWS_QNAME, features);
    }

    public FlightsWS_Service(URL wsdlLocation) {
        super(wsdlLocation, FLIGHTSWS_QNAME);
    }

    public FlightsWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FLIGHTSWS_QNAME, features);
    }

    public FlightsWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FlightsWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FlightsWS
     */
    @WebEndpoint(name = "FlightsWSPort")
    public FlightsWS getFlightsWSPort() {
        return super.getPort(new QName("http://flights.adlabs.edu/", "FlightsWSPort"), FlightsWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FlightsWS
     */
    @WebEndpoint(name = "FlightsWSPort")
    public FlightsWS getFlightsWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://flights.adlabs.edu/", "FlightsWSPort"), FlightsWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FLIGHTSWS_EXCEPTION!= null) {
            throw FLIGHTSWS_EXCEPTION;
        }
        return FLIGHTSWS_WSDL_LOCATION;
    }

}
