function addVehicleToForm(vehicle) {
            var option = document.createElement("option");
            var br = document.createElement("br");
            var vehicles = document.getElementById("select-vehicle");

            option.value = vehicle.year + " " + vehicle.make + " " + vehicle.model;
            option.innerHTML = vehicle.year + " " + vehicle.make + " " + vehicle.model;

            vehicles.appendChild(option);
            vehicles.appendChild(br);
}
          document.getElementById("new-rental").addEventListener('submit', function(event) {
            event.preventDefault();

            var vehicle = document.getElementById('select-vehicle').value;		
            var pricePerDay = Number(document.getElementById('post_pricePerDay').value);	
            var mileage = parseInt(document.getElementById('post_mileage').value);
            var isAvailable = document.getElementById('post_isAvailable').value;

            var newRental = {
              vehicle: vehicle,
              pricePerDay: pricePerDay,
              mileage: mileage,
              isAvailable: isAvailable
            } 
           	console.log(JSON.stringify(newRental));
            let xhrNewRental = new XMLHttpRequest();	

            xhrNewRental.onreadystatechange = function() {
    
              if(xhrNewRental.readyState === 4) {	

                  var updated = JSON.parse(xhrNewRental.responseText);
                  addRentalToTable(updated);
                  document.getElementById('new-rental').reset();
                  //
              }
            }

            xhrNewRental.open('POST', '/individual-rental-project/rental');
           
            xhrNewRental.send(JSON.stringify(newRental));	
});
// load data after content is loaded
document.addEventListener("DOMContentLoaded", function() {
    let xhrRental = new XMLHttpRequest();
    let xhrVehicles = new XMLHttpRequest();

    xhrRental.onreadystatechange = function() {
        if(xhrRental.readyState === 4) {	
            var rentalArr = JSON.parse(xhrRental.responseText);
            rentalArr.forEach(item => {
            	console.log(item)
                addRentalToTable(item);
            });
        }
    }
    xhrRental.open('GET', '/individual-rental-project/rental');
    xhrRental.send();

    xhrVehicles.onreadystatechange = function() {
        if(xhrVehicles.readyState === 4) {
            var vehicleArr = JSON.parse(xhrVehicles.responseText);
            vehicleArr.forEach(item => {
                addVehicleToForm(item);
            });
        }
    }
    xhrVehicles.open('GET', '/individual-rental-project/vehicles');
    xhrVehicles.send();
});

// Create row and add row to table
function addRentalToTable(rental) {

    // create DOM elements
    var tr = document.createElement('tr');
    var id = document.createElement('td');		
    var mileage = document.createElement('td');		
    var pricePerDay = document.createElement('td'); 
    var year = document.createElement('td'); 	
    var make = document.createElement('td');
    var model = document.createElement('td');
    var isAvailable = document.createElement('td');
    

    // adding data to the elements
    id.innerHTML = rental.carId;
    mileage.innerHTML = rental.mileage;
    pricePerDay.innerText = "$" + rental.pricePerDay;
    year.innerText = rental.year;
    make.innerText = rental.make;
    model.innerText = rental.model;
    
    isAvailable.innerText = formatIsAvailable(rental.available);

    // add data to row
    tr.appendChild(id);
    tr.appendChild(mileage);
    tr.appendChild(pricePerDay);
    tr.appendChild(year);
    tr.appendChild(make);
    tr.appendChild(model);
    tr.appendChild(isAvailable);

    // add row to body
    document.getElementById('rental_table').appendChild(tr);
	
}
function formatIsAvailable(bool) {
    if (bool == true) {
        return "Yes";
    } else {
        return "No";
    }
}