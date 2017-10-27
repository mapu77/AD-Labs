<div class="card">
    <div class="card-body">
        <form method="POST" action="flightManagementServlet">
            <h4 class="card-title">Book a seat</h4>
            <p class="card-text">Enter a flight identifier and a date for booking a seat</p>
            <div class="form-group">
                <label for="flightId">Flight ID</label>
                <div class="input-group">
                    <span class="input-group-addon fa fa-plane" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="flightId" aria-describedby="flightId" placeholder="Enter flight Id" min="1" required>
                </div>
                <small id="flightIdHelp" class="form-text text-muted">i.e. 09111714</small>
            </div>
            <div class="form-group">
                <label for="flightDate">Date</label>
                <div class="input-group">
                    <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                    <input type="number" class="form-control" name="flightDate" placeholder="Enter date" min="1" required>
                </div>
                <small id="flightIdHelp" class="form-text text-muted">Format: aaaammdd</small>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Book</button>
                <c:set var="ocuppiedSeats" value="${pageContext.getSession().getAttribute('ocuppiedSeats')}"></c:set>
                <c:if test="${not empty ocuppiedSeats}">
                    <br/>
                    <c:choose>
                        <c:when test="${ocuppiedSeats < 0}">
                            <p class="card-text text-right" style="color: red; margin-top: -1.875rem">There might be no empty seats in this flight<br/>for this date</p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text text-right" style="margin-top: -1.875rem"><strong>Ocuppied seats: </strong>${ocuppiedSeats}</p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <% pageContext.getSession().removeAttribute("ocuppiedSeats");%>
            </div>
        </form>
    </div>
</div>