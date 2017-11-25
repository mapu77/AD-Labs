<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-body">
        <form method="POST" action="${pageContext.request.contextPath}/flightManagementServletRest">
            <h4 class="card-title">Book a seat</h4>
            <p class="card-text">Enter a flight identifier and a date for booking a seat</p>
            <div class="form-group">
                <label for="flightId">Flight ID</label>
                <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-plane" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="flightId" aria-describedby="flightId" placeholder="Enter flight Id" min="1" required>
                </div>
                <small id="flightIdHelp" class="form-text text-muted">i.e. 09111714</small>
            </div>
            <div class="form-group">
                <label for="flightDate">Date</label>
                <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-calendar" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="flightDate" placeholder="Enter date" min="1" required>
                </div>
                <small id="flightIdHelp" class="form-text text-muted">Format: aaaammdd</small>
            </div>
            <div>
                <button type="submit" id="book" class="btn btn-primary">Book</button>
                <c:set var="occupiedSeats" value="${pageContext.getSession().getAttribute('occupiedSeatsREST')}"></c:set>
                <c:if test="${not empty occupiedSeats}">
                    <br/>
                    <c:choose>
                        <c:when test="${occupiedSeats < 0}">
                            <p class="card-text text-right" style="color: red; margin-top: -1.875rem">There might be no empty seats in this flight<br/>for this date</p>
                            </c:when>
                            <c:otherwise>
                            <p class="card-text text-right" style="margin-top: -1.875rem"><strong>Occupied seats: </strong>${occupiedSeats}</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <% pageContext.getSession().removeAttribute("occupiedSeatsREST");%>
            </div>
        </form>
    </div>
</div>