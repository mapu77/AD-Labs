<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="flights" class="col col-lg-6 col-md-12 col-sm-12 col-xs-12">
    <div class="card">
        <div class="card-header h2">Flights</div>
        <div class="card-body">
            <%@include file="/flights/seats.jsp"%>
            <br/>
            <%@include file="/flights/booking.jsp"%>
        </div>
    </div>
</div>