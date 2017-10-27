<div id="hotels" class="col col-lg-6 col-md-12 col-sm-12 col-xs-12">
    <div class="card">
        <div class="card-header h2">Hotels</div>
        <div class="card-body">
            <div class="card">
                <div class="card-body">
                    <form method="GET" action="HotelManagementServlet">
                        <h4 class="card-title">Check available rooms</h4>
                        <p class="card-text">Enter the hotel identifier and a date</p>
                        <div class="form-group">
                            <label for="hotelId">Hotel ID</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-bed" aria-hidden="true"></span>
                                <input type="number" class="form-control" name="hotelId" aria-describedby="hotelId" placeholder="Enter hotel Id" min="1" required>
                            </div>
                            <small id="hotelIdHelp" class="form-text text-muted">i.e. 09111714</small>
                        </div>
                        <div class="form-group">
                            <label for="hotelDate">Date</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                                <input type="number" class="form-control" name="hotelDate" placeholder="Enter date" min="1" required>
                            </div>
                            <small id="hotelIdHelp" class="form-text text-muted">Format: aaaammdd</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
            <br/>
            <div class="card">
                <div class="card-body">
                    <form method="POST" action="HotelManagementServlet">
                        <h4 class="card-title">Book seat</h4>
                        <p class="card-text">Enter a hotel identifier and a date for booking a room</p>
                        <div class="form-group">
                            <label for="hotelId">Hotel ID</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-bed" aria-hidden="true"></span>
                                <input type="number" class="form-control" name="hotelId" aria-describedby="hotelId" placeholder="Enter hotel Id" min="1" required>
                            </div>
                            <small id="hotelIdHelp" class="form-text text-muted">i.e. 09111714</small>
                        </div>
                        <div class="form-group">
                            <label for="hotelDate">Date</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                                <input type="number" class="form-control" name="hotelDate" placeholder="Enter date" min="1" required>
                            </div>
                            <small id="hotelIdHelp" class="form-text text-muted">Format: aaaammdd</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>