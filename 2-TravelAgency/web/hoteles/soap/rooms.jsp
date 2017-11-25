<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-body">
        <form method="GET" action="hotelManagementServlet">
            <h4 class="card-title">Check available rooms</h4>
            <p class="card-text">Enter the hotel identifier and a date</p>
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
                <small id="hotelDateHelp" class="form-text text-muted">Format: aaaammdd</small>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Check</button>
                <c:set var="emptyRooms" value="${pageContext.getSession().getAttribute('emptyRooms')}"></c:set>
                <c:if test="${not empty emptyRooms}">
                    <c:choose>
                        <c:when test="${emptyRooms < 0}">
                            <p class="card-text text-right" style="color: red; margin-top: -1.875rem">There's no hotel with such id in such date</p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text text-right" style="margin-top: -1.875rem"><strong>Empty rooms: </strong>${emptyRooms}</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <% pageContext.getSession().removeAttribute("emptyRooms");%>
            </div>
        </form>
    </div>
</div>