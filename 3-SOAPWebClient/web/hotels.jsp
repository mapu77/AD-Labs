<div id="hotels" class="col col-md-6">    
    <div class="card">
        <div class="card-header h2">Hotels</div>
        <div class="card-body">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Check available rooms</h4>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
            <br/>
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Book room</h4>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="card-body">
                    <form method="GET" action="HotelManagementServlet">
                        <h4 class="card-title">Check available rooms</h4>
                        <p class="card-text">Enter the hotel identifier and the a date</p>
                        <div class="form-group">
                            <label for="hotelId">Hotel ID</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-plane" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="hotelId" aria-describedby="hotelId" placeholder="Enter hotel Id" min="1">
                            </div>
                            <small id="hotelIdGelp" class="form-text text-muted">i.e. 09111714</small>
                        </div>
                        <div class="form-group">
                            <label for="hotelDate">Date</label>
                            <div class="input-group">
                                <span class="input-group-addon fa fa-calendar" aria-hidden="true"></span>
                                <input type="number" class="form-control" id="hotelDate" placeholder="Enter date" min="1">
                            </div>
                            <small id="hotelIdHelp" class="form-text text-muted">Format aaaammdd</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>