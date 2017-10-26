<div id="flights" class="col col-md-6">
    <div class="card">
        <div class="card-header h2">Flights</div>
        <div class="card-body">
            <div class="card">
                <div class="card-body">
                    <form method="GET" action="FlightManagementServlet">
                        <h4 class="card-title">Check available seats</h4>
                        <p class="card-text">Enter the flight identifier and the date of your flight</p>
                        <div class="form-group">
                            <label for="flightId">Flight ID</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-plane" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="flightId" aria-describedby="flightId" placeholder="Enter flight Id" min="1">
                            </div>
                            <small id="flightIdHelp" class="form-text text-muted">I.e. 09111714</small>
                        </div>
                        <div class="form-group">
                            <label for="flightDate">Date</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="flightDate" placeholder="Enter date" min="1">
                            </div>
                            <small id="flightIdHelp" class="form-text text-muted">Format aaaammdd</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
            <br/>
            <div class="card">
                <div class="card-body">
                    <form method="POST" action="GestionHotelesServlet">
                        <h4 class="card-title">Book seat</h4>
                        <p class="card-text">Enter a flight identifier and a date for booking a seat</p>
                        <div class="form-group">
                            <label for="flightId">Flight ID</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-plane" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="flightId" aria-describedby="flightId" placeholder="Enter flight Id" min="1">
                            </div>
                            <small id="flightIdHelp" class="form-text text-muted">I.e. 09111714</small>
                        </div>
                        <div class="form-group">
                            <label for="flightDate">Date</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="flightDate" placeholder="Enter date" min="1">
                            </div>
                            <small id="flightIdHelp" class="form-text text-muted">Format aaaammdd</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>