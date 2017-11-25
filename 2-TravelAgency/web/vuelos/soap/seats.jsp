<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-body">
        <form method="GET" action="${pageContext.request.contextPath}/flightManagementServlet">
            <h4 class="card-title">Check available seats</h4>
            <p class="card-text">Enter the flight identifier and the date of your flight</p>
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
                <small id="flightDateHelp" class="form-text text-muted">Format: aaaammdd</small>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Check</button>
                <c:set var="emptySeats" value="${pageContext.getSession().getAttribute('emptySeats')}"></c:set>
                <c:if test="${not empty emptySeats}">
                    <c:choose>
                        <c:when test="${emptySeats < 0}">
                            <p class="card-text text-right" style="color: red; margin-top: -1.875rem">There's no flight with such id in such date</p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text text-right" style="margin-top: -1.875rem"><strong>Empty seats: </strong>${emptySeats}</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <% pageContext.getSession().removeAttribute("emptySeats");%>
            </div>
        </form>
    </div>
</div>