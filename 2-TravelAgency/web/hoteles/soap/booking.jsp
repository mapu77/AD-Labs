<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-body">
        <form method="POST" action="hotelManagementServlet">
            <h4 class="card-title">Book seat</h4>
            <p class="card-text">Enter a hotel identifier and a date for booking a room</p>
            <div class="form-group">
                <label for="hotelId">Hotel ID</label>
                <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-bed" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="hotelId" aria-describedby="hotelId" placeholder="Enter hotel Id" min="1" required>
                </div>
                <small id="hotelIdHelp" class="form-text text-muted">i.e. 09111714</small>
            </div>
            <div class="form-group">
                <label for="hotelDate">Date</label>
                <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-calendar" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="hotelDate" placeholder="Enter date" min="1" required>
                </div>
                <small id="hotelIdHelp" class="form-text text-muted">Format: aaaammdd</small>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Book</button>
                <c:set var="occupiedRooms" value="${pageContext.getSession().getAttribute('occupiedRooms')}"></c:set>
                <c:if test="${not empty occupiedRooms}">
                    <c:choose>
                        <c:when test="${occupiedRooms < 0}">
                            <p class="card-text text-right" style="color: red; margin-top: -1.875rem">There might be no empty rooms in this hotel<br/>for this date</p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text text-right" style="margin-top: -1.875rem"><strong>Occupied rooms: </strong>${occupiedRooms}</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <% pageContext.getSession().removeAttribute("occupiedRooms");%>
            </div>
        </form>
    </div>
</div>